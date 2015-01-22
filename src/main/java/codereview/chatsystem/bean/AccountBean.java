package codereview.chatsystem.bean;

import java.io.Serializable;

import lombok.Value;
import codereview.chatsystem.definition.Encrypter;

/**
 * accountテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class AccountBean implements Serializable{
	private static final long serialVersionUID = 1215912304612055644L;
	private int account_id;
	private String email_address;
	private boolean available;
	public AccountBean(int account_id, String email_address, boolean available) {
		super();
		this.account_id = account_id;
		this.email_address = email_address;
		this.available = available;
	}
	

}
