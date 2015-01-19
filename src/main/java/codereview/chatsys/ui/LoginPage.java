package codereview.chatsys.ui;


import lombok.extern.log4j.Log4j;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import codereview.chatsys.service.LoginService;

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
public class LoginPage extends ParentPage {

	private static final long serialVersionUID = -1007740059001063919L;

	private final String loginFailedMsg = "ログインできません";

	private IModel<String> idModel;
	private IModel<String> passModel;

	private IModel<String> testPassModel;

	@Inject
	private LoginService lservice;
	


	@Override
	protected String getTitle() {
		return "ログインページ";
	}
	public LoginPage(PageParameters param) {
		/**
		 * @param enableSidebar
		 *            　サイドバーを有効にする場合はtrue　サイドバーを有効にしない場合はfalse
		 * @param PAGE
		 *            ページを設定　ParentPage.javaを編集してください
		 */
		// TODO trueかfalseを設定
		// TODO PageInfo.を設定
		super(param);
		
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
		this.add(new FeedbackPanel("error"));

		Form<Void> form = new Form<Void>("toIndexPage") {

			private static final long serialVersionUID = 6131985258676512577L;

			@Override
			public void onSubmit() {
				MySession session = (MySession) this.getSession();
				if (session.signIn(idModel.getObject(), passModel.getObject())) {
					log.info("login success");
					setResponsePage(IndexPage.class,new PageParameters().add("id","ChitoseConference1"));
				} else {
					// TODO FeedbackPanelを設置すれば画面に表示される
					error(loginFailedMsg);
				}
			}
		};
		add(form);
		form.add(new TextField<String>("id", idModel));
		form.add(new PasswordTextField("pass", passModel));

		Form<Void> formConference = new Form<Void>("toIndividualIndexPageHtml") {
			private static final long serialVersionUID = 6131985258676512577L;

			@Override
			public void onSubmit() {
//				MySession session = (MySession) this.getSession();
//				session.setTemoraryConferenceId(1);
//				if (session.signIn(idModel.getObject(), passModel.getObject())) {
//					// ログイン終わった状態
//					log.info("login success");
//					setResponsePage(IndividualTopPage.class);
//				} else {
//					log.info("login できない");
//					// TODO この時点でやってしまったほうがいいのか確認
//					// session.invalidate();
//					error(loginFailedMsg);
//				}
			}
		};
		add(formConference);
		formConference.add(new TextField<String>("idConfereceHtml", idModel));
		formConference.add(new PasswordTextField("passConferenceHtml",
				passModel));

		add(new Link<Void>("test") {
			private static final long serialVersionUID = -7440221385073696661L;

			@Override
			public void onClick() {
				setResponsePage(new IndexPage());
			}
		});
		

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
		add(formPassMaker);
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

}
