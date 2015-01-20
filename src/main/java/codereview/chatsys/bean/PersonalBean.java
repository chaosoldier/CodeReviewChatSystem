package codereview.chatsys.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;


/**
 * personalテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class PersonalBean implements Serializable {
	private static final long serialVersionUID = -3853277355815929746L;		/* serialVersionUID */
	private int personal_id;	/* ユーザのアイコンのID */
	private int account_id;		/* ユーザアカウントのID */
	private String name;		/* ユーザの名前(あやしい) */
	private String roman_name;	/* ユーザのアイコンの名前？(あやしい) */
	/**
	 * アバターを入れるためのフィールド
	 */
	private byte[] data;
	private String file_name;	/* 誰かおしえて */
	private Timestamp inserted_at;	/* アイコンを登録したときの時刻を表す変数 */
	private Timestamp updated_at;	/* アイコン画像を変えたときの時刻を表す変数 */
	/*
	 * アバターの定義
	 */
	public PersonalBean(int personal_id, int account_id, String name,
			String roman_name, byte[] data, String file_name,
			Timestamp inserted_at, Timestamp updated_at) {
		super();
		this.personal_id = personal_id;
		this.account_id = account_id;
		this.name = name;
		this.roman_name = roman_name;
		this.data = data;
		this.file_name = file_name;
		this.inserted_at = inserted_at;
		this.updated_at = updated_at;
	}
	
}
