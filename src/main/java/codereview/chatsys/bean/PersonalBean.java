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
	private static final long serialVersionUID = -3853277355815929746L;
	private int personal_id;
	private int account_id;
	private String name;
	private String roman_name;
	/**
	 * アバターを入れるためのフィールド
	 */
	private byte[] data;
	private String file_name;
	private Timestamp inserted_at;
	private Timestamp updated_at;
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