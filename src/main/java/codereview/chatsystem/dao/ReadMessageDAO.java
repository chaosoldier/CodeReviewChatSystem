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
import codereview.chatsystem.bean.ReadMessageBean;
import lombok.extern.log4j.Log4j;

import org.postgresql.util.PSQLException;
import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author vicugna
 *
 */
@Log4j
public class ReadMessageDAO implements IReadMessageDAO {


	@Inject
	private IDBCP dbcp;

	private List<PersonalBean> personalBean;
	@Override
	public void insertReadMessage(ReadMessageBean rmbean) throws PSQLException,SQLException ,NullPointerException{
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "insert into read_message(message_id, account_id, inserted_at)" 
			+ "values(:message_id, :account_id,:inserted_at)";
			con.createQuery(sql)
			.addParameter("message_id",rmbean.getMessage_id())
			.addParameter("account_id",rmbean.getAccount_id())
			.addParameter("inserted_at",rmbean.getInserted_at())
			.executeAndFetchFirst(ReadMessageBean.class);
		}
		
		
	}
//	@Override
//	public List<ReadMessageBean> selectAllReadMessage() throws SQLException {
//		List<ReadMessageBean> list = new ArrayList<ReadMessageBean>();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT * FROM read_message";
//			list = con.createQuery(sql).executeAndFetch(ReadMessageBean.class);
//		}
//		return list;
//	}
	@Override
	public int countReadPerMessage(int message_id) throws SQLException,
			NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select count(*) from read_message where message_id = :message_id";
			return con.createQuery(sql).addParameter("message_id",message_id).executeScalar(Integer.class);
		}
	}
}
