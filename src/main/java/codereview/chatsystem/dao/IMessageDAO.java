package codereview.chatsystem.dao;

import java.sql.SQLException;
import java.util.List;

import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.definition.MessageType;

import com.google.inject.ImplementedBy;

@ImplementedBy(MessageDAO.class)
public interface IMessageDAO {
	/**
	 * メッセージbeanを挿入
	 * @param mbean
	 * @throws SQLException
	 */
	public int insertMessage(MessageBean mbean) throws SQLException;
	/**
	 * 全メッセージを取得
	 * @return
	 * @throws SQLException
	 */
	public List<MessageBean> selectAllMessage() throws SQLException;
	
	/**
	 * このアカウントのメッセージか
	 * @param message_id
	 * @param account_id
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public boolean isThisAccountMessage(int message_id, int account_id)throws SQLException,NullPointerException;
}
