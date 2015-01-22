package codereview.chatsystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import codereview.chatsystem.bean.CheckMessageBean;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.ReadMessageBean;
import codereview.chatsystem.definition.MessageType;

import com.google.inject.ImplementedBy;

@ImplementedBy(CheckMessageDAO.class)
public interface ICheckMessageDAO {
	/**
	 * 確認メッセージbeanを挿入（ユニーク制約済みであるため、そのまま挿入可能）
	 * @param cmbean
	 * @throws SQLException
	 */
	public void insertCheckMessage(CheckMessageBean cmbean) throws PSQLException,SQLException,NullPointerException;
	/**
	 * 確認済みであるかどうか
	 * @param message_id
	 * @param account_id
	 * @return
	 * @throws PSQLException
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public boolean isCheckedMessage(int message_id, int account_id) throws PSQLException,SQLException,NullPointerException;

	/**
	 * メッセージの確認数を取得
	 * @param message_id
	 * @return
	 * @throws SQLException,NullPointerException
	 */
	public int countCheckPerMessage(int message_id) throws SQLException,NullPointerException;
}
