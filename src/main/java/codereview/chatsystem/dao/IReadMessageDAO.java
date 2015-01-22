package codereview.chatsystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.ReadMessageBean;
import codereview.chatsystem.definition.MessageType;

import com.google.inject.ImplementedBy;

@ImplementedBy(ReadMessageDAO.class)
public interface IReadMessageDAO {
	/**
	 * 既読メッセージbeanを挿入（ユニーク制約済みであるため、そのまま挿入可能）
	 * @param rmbean
	 * @throws SQLException
	 */
	public void insertReadMessage(ReadMessageBean rmbean) throws PSQLException,SQLException,NullPointerException;
//	/**
//	 * 全既読メッセージを取得
//	 * @return
//	 * @throws SQLException
//	 */
//	public List<ReadMessageBean> selectAllReadMessage() throws SQLException;
	/**
	 * メッセージの既読数を取得
	 * @param message_id
	 * @return
	 * @throws SQLException,NullPointerException
	 */
	public int countReadPerMessage(int message_id) throws SQLException,NullPointerException;
}
