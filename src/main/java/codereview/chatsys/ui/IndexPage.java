package codereview.chatsys.ui;

import java.util.List;


import lombok.extern.log4j.Log4j;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

/**
 * @see jp.ac.chitose.ILoginService
 * @author Yuta Uehara
 * @author Hiroto Yamakawa
 */
@Log4j
public class IndexPage extends ParentPage {


	private static final long serialVersionUID = 1032132669390389697L;
//	@Inject
//	IConferenceService conferecenService;
	@Override
	protected String getTitle() {
		//TODO　ParentPage.javaを編集してください
		return "";
	}

	public IndexPage() {
		//TODO PageParameterがある場合は番号を設定すること
		super(null);
//		this.add(new Link<String>("toConferenceInsertPagePageHtml") {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onClick() {
//				this.setResponsePage(ConferenceInsertPage.class);
//			}
//		});
//		
//		this.add(new Link<String>("toAdminConferenceContentPageHtml") {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onClick() {
//				this.setResponsePage(AdminConferenceContentPage.class);
//			}
//		});
////		this.add(new Link<String>("toAdminConferenceMailPageHtml") {
////			private static final long serialVersionUID = 1L;
////
////			@Override
////			public void onClick() {
////				this.setResponsePage(AdminConferenceMailPage.class);
////			}
////		});
//		this.add(new Link<String>("toAccountListPageHtml") {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onClick() {
//				this.setResponsePage(AccountListPage.class);
//			}
//		});
//
//		IModel<List<ConferenceBean>> tableModel = new ListModel<>(getConferenceList());
//		ListView<ConferenceBean> tableView = new ListView<ConferenceBean>("table",tableModel) {
//
//			protected void populateItem(ListItem<ConferenceBean> item) {
//				Label titleLabel = new Label("title", item.getModel().getObject().getCaption());
//				Link linkLabel = new Link("toIndividualTopPageHTML") {
//					private static final long serialVersionUID = -8351519148902316402L;
//					@Override
//					public void onClick() {
//						int cid = item.getModel().getObject().getConference_id();
//						MySession.get().getConferenceEntryBean().setConference_id(cid);	
//						this.setResponsePage(new IndividualTopPage());
//					}
//				};
//				item.add(linkLabel);
//				linkLabel.add(titleLabel);
//			}
//
//		};
//		add(tableView);



	}


//
//	public List<ConferenceBean> getConferenceList(){
//		List<ConferenceBean> conlist = conferecenService.getAllConferenceBeanList();
//		//log.info(conlist.get(1).getConference_id());
//		return conlist;
//
//	}



}