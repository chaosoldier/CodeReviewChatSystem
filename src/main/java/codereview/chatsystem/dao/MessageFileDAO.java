package codereview.chatsystem.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import codereview.chatsystem.IDBCP;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.MessageFileBean;
import codereview.chatsystem.bean.PersonalBean;
import lombok.extern.log4j.Log4j;

import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author vicugna
 *
 */
@Log4j
public class MessageFileDAO implements IMessageFileDAO {


	@Inject
	private IDBCP dbcp;

	private List<PersonalBean> personalBean;
	@Override
	public void insertMessageFile(MessageFileBean mfbean) throws SQLException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "insert into message_file(message_id, data, file_name)" 
			+ "values(:message_id, :data, :file_name)";
			con.createQuery(sql)
			.addParameter("message_id",mfbean.getMessage_id())
			.addParameter("data",mfbean.getData())
			.addParameter("file_name",mfbean.getFile_name()).executeUpdate();
		}
		
	}
}
