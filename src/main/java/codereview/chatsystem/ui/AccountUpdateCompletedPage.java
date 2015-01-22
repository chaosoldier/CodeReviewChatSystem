package codereview.chatsystem.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import com.google.inject.Inject;

@AuthorizeInstantiation({Roles.USER})
public class AccountUpdateCompletedPage extends WebPage{

	private static final long serialVersionUID = 1032132669390389697L;


	public AccountUpdateCompletedPage(
			int personal_id,
			String newName,
			String newRoman_name,
			String newOrganization,
			String newReceipt_name){

//
//		PersonalDataService.updatePersnalData(personal_id,
//				PersonalDataService.getPersonalBean(personal_id).getConference_entry_id(),
//				newName,
//				newRoman_name,
//				newOrganization,
//				newReceipt_name);

		this.add(new Link<String>("toAdminPageHtml") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(IndexPage.class);
			}
		});
	}
}