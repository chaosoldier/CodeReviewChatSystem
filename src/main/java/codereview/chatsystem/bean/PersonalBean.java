package codereview.chatsystem.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;


/**
 * personalテーブルに対応したBean
 * @author vicugna
 *
 */
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
	public int getPersonal_id() {
		return personal_id;
	}
	public void setPersonal_id(int personal_id) {
		this.personal_id = personal_id;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoman_name() {
		return roman_name;
	}
	public void setRoman_name(String roman_name) {
		this.roman_name = roman_name;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Timestamp getInserted_at() {
		return inserted_at;
	}
	public void setInserted_at(Timestamp inserted_at) {
		this.inserted_at = inserted_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	
}