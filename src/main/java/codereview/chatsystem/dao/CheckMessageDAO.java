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
import codereview.chatsystem.bean.CheckMessageBean;
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
public class CheckMessageDAO implements ICheckMessageDAO {


	@Inject
	private IDBCP dbcp;

	@Override
	public void insertCheckMessage(CheckMessageBean cmbean)
			throws PSQLException, SQLException, NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "insert into check_message(message_id, account_id, inserted_at)" 
			+ "values(:message_id, :account_id,:inserted_at)";
			con.createQuery(sql)
			.addParameter("message_id",cmbean.getMessage_id())
			.addParameter("account_id",cmbean.getAccount_id())
			.addParameter("inserted_at",cmbean.getInserted_at())
			.executeAndFetchFirst(CheckMessageBean.class);
		}		
	}
	@Override
	public int countCheckPerMessage(int message_id) throws SQLException,
			NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select count(*) from check_message where message_id = :message_id";
			return con.createQuery(sql).addParameter("message_id",message_id).executeScalar(Integer.class);
		}
	}
	@Override
	public boolean isCheckedMessage(int message_id, int account_id)
			throws PSQLException, SQLException, NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
		String sql = "select check_message_id from check_message where message_id = :message_id and account_id = :account_id";
		if(con.createQuery(sql)
				.addParameter("message_id",message_id)
				.addParameter("account_id",account_id).executeScalar(Integer.class)>0){
			return true;
		}else{
			return false;
		}
		}

	}
}
