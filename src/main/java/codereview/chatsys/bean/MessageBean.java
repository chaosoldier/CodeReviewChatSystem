package codereview.chatsys.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Value;
import codereview.chatsys.definition.MessageType;
/**
 * messageテーブルに対応したBean
 * @author vicugna
 *
 */
@Value
public class MessageBean implements Serializable{
	private static final long serialVersionUID = -7019870973585985052L;	/* serialVersionUID */
	private int message_id;		/* メッセージのID */
	private int account_id;		/* アカウントID */
	private MessageType type;	/* メッセージの種類 */
	private String body;		/* メッセージの中身そのもの */
	private Timestamp inserted_at;	/* 投稿時刻を表す変数 */
	private boolean check_available;	/* おしえて誰か */
	
	/*
	 * メッセージの定義
	 */
	public MessageBean(int message_id, int account_id, MessageType type,
			String body, Timestamp inserted_at, boolean check_available) {
		super();
		this.message_id = message_id;
		this.account_id = account_id;
		this.type = type;
		this.body = body;
		this.inserted_at = inserted_at;
		this.check_available = check_available;
	}
	

}
