package codereview.chatsystem.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.expr.NewArray;
import lombok.extern.log4j.Log4j;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource.Attributes;
import org.apache.wicket.util.time.Duration;

import codereview.chatsystem.bean.CheckMessageBean;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.MessageFileBean;
import codereview.chatsystem.definition.MessageType;
import codereview.chatsystem.service.ILoginService;
import codereview.chatsystem.service.IPersonalService;
import codereview.chatsystem.service.iMessageService;

import com.google.inject.Inject;

/**
 * @author vicugna
 */
@Log4j
@AuthorizeInstantiation({ Roles.ADMIN, Roles.USER })
public class IndexPage extends WebPage {

	private Model<String> bodyModel;
	private Model<Boolean> checkAvailableModel;
	private static final long serialVersionUID = 1032132669390389697L;
	

	@Inject
	iMessageService mService;
	@Inject
	IPersonalService pService;

	private int account_id;
	public IndexPage() {
		//アカウントid設定
		account_id = MySession.get().getMyAccountBean().getAccount_id();
		//既読
		mService.setReadMessage(account_id);
//		this.add(new Label("chartA","<script>google.setOnLoadCallback(drawChart);"
//				+ "function drawChart() {"
//				+ "var data = google.visualization.arrayToDataTable(["
//				+ "['Effort', 'amount'],"
//				+ "['既読',     80],"
//				+ "['未読',     20],"
//				+ "]);"
//				+ "var options = {"
//				+ "pieHole: 0.5,"
//				+ "pieSliceTextStyle: {"
//				+ "color: 'white',"
//				+ "},"
//				+ "legend: 'none'"
//				+ "};"
//				+ "var chart = new google.visualization.PieChart(document.getElementById('donut_single'));"
//				+ "chart.draw(data, options);"
//				+ "}</script>"
//				).setRenderBodyOnly(true)
//				.setEscapeModelStrings(false));
		
		//ヘッダ
		this.add(new Link<Void>("toAccountUpdatePageHtml") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(AccountUpdatePage.class);
			}
		});
		this.add(new Link<Void>("toLogoutPageHtml") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(LogoutPage.class);
			}
		});	
        // リストビュー用のコンテナを作成してフォームに追加

		
		this.add(new ListView<MessageBean>("showMsgBoxHtml", mService.getAllMessageBeans()) {

			protected void populateItem(ListItem<MessageBean> item) {
				item.add(new Label("show1",
						"<div class=\"fukidashi-line\">"
						+ "<div class=\"kao\">").setRenderBodyOnly(true)
						.setEscapeModelStrings(false));
				//アバターの表示
				item.add(new Image("hoge", new DynamicImageResource() {
							@Override
							protected byte[] getImageData(Attributes attributes) {
								return pService.getPersonalBean(item.getModel().getObject().getAccount_id()).getData();
							}
						}).add(
						new AttributeAppender("class", new Model<String>(
								"img2"), " ")));
				item.add(new Label("show15",
						//投稿者の名前の表示
						"<span class=\"position\">"
						+ pService.getPersonalBean(item.getModel().getObject().getAccount_id()).getName()
						+ "</span>"
						+"<span class=\"position\">"
						+ "既読数："+mService.countReadPerMessage(item.getModel().getObject().getMessage_id())
						+ "</span>"
						+ "</div><div class=\"fukidashi_left\">").setRenderBodyOnly(true)
						.setEscapeModelStrings(false));
				item.add(new Label("showMsgBodyBoxHtml",
						"<div class=\"box left\">"+
						convertTagChar(item.getModel().getObject().getBody())
						.replaceAll("\n","<br />")+
						"</div>"){
					@Override
					protected void onInitialize() {
							super.onInitialize();
//							add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(10)));
					}
					@Override
					protected void onConfigure() {
							super.onConfigure();
					}
				}
				.setRenderBodyOnly(true)
						.setEscapeModelStrings(false));
				item.add(new Label("show2",
						//投稿時間の表示
						new SimpleDateFormat("MM'月'dd'日' E H:mm").format(item.getModel().getObject().getInsert_at())
						).setRenderBodyOnly(true)
						.setEscapeModelStrings(false));
				//本人のものか
				if(mService.isThisAcountMessage(
								item.getModel().getObject().getMessage_id(),
								account_id)){
					item.add(new Form<Void>("checkButtonFormHtml"){
						@Override
						protected void onSubmit(){
							mService.setCheckMessage(new CheckMessageBean(
									item.getModel().getObject().getMessage_id(),
									account_id,
									new Timestamp(System.currentTimeMillis())
									));
							setResponsePage(IndexPage.class);
						}
					}.add(new Label("checkButtonFormTextHtml", ""
							+ "ご本人").setEscapeModelStrings(false)).add(
							new AttributeAppender("class", new Model<String>(
									"list-group-item blog_sidebar"), " ")));
				//確認済みか
				}else if(mService.isThisAcountMessage(
						item.getModel().getObject().getMessage_id(),
						account_id)){
					item.add(new Form<Void>("checkButtonFormHtml"){
						@Override
						protected void onSubmit(){
							mService.setCheckMessage(new CheckMessageBean(
									item.getModel().getObject().getMessage_id(),
									account_id,
									new Timestamp(System.currentTimeMillis())
									));
							setResponsePage(IndexPage.class);
						}
					}.add(new Label("checkButtonFormTextHtml", ""
							+ "確認済み").setEscapeModelStrings(false)).add(
							new AttributeAppender("class", new Model<String>(
									"list-group-item blog_sidebar"), " ")));
				}else{
					item.add(new Form<Void>("checkButtonFormHtml"){
						@Override
						protected void onSubmit(){
							mService.setCheckMessage(new CheckMessageBean(
									item.getModel().getObject().getMessage_id(),
									account_id,
									new Timestamp(System.currentTimeMillis())
									));
							setResponsePage(IndexPage.class);
						}
					}.add(new Label("checkButtonFormTextHtml", ""
							+ "未確認").setEscapeModelStrings(false)).add(
							new AttributeAppender("class", new Model<String>(
									"list-group-item blog_sidebar"), " ")));
					
				}
				
				
				item.add(new Label("show3",
						"</div>"
						+ "</div>").setRenderBodyOnly(true)
						.setEscapeModelStrings(false));
//				item.add(new Link<String>("sidebarLinksHTML") {
//					private static final long serialVersionUID = 5329037423254337160L;
//
//					@Override
//					public void onClick() {
//						setResponsePage(item.getModel().getObject()
//								.getPageClass());
//					}
//				}.add(new Label("sidebarLinktextHtml", ""
//						+ item.getModel().getObject().getPageName()
//						+ "<br />").setEscapeModelStrings(false)).add(
//						new AttributeAppender("class", new Model<String>(
//								"list-group-item blog_sidebar"), " ")));
			}
		}
		);

		//新規投稿
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
	    feedbackPanel.setOutputMarkupId( true );
	    this.add(feedbackPanel);
		bodyModel = new Model<>();
		final FileUploadField uploadField = new FileUploadField("fileUploadHtml");
		final CheckBox checkAvailable = new CheckBox("checkAvailableHtml", Model.of(Boolean.TRUE));
//		AjaxButton ajaxButton = new AjaxButton("newSubmitAjaxHtml") {
//			@Override
//		    protected void onSubmit(AjaxRequestTarget target, Form<?> newMssageForm) {
//				super.onSubmit();
//				int mid = mService.setMessage(new MessageBean(
//						account_id, MessageType.MAIN, 
//						bodyModel.getObject(),
//						new Timestamp(System.currentTimeMillis()), 
//						checkAvailable.getModelObject() ));
//				if(uploadField.getFileUpload()!=null){
//					//TODO up可能なファイルの検討
//					//TODO validatorの追加
//					InputStream stream;
//					try {
//						stream = uploadField.getFileUpload().getInputStream();
//						byte[] bytes = readAll(stream);//stream 書き込み処理
//						mService.setMessageFile(new MessageFileBean(mid, bytes, uploadField.getFileUpload().getClientFileName()));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				setResponsePage(MessageSendPage.class);
////              target.addComponent(newMssageForm.get("listViewContainer"));
//                // テキストフィールドの内容をリストビューに追加
////                TextField field = (TextField)form.get("messageField");
////                String textString = field.getValue();
////                ListView listView = (ListView)form.get("listViewContainer:listView");
////                List<String> list = listView.getModelObject();
////                list.add(textString);
////                target.addComponent(form.get("listViewContainer"));
//            }
//			
//		};
		Form<Void> newMssageForm = new Form<Void>("newMessageFormHtml") {
			@Override
			public void onSubmit() {
				int mid = mService.setMessage(new MessageBean(
						account_id, MessageType.MAIN, 
						bodyModel.getObject(),
						new Timestamp(System.currentTimeMillis()), 
						checkAvailable.getModelObject() ));
				if(uploadField.getFileUpload()!=null){
					//TODO up可能なファイルの検討
					//TODO validatorの追加
					InputStream stream;
					try {
						stream = uploadField.getFileUpload().getInputStream();
						byte[] bytes = readAll(stream);//stream 書き込み処理
						mService.setMessageFile(new MessageFileBean(mid, bytes, uploadField.getFileUpload().getClientFileName()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				setResponsePage(MessageSendPage.class);
				
			}
		};

		newMssageForm.add(new TextArea("bodyHtml", bodyModel));
		newMssageForm.add(checkAvailable);
		newMssageForm.setMultiPart(true);
		newMssageForm.add(uploadField);
		this.add(newMssageForm);
		
		//.add(new Check("check",item.getModel()));
		



	}


	public void renderHead(IHeaderResponse response) {
		// TODO HTML5 on IE9
		// <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at
		// the end of file. -->
		// <!--[if lt IE 9]>
		// <script src="js/vendor/html5shiv.js"></script>
		// <script src="js/vendor/respond.min.js"></script>
		// <![endif]-->

		// <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at
		// the end of file. -->
		// <!--[if lt IE 9]>
		// <script src="js/vendor/html5shiv.js"></script>
		// <script src="js/vendor/respond.min.js"></script>
		// <![endif]-->

		response.render(JavaScriptHeaderItem
				.forUrl("./html/js/vendor/jquery.min.js"));

		response.render(JavaScriptHeaderItem
				.forUrl("./html/js/bootstrap.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("./html/js/flat-ui.min.js"));
		response.render(JavaScriptHeaderItem
				.forUrl("./html/js/google-code-prettify/prettify.js"));
		response.render(JavaScriptHeaderItem
				.forUrl("./html/js/jquery.ex-code-prettify.js"));

		response.render(CssHeaderItem
				.forUrl("./html/css/vendor/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./html/css/flat-ui.css"));
		response.render(CssHeaderItem
				.forUrl("./html/css/jquery.ex-code-prettify.css"));
		response.render(CssHeaderItem.forUrl("./html/custom.css"));
	}
	public byte[] readAll(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte [] buffer = new byte[1024];
	    while(true) {
	        int len = inputStream.read(buffer);
	        if(len < 0) {
	            break;
	        }
	        bout.write(buffer, 0, len);
	    }
	    return bout.toByteArray();
	}
	/**
	 *　文字列から＜＞を適当に変換　preのなかにpreがあっても対応
	 * @param str2
	 * @return
	 */
	private String convertTagChar(String str2){
		int b = 0;
		int a = str2.lastIndexOf("</pre>");
		String regex = "<pre>";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str2);

		if (m.find()){
		    b = m.end();
		}
		//preがないbodyに対応
		String text = str2;
		String textPreString = str2;
		String textAfterString = str2;
		try {
			textPreString = str2.substring(0,b);
			textAfterString = str2.substring(a,str2.length());
			text = str2.substring(b,a);
			text = text.replace("<","&lt;");
			text = text.replace(">", "&gt;");
			return textPreString + text + textAfterString;
		} catch (StringIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}


}