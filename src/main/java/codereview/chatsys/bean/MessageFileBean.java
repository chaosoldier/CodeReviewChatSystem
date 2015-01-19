package codereview.chatsys.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;


/**
 * message_fileテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class MessageFileBean implements Serializable {
	private static final long serialVersionUID = -6621182091682997484L;
	private int message_file_id;
	private int message_id;
	/**
	 * メッセージに関連付けされたファイルを入れるためのフィールド
	 */
	private byte[] data;
	private String file_name;
	public MessageFileBean(int message_file_id, int message_id, byte[] data,
			String file_name) {
		super();
		this.message_file_id = message_file_id;
		this.message_id = message_id;
		this.data = data;
		this.file_name = file_name;
	}
	
	
}