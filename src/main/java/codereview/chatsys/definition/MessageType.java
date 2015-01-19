package codereview.chatsys.definition;

/**
 * messageテーブルのメッセージタイプカラムに相当するenum。REPLYは一階層のみ。
 * @author vicugna
 *
 */
public enum MessageType {
	/**
	 * メッセージの親
	 */
	MAIN,
	/**
	 * 親に対する返信
	 */
	REPLY;
	private MessageType TYPE;
	public void setMessageType(MessageType TYPE){
		this.TYPE = TYPE;
	}
	public MessageType getMessageType(){
		return this.TYPE;
	}
}
