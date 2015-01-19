package codereview.chatsys.ui;



import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;


/**
 * @author yuta uehara
 * @author shibata harunori
 *
 */

@AuthorizeInstantiation({Roles.ADMIN})
public class AccountUpdateCheckPage extends ParentPage{

	private static final long serialVersionUID = 1032132669390389697L;



	@Override
	protected String getTitle() {
		return "アカウント更新";
	}

	public AccountUpdateCheckPage(
			int account_id,
			IModel<String> newName,
			IModel<String> newRoman_name,
			IModel<String> newOrganization,
			IModel<String> newReceipt_name){

		super(null);
//
//		PersonalBean personal=null;
//		personal=PersonalDataService.getPersonalBean(account_id);
//		String email_address=ConferenceEntryService.selectConferenceEntryBean(personal.getConference_entry_id()).getEmail_address();
//
//		Label nameLabel=new Label("oldName",new Model<>(personal.getName()));
//		Label roman_nameLabel=new Label("oldRoman_name",new Model<>(personal.getRoman_name()));
//		Label organizationLabel=new Label("oldOrganization",new Model<>(personal.getOrganization()));
//		Label receipt_nameLabel=new Label("oldReceipt_name",new Model<>(personal.getReceipt_name()));
//
//		if(newName.getObject()==null){
//			newName=new Model<>(personal.getName());
//		}
//		if(newRoman_name.getObject()==null){
//			newRoman_name=new Model<>(personal.getRoman_name());
//		}
//		if(newOrganization.getObject()==null){
//			newOrganization=new Model<>(personal.getOrganization());
//		}
//		if(newReceipt_name.getObject()==null){
//			newReceipt_name=new Model<>(personal.getReceipt_name());
//		}
//
//		final int finalPersonal_id=account_id;
//		final String finalName=newName.getObject();
//		final String finalRoman_name=newRoman_name.getObject();
//		final String finalOrganization=newOrganization.getObject();
//		final String finalReceipt_name=newReceipt_name.getObject();
//
//
//		Label newNameLabel=new Label("newName",newName);
//		Label newRoman_nameLabel=new Label("newRoman_name",newRoman_name);
//		Label newOrganizationLabel=new Label("newOrganization",newOrganization);
//		Label newReceipt_nameLabel=new Label("newReceipt_name",newReceipt_name);
//
//
//		add(nameLabel);
//		add(roman_nameLabel);
//		add(organizationLabel);
//		add(receipt_nameLabel);
//
//		add(newNameLabel);
//		add(newRoman_nameLabel);
//		add(newOrganizationLabel);
//		add(newReceipt_nameLabel);
//
//		Form<Void> form = new Form<Void>("buttonForm");
//
//		Button button1 = new Button("toAccountUpdateCompletedPage") {
//
//			@Override
//			public void onSubmit() {
//				setResponsePage(new AccountUpdateCompletedPage(finalPersonal_id,
//						finalName,
//						finalRoman_name,
//						finalOrganization,
//						finalReceipt_name));
//			}
//		};
//		Button button2 = new Button("toAccountUpdatePage") {
//			@Override
//			public void onSubmit() {
//				setResponsePage(new AccountUpdatePage(finalPersonal_id));
//			}
//		};
//		add(form);
//		form.add(button1);
//		form.add(button2);
	}
}

