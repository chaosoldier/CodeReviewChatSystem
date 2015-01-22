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
import codereview.chatsystem.bean.PersonalBean;
import lombok.extern.log4j.Log4j;

import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author vicugna
 *
 */
@Log4j
public class PersonalDAO implements IPersonalDAO {


	@Inject
	private IDBCP dbcp;

	private List<PersonalBean> personalBean;

	@Override
	public void insertpaper() throws SQLException,NullPointerException, IOException {
		//boolean activeFlag = false;
		int account_id = 3;
		String file_name = "default.jpg";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		FileInputStream fis = new FileInputStream("/contents/avatar/tora.jpg");
		FileChannel channel = fis.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
		channel.read(buffer);
		buffer.clear();
		byte[] bytes = new byte[buffer.capacity()];
		buffer.get(bytes);
		channel.close();
		fis.close();
		try (Connection con = dbcp.getSql2o().open()) {
			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
			//String insertSql = "insert into personal(name,name_pronunciation,belong_organization,organization) " + "values (:userid, :name, :name_p, :b_organization, :organization)";
			String sql = "update personal set data = :data, updated_at = :updated_at, file_name = :file_name" 
			+ " where account_id = :account_id";
			con.createQuery(sql)
			.addParameter("account_id",account_id)
			.addParameter("data",bytes)
			.addParameter("updated_at",timestamp)
			.addParameter("file_name",file_name)
			.executeUpdate();
		}
	}

	@Override
	public void insertpaper(int publication_id,byte[] data,Timestamp updata_at,String caption,String summary,String dataname) throws SQLException,NullPointerException, IOException {
		//boolean activeFlag = false;
		/*int index = 1;
		String caption = "test";
		String summary = "test2";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		FileInputStream fis = new FileInputStream("c:/test.pdf");
		FileChannel channel = fis.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
		channel.read(buffer);
		buffer.clear();
		byte[] bytes = new byte[buffer.capacity()];
		buffer.get(bytes);
		channel.close();
		fis.close();*/
		boolean available = false;
		try (Connection con = dbcp.getSql2o().open()) {
			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
			//String insertSql = "insert into personal(name,name_pronunciation,belong_organization,organization) " + "values (:userid, :name, :name_p, :b_organization, :organization)";
			String sql = "insert into paper(publication_id, data, updata_at, caption, summary,dataname,)" + "values(:publication_id, :data, :updata_at, :caption, :summary, :available)";
			con.createQuery(sql)
			.addParameter("publication_id",publication_id)
			.addParameter("data",data)
			.addParameter("updata_at",updata_at)
			.addParameter("caption",caption)
			.addParameter("summary",summary)
			.addParameter("dataname",dataname)
			.addParameter("available",available).executeUpdate();
		}
	}

	@Override
	public void serectpaper() throws SQLException,NullPointerException, IOException {
		//boolean activeFlag = false;
		int id = 4;
		try (Connection con = dbcp.getSql2o().open()) {
			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
			//String insertSql = "insert into personal(name,name_pronunciation,belong_organization,organization) " + "values (:userid, :name, :name_p, :b_organization, :organization)";
			String sql = "select data from paper where paper_id = :id";
			byte[] binary = con.createQuery(sql).addParameter("id",id).executeScalar(byte[].class);

			//log.info(binary);



			//byte[] bytes = binary.getBytes();

			FileOutputStream fos = new FileOutputStream("result.pdf");
			fos.write(binary);
			fos.close();
		}
	}

	@Override
	public PersonalBean selectPersonal(int accountId) throws SQLException,IOException{
		//created by vicugna
		PersonalBean bean = null;
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select * from personal where account_id = :id";
			bean = con.createQuery(sql).addParameter("id",accountId).executeAndFetchFirst(PersonalBean.class);

		} catch(SQLException e){
			e.printStackTrace();
		}

		return bean;
	}

	@Override
	public List<PersonalBean> SelectAllPaper() {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select * from paper";
			personalBean = con.createQuery(sql).executeAndFetch(PersonalBean.class);

		} catch(SQLException e){
			e.printStackTrace();
		}

		return personalBean;
	}

	@Override
	public void DeleteAllPaper(int id) {

		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "delete from paper where paper_id = :id";
			con.createQuery(sql).addParameter("id",id).executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();
		}
	}



	@Override
	public void updateAvailablePaper(int paper_id)
			throws SQLException, NullPointerException {
		boolean available = true;
		String sql = "update paper set available = :available where paper_id = :paper_id";
		try(Connection con = dbcp.getSql2o().open()){
			con.createQuery(sql).addParameter("available",available)
			.addParameter("paper_id",paper_id).executeUpdate();
		}
	}


	@Override
	public void updateAvatar(int account_id, byte[] data, Timestamp updated_at,
			String file_name) throws SQLException, NullPointerException {
//		FileInputStream fis = new FileInputStream("/contents/avatar/tora.jpg");
//		FileChannel channel = fis.getChannel();
//		ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
//		channel.read(buffer);
//		buffer.clear();
//		byte[] bytes = new byte[buffer.capacity()];
//		buffer.get(bytes);
//		channel.close();
//		fis.close();
		try (Connection con = dbcp.getSql2o().open()) {
			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
			//String insertSql = "insert into personal(name,name_pronunciation,belong_organization,organization) " + "values (:userid, :name, :name_p, :b_organization, :organization)";
			String sql = "update personal set data = :data, updated_at = :updated_at, file_name = :file_name" 
			+ " where account_id = :account_id";
			con.createQuery(sql)
			.addParameter("account_id",account_id)
			.addParameter("data",data)
			.addParameter("updated_at",updated_at)
			.addParameter("file_name",file_name)
			.executeUpdate();
		}
		
	}

}
