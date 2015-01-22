package codereview.chatsystem.service;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import codereview.chatsystem.bean.CheckMessageBean;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.MessageFileBean;
import codereview.chatsystem.bean.ReadMessageBean;

import com.google.inject.ImplementedBy;

/**
 * @author Yuta Uehara
 *
 */
@ImplementedBy(MessageService.class)
public interface iMessageService {
	/**
	 * メッセージbeanを挿入
	 * @param mbean
	 * @return if false return false
	 */
	public int setMessage(MessageBean mbean);
	/**
	 * メッセージの添付ファイルをbeanで挿入
	 * @param mfbean
	 * @return  if false return false
	 */
	public boolean setMessageFile(MessageFileBean mfbean);
	/**
	 * 全メッセージを取得
	 * @return if false return null
	 */
	public List<MessageBean> getAllMessageBeans();
	/**
	 * 既読メッセージのbeanリストからSQL発行（ユニーク制約済みであるため、そのまま挿入可能）
	 * @param rmbean
	 */
	public void setReadMessage(int account_id);
	/**
	 * メッセージの既読数を取得
	 * @param message_id
	 * @return if excetion return 0, if count 0 return 0
	 */
	public int countReadPerMessage(int message_id);
	/**
	 * 確認メッセージbeanを挿入（ユニーク制約済みであるため、そのまま挿入可能）
	 * @param cmbean
	 */
	public void setCheckMessage(CheckMessageBean cmbean);
	/**
	 * メッセージの確認数を取得
	 * @param message_id
	 * @return if excetion return 0, if count 0 return 0
	 */
	public int countCheckPerMessage(int message_id);
	
	/**
	 * メッセージは確認済みであるか
	 * @param message_id
	 * @param account_id
	 * @return 例外時もfalse
	 */
	public boolean isCheckedMessage(int message_id, int account_id);
	/**
	 * メッセージはログイン中の人のものであるか
	 * @param message_id
	 * @param account_id
	 * @return
	 */
	public boolean isThisAcountMessage(int message_id, int account_id);
}