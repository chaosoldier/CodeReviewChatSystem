package codereview.chatsystem.ui;


import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author yuta uehara
 *
 */
@AuthorizeInstantiation({Roles.USER})
public class MessageSendPage extends WebPage{
	private static final long serialVersionUID = -7000810615002308365L;

	public MessageSendPage(){
		add(new Link<Void>("toIndexPageHtml"){
			private static final long serialVersionUID = -3613209872321571566L;

			@Override
			public void onClick(){
				setResponsePage(IndexPage.class);
			}
		});
	}

	public void renderHead(IHeaderResponse response) {
		 //TODO HTML5 on IE9
		//<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
		//<!--[if lt IE 9]>
		//  <script src="js/vendor/html5shiv.js"></script>
		//  <script src="js/vendor/respond.min.js"></script>
		//<![endif]-->
		
		//<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
		//<!--[if lt IE 9]>
		//  <script src="js/vendor/html5shiv.js"></script>
		//  <script src="js/vendor/respond.min.js"></script>
		//<![endif]-->
		response.render(JavaScriptHeaderItem.forUrl("./html/js/vendor/jquery.min.js"));
		
		response.render(JavaScriptHeaderItem.forUrl("./html/js/bootstrap.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("./html/js/flat-ui.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("./html/js/google-code-prettify/prettify.js"));
		response.render(JavaScriptHeaderItem.forUrl("./html/js/jquery.ex-code-prettify.js"));
			
		response.render(CssHeaderItem.forUrl("./html/css/vendor/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./html/css/vendor/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./html/css/jquery.ex-code-prettify.css"));
		response.render(CssHeaderItem.forUrl("./html/css/flat-ui.css"));
		response.render(CssHeaderItem.forUrl("./html/yahagi.css"));
	}

}