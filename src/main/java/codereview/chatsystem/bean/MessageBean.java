package codereview.chatsystem.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;
import codereview.chatsystem.definition.MessageType;
/**
 * messageテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class MessageBean implements Serializable{
	private static final long serialVersionUID = -7019870973585985052L;
	private int message_id;
	private int account_id;
	private MessageType type;
	private String body;
	private Timestamp insert_at;
	private boolean check_available;
	public MessageBean(int account_id, MessageType type,
			String body, Timestamp insert_at, boolean check_available) {
		super();
		this.message_id = 0;
		this.account_id = account_id;
		this.type = type;
		this.body = body;
		this.insert_at = insert_at;
		this.check_available = check_available;
	}
	

}
