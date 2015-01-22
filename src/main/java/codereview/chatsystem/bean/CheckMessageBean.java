package codereview.chatsystem.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;

/**
 * check_messageテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class CheckMessageBean implements Serializable {
	private static final long serialVersionUID = 8754317337708755261L;
	private int check_message_id;
	private int message_id;
	private int account_id;
	private Timestamp inserted_at;
	public CheckMessageBean(int message_id, int account_id,
			Timestamp inserted_at) {
		super();
		this.check_message_id = 0;
		this.message_id = message_id;
		this.account_id = account_id;
		this.inserted_at = inserted_at;
	}
}
