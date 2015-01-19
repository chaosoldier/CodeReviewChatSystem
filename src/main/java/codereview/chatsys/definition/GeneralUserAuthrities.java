package codereview.chatsys.definition;

public enum GeneralUserAuthrities {
		// 特定学会での主催者
		ORGANIZER("管理者", 3),
		// 特定学会での講演者
		PRESENTER("講演者", 2),
		// 特定学会での聴講者
		AUDITOR("聴講者", 1);
		private final String name;
		private final int num;

		/**
		 * コンストラクタを設定しているだけ
		 *
		 * @param name
		 * @param num
		 */
		GeneralUserAuthrities(String name, int num) {
			this.name = name;
			this.num = num;
		}
		/**
		 * @return 一般ユーザ権限判定用の数値を返す
		 */
		public int toIntValue() {
			return this.num;
		}

}
