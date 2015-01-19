package codereview.chatsys.definition;

/**
 * @author yuta uehara
 *
 */
public enum PageParamDefinition {
	
	/**
	 * システム管理者用
	 */
	SYSTEMADMINSIGNIN("login35"),
	
	/**
	 * ページ見つからなかった時用のデフォルト値
	 */
	NOTFOUNDPAGE("404");
	private String nameString;
	PageParamDefinition(String nameString){
		this.nameString = nameString;
	}
	public String toString(){
		return this.nameString;
	}

}
