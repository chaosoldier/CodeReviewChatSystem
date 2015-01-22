package codereview.chatsystem.ui;

import codereview.chatsystem.bean.AccountBean;
import codereview.chatsystem.service.*;

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
public class MySession extends AuthenticatedWebSession implements Serializable {
	private static final long serialVersionUID = 7901051676904438201L;

	/**
	 * @author vicunga
	 *
	 */

	/**
	 * accountId email availableを所持
	 */
	@Getter
	private AccountBean myAccountBean;

	@Inject
	private ILoginService loginService;

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
	}
	@Override
	public boolean authenticate(String email, String pass) {
		// superClassのsignInメソッドから呼び出される認証部分
		// 一般ユーザの場合
		if (loginService.loginAsUserAccount(email, pass)) {
			this.myAccountBean = loginService.getAccountBean(email);
			if(myAccountBean.isAvailable()){
				roles = new Roles(Roles.USER);
				replaceSession();
				return true;
			}
		}
		return false;
	}

	@Override
	public Roles getRoles() {
		return this.roles;
	}
}