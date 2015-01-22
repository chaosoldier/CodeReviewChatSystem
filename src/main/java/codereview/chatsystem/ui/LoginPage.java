package codereview.chatsystem.ui;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import codereview.chatsystem.dao.IPersonalDAO;
import codereview.chatsystem.service.ILoginService;
import codereview.chatsystem.service.LoginService;
import codereview.chatsystem.service.iMessageService;

import com.google.inject.Inject;

/**
 *
 * @author Hiroto Yamakawa
 * @author Kazuki Madokoro
 * @author Yuta Uehara
 * @author Ryuusuke Kobayashi
 * @author Harunori Shibata
 * @author Takatoshi Iihara
 */
@Log4j
public class LoginPage extends WebPage {

	private static final long serialVersionUID = -1007740059001063919L;

	private final String loginFailedMsg = "ログインできません";

	private IModel<String> idModel;
	private IModel<String> passModel;

	private IModel<String> testPassModel;

	@Inject
	private ILoginService lservice;
	
	@Inject
	private  IPersonalDAO pdao;
	
	@Inject
	private iMessageService mservice;


//	@Override
//	protected String getTitle() {
//		return "ログインページ";
//	}
	public LoginPage(PageParameters param) {

		/**
		 * @param enableSidebar
		 *            　サイドバーを有効にする場合はtrue　サイドバーを有効にしない場合はfalse
		 * @param PAGE
		 *            ページを設定　ParentPage.javaを編集してください
		 */
		// TODO trueかfalseを設定
		// TODO PageInfo.を設定
		
		//SYSTEMADMINSIGNINで指定したparamを付加しないアクセスは認めない
//		if(!param.get("id").toString(PageParamDefinition.NOTFOUNDPAGE.toString())
//				.equals(PageParamDefinition.SYSTEMADMINSIGNIN.toString())){
//			throw new RestartResponseException(NotFoundPage.class);
//		}
		//
		// lservice.createNewPassword("aa@docomo.ne.jp");
		//
		idModel = new Model<>();
		passModel = new Model<>();
//		this.add(new FeedbackPanel("error"));

		Form<Void> loginForm = new Form<Void>("loginFormHtml") {
			private static final long serialVersionUID = 6131985258676512577L;
			@Override
			public void onSubmit() {
//				setResponsePage(AccountUpdatePage.class);
				MySession session = (MySession) this.getSession();
				if (session.signIn(idModel.getObject(), passModel.getObject())) {
					log.info(idModel.getObject()+":"+passModel.getObject());
					setResponsePage(new IndexPage());
					
				} else {
					// TODO FeedbackPanelを設置すれば画面に表示される
					error(loginFailedMsg);
				}
			}
		};
		loginForm.add(new TextField<String>("idHtml", idModel));
		loginForm.add(new PasswordTextField("passHtml", passModel));
		add(loginForm);
		
//		try {
//			pdao.insertpaper();
//		} catch (NullPointerException | SQLException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}


		add(new Link<Void>("test") {
			private static final long serialVersionUID = -7440221385073696661L;

			@Override
			public void onClick() {
				setResponsePage(new AccountUpdatePage());
			}
		});
		add(new Link<Void>("toChatPageHtml") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2780369743836587655L;

			@Override
			public void onClick() {
				setResponsePage(new IndexPage());
			}
		});
		
//		.add(
//				new AttributeAppender("class", new Model<String>(
//						"form-control"), " "))
		
		testPassModel = new Model<>();

		// TODO delete from this line
		// 重要　本稼働時、sampleパッケージと下のnew SampleSendMail();は外す
		Form<Void> formPassMaker = new Form<Void>("formPassMakerHtml") {
			private static final long serialVersionUID = 6131985258676512577L;

			@Override
			public void onSubmit() {
//
//				String pass = lservice.convertPass(testPassModel.getObject(),
//						testPassModel.getObject(), Encrypter.TYPE2);
//				error(pass);
//				log.info(pass);
			}
		};
//		add(formPassMaker);
		// <!-- TODO delete TO this line -->
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
//		 セッションありでこのページを表示させない
		 if(MySession.get().isSignedIn()){
			 MySession.get().invalidate();
		 }
	}
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forUrl("./html/js/bootstrap.min.js"));
	
		response.render(CssHeaderItem.forUrl("./html/css/vendor/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./html/css/flat-ui.css"));
		response.render(CssHeaderItem.forUrl("./html/css/demo.css"));
		response.render(CssHeaderItem.forUrl("./html/css/yahagi.css"));
	}
//    <meta name="viewport" content="width=device-width, initial-scale=1.0">
//
//    <!-- Loading Bootstrap -->
//    <link href="../dist/css/vendor/bootstrap.min.css" rel="stylesheet">
//
//    <!-- Loading Flat UI -->
//    <link href="../dist/css/flat-ui.min.css" rel="stylesheet">
//
//    <link rel="shortcut icon" href="img/favicon.ico">
//
//    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
//    <!--[if lt IE 9]>
//      <script src="js/vendor/html5shiv.js"></script>
//      <script src="js/vendor/respond.min.js"></script>
//    <![endif]-->
//    <script type="text/javascript" src="./footerFixed.js"></script>
//    <link rel="stylesheet" type="text/css" href="yahagi.css">
	
//	
//    <!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
//    <script src="../dist/js/vendor/jquery.min.js"></script>
//    <!-- Include all compiled plugins (below), or include individual files as needed -->
//    <script src="../dist/js/vendor/video.js"></script>
//    <script src="../dist/js/flat-ui.min.js"></script>


}
