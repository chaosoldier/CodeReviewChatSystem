package codereview.chatsys.ui;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


import codereview.chatsys.definition.GeneralUserAuthrities;
import codereview.chatsys.definition.PageParamDefinition;
import codereview.chatsys.service.IConferenceService;
import codereview.chatsys.service.IPersonalDataService;

import com.google.inject.Inject;

/**
 * @author yuta uehara
 *
 */
@Log4j
public abstract class ParentPage extends WebPage {

	


	/**
	 *
	 * 新たにページを追加した時は次の情報を入れる事
	 *
	 * [ページ要素名(基本的にJavaのファイル名)]("ページの日本語名","ページの日本語名(短縮版)",Javaのファイル名.class,
	 * 一つ上のページ要素名)
	 *
	 * @author yuta uehara
	 *
	 */

	// make Google selected by default
	private String selected;

	private List<String> conferenceListString;

	// GetSelectConferenceData
	@Inject
	IConferenceService cservice;

	@Inject
	IPersonalDataService personalService;



	private String info_accountNameString;
	private String info_userTypeString;
	private String info_conferenceNameString;
	private String info_genUserAuthrityString;

	private String navibar1LinkString;
	private Class<? extends ParentPage> navibar1LinkClass;
	private String navibar1IconString = "glyphicon glyphicon-home";
	private boolean navibar1Enable = true;

	private Class<? extends ParentPage> navibar2LinkClass;
	private String navibar2LinkString;
	private String navibar2IconString = "glyphicon glyphicon-user";
	private boolean navibar2Enable = true;

	private String navibar3LinkString;
	private Class<? extends ParentPage> navibar3LinkClass;
	private String navibar3IconString = "glyphicon glyphicon-user";
	private boolean navibar3Enable = true;

	private Class<? extends ParentPage> navibar4LinkClass;
	private String navibar4LinkString;
	private String navibar4IconString = "glyphicon glyphicon-off";
	private boolean navibar4Enable = true;



	private boolean enableSidebar = false;

	private String titleNameString;


	// コンストラクタ
	/**
	 * @param crumbLastString
	 *            現在のページの値、パンくずリストの最後に表示される
	 * @param crumbPagelist
	 *            　パンくずリスト
	 * @param enableSidebar
	 *            　サイドバーを有効にする場合はtrue
	 * @param sidebarPattern
	 *            サイドバーを有効にしない場合はfalse
	 */
	protected ParentPage(PageParameters param) {
		super();

	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forUrl("js/bootstrap.min.js"));
		response.render(CssHeaderItem.forUrl("./css/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./css/my-bootstrap.css"));
		response.render(CssHeaderItem.forUrl("./css/my-customize.css"));
	}

	// タイトル・見出し文言を取得
	protected abstract String getTitle();

}