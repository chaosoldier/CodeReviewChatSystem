package codereview.chatsys.bean;

import java.io.Serializable;

import lombok.Value;
import codereview.chatsys.definition.Encrypter;

/**
 * accountテーブルに対応したBean
 * @author vicugna
 *
 * コメントを追加 by user340
 * 
 */
@Value
public class AccountBean implements Serializable{
	private static final long serialVersionUID = 1215912304612055644L;	/* serialVersionUID */
	private int account_id;		/* アカウントID */
	private String email_address;	/* メールアドレス */
	private String passphrase;	/* パスワード */
	private Encrypter TYPE;		/* 暗号化関係の変数？ */
	private boolean available;	/* システムの利用が可能なユーザかどうかを判別するための変数？ */
	/*
	 * ユーザの追加？定義？
	 */
	public AccountBean(int account_id, String email_address, String passphrase,
			Encrypter tYPE, boolean available) {
		super();
		this.account_id = account_id;
		this.email_address = email_address;
		this.passphrase = passphrase;
		TYPE = tYPE;
		this.available = available;
	}
	

}
