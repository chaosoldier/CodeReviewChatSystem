package codereview.chatsystem.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.PasswordField;
import lombok.extern.log4j.Log4j;

import org.apache.wicket.markup.html.image.resource.*;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import sun.util.logging.resources.logging;
import codereview.chatsystem.bean.PersonalBean;
import codereview.chatsystem.service.ILoginService;
import codereview.chatsystem.service.IPersonalService;

import com.google.inject.Inject;

/**
 * @author yuta uehara
 * @author shibata harunori
 *
 */

 @AuthorizeInstantiation({Roles.USER})
@Log4j
public class AccountUpdatePage extends WebPage {

	private static final long serialVersionUID = 1032132669390389697L;

	private IModel<String> nameModel;
	private IModel<String> roman_nameModel;
	private IModel<String> groupModel;
	private IModel<String> receipt_nameModel;

	@Inject
	IPersonalService iPersonalService;

	@Inject
	ILoginService iLoginService;

	private int accountId;

	private String avatarFilePath;

	private Model<String> passwordModel;

	private Model<String> passwordREModel;
	
	private Form<Void> imageUpdateForm;

	// @Override
	// protected String getTitle() {
	// return "";
	// }

	public AccountUpdatePage() {

		//初期化
		accountId = MySession.get().getMyAccountBean().getAccount_id();
		PersonalBean personalBean = iPersonalService.getPersonalBean(accountId);

		
		avatarFilePath = "/contents/avatar/tmp" + String.valueOf(accountId)
				+ personalBean.getFile_name();
		byte[] binary = personalBean.getData();
		File fos = new File(avatarFilePath);
		try {
			FileOutputStream output = new FileOutputStream(fos);
			output.write(binary);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//名前の表示
		this.add(new Label("nameHtml",personalBean.getName()));
		//Resource image
		this.add(new Image("hoge", new DynamicImageResource() {
			@Override
			protected byte[] getImageData(Attributes attributes) {
				return personalBean.getData();
			}
		}));


		
		//プロフィール画像の変更
		final FileUploadField uploadField = new FileUploadField("fileUploadHtml");
		imageUpdateForm = new Form<Void>("toAccountUpdateCompletePage") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit() {
				//TODO 画像サイズの確認、MIME確認
				try {
					InputStream stream = uploadField.getFileUpload().getInputStream();
					byte[] bytes = readAll(stream);//stream 書き込み処理
					iPersonalService.updateAvatar(accountId, bytes, uploadField.getFileUpload().getClientFileName());
//					setResponsePage(AccountUpdateCompletedPage.class);
				} catch(IOException e) {
					e.printStackTrace();
				}
				//setResponsePage(new LecturerAcceptCheckPage());
			}
		};
		imageUpdateForm.setMultiPart(true);
		imageUpdateForm.add(uploadField);
		this.add(imageUpdateForm);

		//パスワード情報の変更
		passwordModel = new Model<>();
		passwordREModel = new Model<>();
		Form<Void> passForm = new Form<Void>("passwordUpdateFormHtml") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				log.info(passwordModel.getObject());
				if(passwordModel.getObject().equals(passwordREModel.getObject())){
					iLoginService.updatePassword("abc@abc.com", passwordModel.getObject());
				}else{
					//TODO feedback needed
				}
			}
		};
		passForm.add(new PasswordTextField("pass", passwordModel));
		passForm.add(new PasswordTextField("repass", passwordREModel));
		this.add(passForm);

		

		nameModel = new Model<>();
		roman_nameModel = new Model<>();
		groupModel = new Model<>();
		receipt_nameModel = new Model<>();

		Form<Void> form = new Form<Void>("toAccountUpdateCheckPage") {
			private static final long serialVersionUID = 6131985258676512577L;

			@Override
			public void onSubmit() {
				super.onSubmit();
			}
		};
		form.add(new TextField<String>("newName", nameModel));
		form.add(new TextField<String>("newRoman_name", roman_nameModel));
		form.add(new TextField<String>("newOrganization", groupModel));
		form.add(new TextField<String>("newReceipt_name", receipt_nameModel));
		
		this.add(new Link<Void>("toLogoutPageHtml") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(LogoutPage.class);
			}
		});
		this.add(new Link<Void>("toIndexPageHtml") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.setResponsePage(IndexPage.class);
			}
		});

		// add(form);

		// this.add(new Link<String>("toAccountUpdateCheckPageHtml") {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void onClick() {
		// this.setResponsePage(AccountUpdateCheckPage.class);
		// }
		// });

	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem
				.forUrl("./html/css/vendor/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("./html/css/flat-ui.css"));
		response.render(CssHeaderItem.forUrl("./html/css/demo.css"));
	}
	//バイト変換
	public byte[] readAll(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte [] buffer = new byte[1024];
	    while(true) {
	        int len = inputStream.read(buffer);
	        if(len < 0) {
	            break;
	        }
	        bout.write(buffer, 0, len);
	    }
	    return bout.toByteArray();
	}
}