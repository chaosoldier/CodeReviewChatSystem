package codereview.chatsys.ui;

import org.apache.wicket.markup.html.WebPage;

/**
 * すべてのWebPageの親クラス
 * 
 * @author yuta uehara
 */
public abstract class AbstractPage extends WebPage{
	private static final long serialVersionUID = 5940554525205234369L;
	abstract protected boolean getEnableSideBar();
	
}
