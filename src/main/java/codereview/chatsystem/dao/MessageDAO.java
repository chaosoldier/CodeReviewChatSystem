package codereview.chatsystem.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import codereview.chatsystem.IDBCP;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.PersonalBean;
import lombok.extern.log4j.Log4j;

import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author vicugna
 *
 */
@Log4j
public class MessageDAO implements IMessageDAO {

	@Inject
	private IDBCP dbcp;

	private List<PersonalBean> personalBean;

	@Override
	public int insertMessage(MessageBean mbean) throws SQLException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "insert into message(account_id, type, body, insert_at, check_available)"
					+ "values(:account_id, :type, :body, :insert_at, :check_available) RETURNING message_id";
			return con.createQuery(sql)
					.addParameter("account_id", mbean.getAccount_id())
					.addParameter("type", mbean.getType().toString())
					.addParameter("body", mbean.getBody())
					.addParameter("insert_at", mbean.getInsert_at())
					.addParameter("check_available", mbean.isCheck_available())
					.executeScalar(Integer.class);
		}

	}

	@Override
	public List<MessageBean> selectAllMessage() throws SQLException {
		List<MessageBean> list = new ArrayList<MessageBean>();
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "SELECT * FROM message";
			list = con.createQuery(sql).executeAndFetch(MessageBean.class);
		}
		return list;
	}

	@Override
	public boolean isThisAccountMessage(int message_id, int account_id)
			throws SQLException, NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select message_id from message where message_id = :message_id and account_id = :account_id";
			if (con.createQuery(sql).addParameter("message_id", message_id)
					.addParameter("account_id", account_id)
					.executeScalar(Integer.class) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}
