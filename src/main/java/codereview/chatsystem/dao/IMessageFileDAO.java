package codereview.chatsystem.dao;

import java.sql.SQLException;

import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.MessageFileBean;
import codereview.chatsystem.definition.MessageType;

import com.google.inject.ImplementedBy;

@ImplementedBy(MessageFileDAO.class)
public interface IMessageFileDAO {
	/**
	 * メッセージの添付ファイルをbeanで挿入
	 * @param mfbean
	 * @throws SQLException
	 */
	public void insertMessageFile(MessageFileBean mfbean) throws SQLException;
}
