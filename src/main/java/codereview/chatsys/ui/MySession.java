package codereview.chatsys.ui;

import codereview.chatsys.service.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;

import com.google.inject.Inject;

/**
 * @author yuta uehara
 *
 */
@Log4j
public class MySession extends AuthenticatedWebSession implements Serializable{
	private static final long serialVersionUID = 7901051676904438201L;

	/**
	 * @author yuta uehara
	 *
	 */
	public static final String ROLE_SYSADMIN_USER = "システム管理者";
	public static final String ROLE_GENERARL_USER = "一般ユーザ";

	@Inject
	private ILoginService loginService;

	//emailとconferenceidとentryidを保持
//	@Getter
//	@Setter
//	private ConferenceEntryBean conferenceEntryBean;
	
	//一般ユーザの権限
//	@Getter
//	private GeneralUserAuthrities authrity;

	//一時的なconference_id セッション内部で一時的に使用する（セッション確立後はbeanからとってくる）
	//MySession.get().getConferenceEntryBean().getConference_id()
	private int temporarySelectedConferenceId;

	// 利用可能な大会情報
//	@Getter
//	@Setter
//	List<ConferenceBean> availablebeanList;

	// アカウントID
//	@Getter
//	private int accountId;

	@Getter
	private Roles roles;

	// @Inject
	// private IAuthService AuthSerice;

	public static MySession get() {
		return (MySession) Session.get();
	}

	public MySession(Request request) {
		super(request);
		this.roles = null;
		WebApplication.get().getMetaData(GuiceInjectorHolder.INJECTOR_KEY)
				.getInjector().injectMembers(this);
		this.temporarySelectedConferenceId = 0;
//		availablebeanList = new ArrayList<ConferenceBean>();
	}

	@Override
	public boolean authenticate(String email, String pass) {
		// superClassのsignInメソッドから呼び出される認証部分
		// 一般ユーザの場合
//		if (temporarySelectedConferenceId > 0) {
//			if (loginService.login(email, pass, temporarySelectedConferenceId)) {
//				
//				//beanを取得
//				conferenceEntryBean = loginService.getConferenceEntryBean(temporarySelectedConferenceId, email);
//				log.info(conferenceEntryBean.getConference_id());
//				//TODO　最初からBEANに入れたほうがスマートなので修正できれば修正する
//				temporarySelectedConferenceId = -1;//後処理
//				
//				//権限を設定
//				this.authrity = 
//						loginService
//						.getGeneralUserAuthrities(conferenceEntryBean.getConference_id(),conferenceEntryBean.getEmail_address());
//				log.info(authrity);
//				//この後はselectedConferenceIdを使わない
//				roles = new Roles(Roles.USER);
//				replaceSession();
//				return true;
//			}
//			// システム管理者の場合
//		} else {
//			if (loginService.loginAsSystemAccount(email, pass)) {
//				conferenceEntryBean = loginService.getConferenceEntryBean(email);
//				roles = new Roles(Roles.ADMIN);
//				replaceSession();
//				return true;
//			}
//		}
		return false;

	}

//	/**
//	 * ログイン前にconferenceIdとGeneralUserAuthrityを設定する（失敗したらセッション破棄必須）
//	 * 
//	 * @param ConferenceId
//	 * @param email
//	 * @return
//	 */
//	public boolean isAllowdConference(int ConferenceId, String email) {
//		if (loginService.checkAllowdConference(ConferenceId, email)) {
//			this.selectedConferenceId = ConferenceId;
//			// setGeneralUserAuthrity();//一般ユーザの権限もセッションに保存する
//			return true;
//		}
//		return false;
//	}
	
	//TODO コメント変更
	/**
	 * 
	 * 次の機能は実装していない
	 * セッションに大会を設定
	 * 大会に入れるかどうかを検査して問題がなければ selectedConferenceIdの設定と同時に一般ユーザの権限もセッションに保存する
	 *
	 * @param ConferenceId
	 */
	public void setTemoraryConferenceId(int ConferenceId) {
		this.temporarySelectedConferenceId = ConferenceId;
		// TODO
//		if (loginService.checkAllowdConference(ConferenceId, email)) {
//			this.selectedConferenceId = ConferenceId;
//			setGeneralUserAuthrity();// 一般ユーザの権限もセッションに保存する
//			return true;
//		}
	}
//	public void setConferenceIdOnBean(int ConferenceId) {
//		this.conferenceEntryBean.
//	}

	/**
	 * @see setConferenceId() システム管理者は一般ユーザの権限を設定しない
	 *      一般ユーザでも、Indexpageに滞在しているaccountは権限を設定しない
	 *      注意：MySessionのコンストラクタにて、selectedConferenceIdは0で初期化されている
	 */
//	private void setGeneralUserAuthrity() {
//
//		// システム管理者でなければ一般ユーザの権限を設定
//		if (!getRoles().toString().equals("ADMIN")) {
//			log.info("acc " + accountId + " cid" + selectedConferenceId);
//			// accountIdと大会IDを使って大会参加者IDを特定した上で権限を確認
//			switch (loginService.checkGeneralUserAuthrity(selectedConferenceId,
//					accountId)) {
//			case "管理者":
//				this.GeneralUserAuthrity = GeneralUserAuthrities.ORGANIZER;
//				break;
//			case "講演者":
//				this.GeneralUserAuthrity = GeneralUserAuthrities.PRESENTER;
//				break;
//			case "聴講者":
//				this.GeneralUserAuthrity = GeneralUserAuthrities.AUDITOR;
//				break;
//			}
//		}
//
//	}

	@Override
	public Roles getRoles() {
		if (isSignedIn()) {
			return this.roles;
		}
		return null;
	}

	/**
	 * 一般ユーザの権限判定用メソッド
	 *
	 * 特定学会に関係しないページは判定用コードはいれないこと ＜例１＞特定大会に入っていないユーザは弾く
	 *
	 * @Override protected void onInitialize() { super.onInitialize();
	 *           if(!MySession
	 *           .get().getEnableGeneralUserAuthrity(GeneralUserAuthrities
	 *           .PRESENTER)){ throw new
	 *           RestartResponseAtInterceptPageException(LoginPage.class); } }
	 *           
	 *           
	 *           ＜例２＞特定学会に入っていても特定学会の管理者以外のユーザは弾く
	 * @Override protected void onInitialize() { super.onInitialize();
	 *           if(!MySession
	 *           .get().getEnableGeneralUserAuthrity(GeneralUserAuthrities
	 *           .ORGANIZER)){ throw new
	 *           RestartResponseAtInterceptPageException(LoginPage.class); } }
	 *           
	 *           ＜例３＞管理者は可能、特定学会の管理者以外のユーザは弾く
	 * @Override protected void onInitialize() { super.onInitialize();
	 *           if(管理者ではない){
	 *           if(!MySession.get().getEnableGeneralUserAuthrity(GeneralUserAuthrities
	 *           .ORGANIZER)){ throw new
	 *           RestartResponseAtInterceptPageException(LoginPage.class); } } }
	 * @param authorizedAuthrity
	 *            　許可されている権限を入れる
	 * @return　許可されているか
	 */
	//書き方　特定大会の講演者以上のユーザは許可
//	@Override
//	protected void onInitialize() {
//		super.onInitialize();
//		if (!MySession.get().getEnableGeneralUserAuthrity(
//				GeneralUserAuthrities.PRESENTER)) {
//			PageParameters parameters = new PageParameters();
//			parameters.add("id", String.valueOf(MySession.get().getConferenceEntryBean().getConference_id()));
//			setResponsePage(IndividualLoginPage.class,parameters);
//		}
//	}
	// TODO check this program
	// 許可された権限 比較　実際の権限
	// 1 <= 1 ok
	// 1 <= 2 ok
	// 1 <= 3 ok
	// 2 <= 1 ng
	// 2 <= 2 ok
	// 2 <= 3 ok
	// 3 <= 1 ng
	// 3 <= 2 ng
//	public boolean getEnableGeneralUserAuthrity(
//			GeneralUserAuthrities authorizedAuthrity) {
//		if (getRoles().toString().equals("USER")) {
//			if (authorizedAuthrity.toIntValue() <= authrity
//					.toIntValue()) {
//				return true;
//			} else {
//				return false;
//			}
//		} else if (getRoles().toString().equals("ADMIN")) {
//			return false;
//		} else {
//			return false;
//		}
//	}

	/**
	 * ユーザの種類名を日本語で返す
	 * 
	 * @return 該当するものがないときは０文字で返す
	 */
	public String getRoleName() {
		if (isSignedIn()) {
			if (getRoles().toString().equals("USER")) {
				return ROLE_GENERARL_USER;
			} else if (getRoles().toString().equals("ADMIN")) {
				return ROLE_SYSADMIN_USER;
			} else {
				return "";
			}
		}
		return null;
	}
	
	//TODO 不要？
//	/**
//	 * 一般ユーザの権限名を日本語で返す
//	 * 
//	 * @return 該当するものがないときは０文字で返す
//	 */
//	public String getGeneralUserAuthrityName() {
//		if (isSignedIn()) {
//			if (getRoles().toString().equals("USER")) {
//				log.info("種類：ユーザ");
//				// 特定の権限以外入らないことを想定しているため、0文字判定などはしていない
//				if (GeneralUserAuthrity == null) {
//					log.info("大会IDが設定されていない");
//					return "";
//				} else {
//					log.info("返るはず");
//					return this.GeneralUserAuthrity.toString();
//				}
//
//			} else if (getRoles().toString().equals("ADMIN")) {
//				log.info("種類：管理者");
//				return "";
//			} else {
//				log.info("not login");
//				return "";
//			}
//		}
//		return "";
//	}

}