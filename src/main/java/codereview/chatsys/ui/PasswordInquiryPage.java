package codereview.chatsys.ui;

import java.util.ArrayList;
import java.util.List;



import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

public class PasswordInquiryPage extends ParentPage{

//	@Inject
//	ISendMailService mservice;
//
//	@Inject
//	ILoginService loginservice;

	private IModel<String> mailModel;

	private String title = "パスワード送信";
	private String subject = "パスワードはです";
	private List<String> toAddressList=new ArrayList<>();
	private String newpass;
	//toAdderssList.add("cist.b212.shibata@gmail.com");
	//toAdderssList.add("cist.b212.uehara@gmail.com");

	private List<String> ccAddressList=new ArrayList<>();
	private List<String> bccAddressList=new ArrayList<>();

	private int conferenceId;

	public static String setCrumbLastString(){
		//TODO このページ名を記述
		return "パスワード送信ページ";
	}
	
	public PasswordInquiryPage(PageParameters param){

		super(null);
//		//paramがnullなら、この時点でNotFoundPage。
//		//なぜかすでにNotFoundPageのParamがついているなら、この時点でNotFoundPage
//		if(param.get("id").toString(PageParamDefinition.NOTFOUNDPAGE.toString())
//				.equals(PageParamDefinition.NOTFOUNDPAGE.toString())){
//			throw new RestartResponseException(NotFoundPage.class);
//		}
//		//エイリアスの大会idが存在しないとき、この時点でNotFoundPage
//		if(!cservice.isConferenceAiliasAvailable(param.get("id").toString())){
//			throw new RestartResponseException(NotFoundPage.class);
//		}
		//大会idが存在するので設定
//		conferenceId = cservice.getConferenceIdByAilias(param.get("id").toString());

		mailModel = new Model<>();

		Form<Void> form = new Form<Void>("toPasswordInquiryCompletedPage") {

			private static final long serialVersionUID = 6131985258676512577L;

				@Override
			public void onSubmit() {
					super.onSubmit();
					//toAddressList.add("cist.b212.iihara@gmail.com");
//
//					newpass = loginservice.createNewPassword(mailModel.getObject());
//					subject = "パスワードは"+newpass+"です";
//					toAddressList.add(mailModel.getObject());
//					mservice.sendMail(title, subject, toAddressList, ccAddressList, bccAddressList);
//					setResponsePage(new PasswordInquiryCompletedPage());

			}
		};

		add(new Link<Void>("toLoginPage") {
			private static final long serialVersionUID = -7440221385073696661L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});

		form.add(new TextField<String>("mail", mailModel));

		add(form);

	}

	@Override
	protected String getTitle() {
		// TODO 自動生成されたメソッド・スタブ
		return "パスワード再設定";
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		//セッションありでこのページを表示させない
		if(MySession.get().isSignedIn()){
			MySession.get().invalidate();
		}
	}


}
