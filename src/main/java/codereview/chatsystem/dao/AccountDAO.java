package codereview.chatsystem.dao;

import java.sql.SQLException;

import codereview.chatsystem.IDBCP;
import codereview.chatsystem.bean.AccountBean;
import codereview.chatsystem.definition.Encrypter;
import lombok.extern.log4j.Log4j;

import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author Hiroto Yamakawa
 * @author Yuta Uehara
 */
@Log4j
public class AccountDAO implements IAccountDAO {

	@Inject
	private IDBCP dbcp;


	@Override
	public boolean login(String email,String password)throws SQLException, NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select passphrase from account where email_address = :email";
			//一致したら成功
			if(con.createQuery(sql).addParameter("email",email).executeScalar(String.class).equals(password)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Encrypter affirmEncrypter(String email)
			throws SQLException, NullPointerException {
		Encrypter encrypter = null;
		String encrypterStr = "";
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select encrypter from account where email_address = :email";
			encrypterStr = con.createQuery(sql).addParameter("email",email).executeScalar(String.class);
		}
		switch (encrypterStr) {
		case "TYPE1":
			encrypter = Encrypter.TYPE1;
			break;
		case "TYPE2":
			encrypter = Encrypter.TYPE2;
			break;
		}
		return encrypter;
	}
	@Override
	public AccountBean selectAccountBean(String email) throws SQLException,
			NullPointerException {
		try (Connection con = dbcp.getSql2o().open()) {
			String sql = "select account_id,email_address,available from account where email_address = :email";
			return con.createQuery(sql).addParameter("email",email).executeAndFetchFirst(AccountBean.class);
		}
	}
//	@Override
//	public int searchAccountForRecreatePassword(String email) throws SQLException,NullPointerException {
//		int accountId = 0;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select account_id from account where email_address = :email";
//			accountId = con.createQuery(sql).addParameter("email",email).executeScalar(Integer.class);
//		}
//		return accountId;
//	}
////	@Override
////	public void test() throws SQLException {
////		List<Integer> isbns = null;
////		try (Connection con = dbcp.getSql2o().open()) {
////			isbns = con.createQuery("select account_id from accout")
////					.executeScalarList(Integer.class);
////		}
////	}
//
//	@Override
//	public boolean isAccountActive(String email) throws SQLException,NullPointerException{
//		boolean activeFlag = false;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select account_id from account where email_address = :email";
//			activeFlag = con.createQuery(sql).addParameter("email",email).executeScalar(Boolean.class);
//		}
//		return activeFlag;
//	}
//
//	@Override
//	public Encrypter searchAccountEncrypter(String email) throws SQLException,NullPointerException {
//		String encrypterString = "";
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select encrypter from account where email_address = :email";
//			encrypterString = con.createQuery(sql).addParameter("email",email).executeScalar(String.class);
//		}
//		log.info(encrypterString);
//		switch (encrypterString) {
//		case "TYPE1":
//			return Encrypter.TYPE1;
//		case "TYPE2":
//			return Encrypter.TYPE2;
//		default:
//			return null;
//		}
//	}
	@Override
	public void updatePassword(String email,String pass,Encrypter currentEncrypter)throws SQLException{
		String insertSql = "update account set passphrase = :passParam ,encrypter = :accountEncrypter where email_address = :email";
		try (Connection con = dbcp.getSql2o().open()) {
			 con.createQuery(insertSql).addParameter("passParam",pass).addParameter("email",email).addParameter("accountEncrypter",currentEncrypter.toString()).executeUpdate();
		}
	}
//	@Override
//	public Encrypter searchAccountEncrypter(String email) throws SQLException,
//			NullPointerException {
//		// TODO Auto-generated method stub
//		return null;
//	}


//
//	@Override
//	public void updateEmailAddress(String email, String address)
//			throws SQLException {
//		String sql = "update account set email_address = :email_address  where email_address = :email";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("email_address",address).addParameter("email",email).executeUpdate();
//		}
//	}
//
//	public boolean insertAccount(String email, String pass,String encrypter) throws SQLException,NullPointerException {
//		boolean activeFlag = false;
//		try (Connection con = dbcp.getSql2o().open()) {
//			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
//			String insertSql = "insert into account(email_address, passphrase, encrypter) " + "values (:email, :pass, :encrypter)";
//			con.createQuery(insertSql).addParameter("email",email).addParameter("pass",pass).addParameter("encrypter",encrypter).executeUpdate();
//		}
//		return activeFlag;
//	}
//
//	@Override
//	public String selectEmailAddress(int accountId) throws SQLException,NullPointerException {
//		String email_address = "";
//		try (Connection con = dbcp.getSql2o().open()){
//			String sql = "select email_address from account where account_id = :accountId";
//			email_address = con.createQuery(sql).addParameter("accountId", accountId).executeScalar(String.class);
//		}
//		return email_address;
//	}
//
//	@Override
//	public void deleteAccount(int account_id) throws SQLException {
//		if (account_id!=1){
//			String sql="delete from account where account_id=:account_id";
//			try(Connection con = dbcp.getSql2o().open()){
//				con.createQuery(sql).addParameter("account_id",account_id).executeUpdate();
//			}
//		}
//	}
//	@Override
//	public int selectAccountId(String email) throws SQLException,
//			NullPointerException {
//		int accoundId = -1;
//		try (Connection con = dbcp.getSql2o().open()){
//			String sql = "select account_id from account where email_address = :email";
//			accoundId = con.createQuery(sql).addParameter("email", email).executeScalar(Integer.class);
//		}
//		return accoundId;
//	}
}