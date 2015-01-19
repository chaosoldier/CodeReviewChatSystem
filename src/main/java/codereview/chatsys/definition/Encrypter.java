package codereview.chatsys.definition;


/**
 * パスワードの暗号化アルゴリズムの指定を行うためのenum
 * @author vicunga
 *
 */
public enum Encrypter {
	TYPE1,
	/**
	 * ストレッチを20000回行うバージョン
	 */
	TYPE2;
	private Encrypter TYPE;
	public void setEncrypter(Encrypter TYPE){
		this.TYPE = TYPE;
	}
	public Encrypter getEncrypter(){
		return this.TYPE;
	}
}
