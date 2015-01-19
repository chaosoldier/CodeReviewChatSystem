package codereview.chatsys.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import com.google.inject.Inject;


/**
 * @author yuta uehara
 * @author shibata harunori
 *
 */

@AuthorizeInstantiation({Roles.ADMIN})
public class AccountUpdatePage extends ParentPage{

	private static final long serialVersionUID = 1032132669390389697L;

	private IModel<String> nameModel;
	private IModel<String> roman_nameModel;
	private IModel<String> groupModel;
	private IModel<String> receipt_nameModel;



	@Override
	protected String getTitle() {
		return "";
	}


	public AccountUpdatePage(int personal_id){

		super(null);

		nameModel = new Model<>();
		roman_nameModel = new Model<>();
		groupModel = new Model<>();
		receipt_nameModel = new Model<>();

		Form<Void> form = new Form<Void>("toAccountUpdateCheckPage") {

			private static final long serialVersionUID = 6131985258676512577L;

				@Override
			public void onSubmit() {
					super.onSubmit();
					setResponsePage(new AccountUpdateCheckPage(
							personal_id,
							nameModel,
							roman_nameModel,
							groupModel,
							receipt_nameModel));

			}
		};
		form.add(new TextField<String>("newName", nameModel));
		form.add(new TextField<String>("newRoman_name", roman_nameModel));
		form.add(new TextField<String>("newOrganization", groupModel));
		form.add(new TextField<String>("newReceipt_name",receipt_nameModel));

		add(form);

//		this.add(new Link<String>("toAccountUpdateCheckPageHtml") {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onClick() {
//				this.setResponsePage(AccountUpdateCheckPage.class);
//			}
//		});

	}
}