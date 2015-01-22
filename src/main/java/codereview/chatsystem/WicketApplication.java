package codereview.chatsystem;


import lombok.extern.log4j.Log4j;
import codereview.chatsystem.ui.*;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;

import codereview.chatsystem.ui.IndexPage;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */

@Log4j
public class WicketApplication extends AuthenticatedWebApplication {

	@Override
	public void init() {
		super.init();
		this.getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		this.getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		log.info("a");
		mount(new MountedMapper("/signin", LoginPage.class, new UrlPathPageParametersEncoder()));
		//TODO delete this line
		//test code
//		mount(new MountedMapper("/conference1", (Class<? extends IRequestablePage>) AdminConferenceContentPage.class, new UrlPathPageParametersEncoder()));



		initGuice();
	}

	//add by AuthenticatedWebApplication
	//ログインページを返す
    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
    	MySession.get().invalidate();
        return LoginPage.class;
    }

	//add by AuthenticatedWebApplication
    //Webセッションを返す
    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return MySession.class;
    }

    @Override
    protected void onUnauthorizedPage(Page page) {
    	MySession.get().invalidate();
    	throw new RestartResponseAtInterceptPageException(LoginPage.class);
    }

	//add by AuthenticatedWebApplication
    //認証に失敗したときの処理を用意するためのメソッド
//    @Override
//    public void onUnauthorizedPage(Page page) {
//        throw new RestartResponseAtInterceptPageException(ErrorPage.class);
//    }

	//add by AuthenticatedWebApplication
    //homePageに移動
    @Override
    public Class<? extends Page> getHomePage() {
        return LoginPage.class;
    }

	//add by AuthenticatedWebApplication
    //getConifgurationType
    //開発中の時は、WebApplication.DEVELOPMENT
//	@Override
//	public String getConfigurationType() {
//		return org.apache.wicket.Application.get().;
//	}
//
//
//
//	@Override
//	public Session newSession(Request request, Response response) {
//		return new MySession(request);
//	}
//
	protected void initGuice() {
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, getGuiceModule()));
	}
//
	protected Module getGuiceModule() {
		return new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(IDBCP.class).to(DBCP.class).asEagerSingleton();
			}
		};
	}

}
