package codereview.chatsys.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import codereview.chatsys.IDBCP;

import lombok.extern.log4j.Log4j;

import org.sql2o.Connection;

import com.google.inject.Inject;

/**
 * @author Hiroto Yamakawa
 * @author Yuta Uehara
 */
@Log4j
public class ConferenceEntryDAO implements IConferenceEntryDAO {

	@Inject
	private IDBCP dbcp;

//	@Override
//	public int selectConferenceEntryID(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		int accountId = 0;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select conference_entry_id from conference_entry where conference_id = :cid and email_address = :email";
//			accountId = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(Integer.class);
//		}
//		return accountId;
//	}
//
//	@Override
//	public Encrypter affirmEncrypter(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		Encrypter encrypter = null;
//		String encrypterStr = "";
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select encrypter from conference_entry where conference_id = :cid and email_address = :email";
//			encrypterStr = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(String.class);
//		}
//		switch (encrypterStr) {
//		case "TYPE1":
//			encrypter = Encrypter.TYPE1;
//			break;
//		case "TYPE2":
//			encrypter = Encrypter.TYPE2;
//			break;
//		}
//		return encrypter;
//	}
//
//	@Override
//	public GeneralUserAuthrities affirmConferenceAuthority(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		GeneralUserAuthrities auth = null;
//		String authStr = "";
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select conference_role from conference_entry where conference_id = :cid and email_address = :email";
//			authStr = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(String.class);
//		}
//		switch (authStr) {
//		case "管理者":
//			auth = GeneralUserAuthrities.ORGANIZER;
//			break;
//		case "講演者":
//			auth = GeneralUserAuthrities.PRESENTER;
//			break;
//		case "聴講者":
//			auth = GeneralUserAuthrities.AUDITOR;
//			break;
//		}
//		return auth;
//	}
//	@Override
//	public boolean affirmEnterablePresenter(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		boolean enterable = false;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select enterable_presenter from conference_entry where conference_id = :cid and email_address = :email";
//			enterable = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(Boolean.class);
//		}
//		return enterable;
//	}
//	@Override
//	public boolean affirmEnterable(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		boolean enterable = false;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select enterable from conference_entry where conference_id = :cid and email_address = :email";
//			enterable = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(Boolean.class);
//		}
//		return enterable;
//	}
//	@Override
//	public boolean affirmAvailable(int conferenceId, String email)
//			throws SQLException, NullPointerException {
//		boolean available = false;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select enterable from conference_entry where conference_id = :cid and email_address = :email";
//			available = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(Boolean.class);
//		}
//		return available;
//	}
//	@Override
//	public List<ConferenceEntryBean> selectAllActiveConferenceEntry(
//			int conferenceId) throws SQLException, NullPointerException {
//		List<ConferenceEntryBean> list = new ArrayList<ConferenceEntryBean>();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT conference_entry_id, conference_id,email_address FROM conference_entry where conference_id = :cid and enterable=true and available=true";
//			list = con.createQuery(sql).addParameter("cid",conferenceId).executeAndFetch(ConferenceEntryBean.class);
//		}
//		return list;
//	}
//	@Override
//	public List<ConferenceEntryBean> selectAllWaitedConferenceEntry(
//			int conferenceId) throws SQLException, NullPointerException {
//		List<ConferenceEntryBean> list = new ArrayList<ConferenceEntryBean>();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT conference_entry_id, conference_id,email_address FROM conference_entry where conference_id = :cid and enterable=false and available=true";
//			list = con.createQuery(sql).addParameter("cid",conferenceId).executeAndFetch(ConferenceEntryBean.class);
//		}
//		return list;
//	}
//	@Override
//	public List<ConferenceEntryBean> selectAllWaitedPresenterConferenceEntry(
//			int conferenceId) throws SQLException, NullPointerException {
//		List<ConferenceEntryBean> list = new ArrayList<ConferenceEntryBean>();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT conference_entry_id, conference_id,email_address FROM conference_entry where conference_id = :cid and enterable=true and enterable_presenter=true and available=true";
//			list = con.createQuery(sql).addParameter("cid",conferenceId).executeAndFetch(ConferenceEntryBean.class);
//		}
//		return list;
//	}
//	@Override
//	public List<ConferenceEntryBean> selectAllConferenceEntry(
//			int conferenceId) throws SQLException, NullPointerException {
//		List<ConferenceEntryBean> list = new ArrayList<ConferenceEntryBean>();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT conference_entry_id, conference_id,email_address FROM conference_entry where conference_id = :cid and available=true";
//			list = con.createQuery(sql).addParameter("cid",conferenceId).executeAndFetch(ConferenceEntryBean.class);
//		}
//		return list;
//	}
//	@Override
//	public ConferenceEntryBean selectConferenceEntryBean(int conferenceId,
//			String email) throws SQLException, NullPointerException {
//		ConferenceEntryBean bean;
//
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select conference_entry_id, conference_id,email_address from conference_entry where conference_id = :cid and email_address = :email";
//			bean = con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeAndFetchFirst(ConferenceEntryBean.class);
//		}
//		return bean;
//	}
//	@Override
//	public ConferenceEntryBean selectConferenceEntryBean(int conference_entry_id)
//			throws SQLException, NullPointerException {
//		ConferenceEntryBean bean;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select conference_entry_id, conference_id,email_address from conference_entry where conference_entry_id = :cid ";
//			bean = con.createQuery(sql).addParameter("cid",conference_entry_id).executeAndFetchFirst(ConferenceEntryBean.class);
//		}
//		return bean;
//	}
//	@Override
//	public boolean login(int conferenceId, String email,String password)
//			throws SQLException, NullPointerException {
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select passphrase from conference_entry where conference_id = :cid and email_address = :email";
//			//一致したら成功
//			if(con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeScalar(String.class).equals(password)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void updatePassword(int conferenceId,String email,String password,Encrypter TYPE) throws SQLException {
//		String sql = "update conference_entry set passphrase = :password , encrypter=:encrypter where conference_id = :cid and email_address = :email";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("password",password).addParameter("encrypter",TYPE.toString()).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
//		}
//
//	}
////	@Override
////	public void updateMailAddress(int conference_entry_id, String email)
////			throws SQLException {
////		String sql = "update conference_entry set email_address = :email_address where conference_entry_id = :conference_entry_id ";
////		try (Connection con = dbcp.getSql2o().open()) {
////			 con.createQuery(sql).addParameter("email_address",email).addParameter("conference_entry_id",conference_entry_id).executeUpdate();
////		}
////	}
//	@Override
//	public void updateEnterable(int conferenceId, String email,
//			boolean enterable) throws SQLException {
//		String sql = "update conference_entry set enterable=:enterable where conference_id = :cid and email_address = :email";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("enterable",enterable).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
//		}
//
//	}
//
//	@Override
//	public void updateAvailable(int conferenceId, String email,
//			boolean available) throws SQLException {
//		String sql = "update conference_entry set available=:available where conference_id = :cid and email_address = :email";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("available",available).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
//		}
//
//	}
//
//	@Override
//	public void updaterole(int conferenceId,String role) throws SQLException {
//		String sql = "update conference_entry set conference_role = :role where conference_entry_id = :cid";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("role",role).addParameter("cid",conferenceId).executeUpdate();
//		}
//
//	}
//
////	@Override
////	public void deleteConferenceEntryUsingPassword(int conferenceId, String email,String password)
////			throws SQLException,NullPointerException {
////		String sql = "delete from where conference_id = :cid and email_address = :email and passphrase = :pass";
////		try (Connection con = dbcp.getSql2o().open()) {
////			 con.createQuery(sql).addParameter("pass",password).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
////		}
////
////	}
////	@Override
////	public void deleteConferenceEntry(int conferenceId, String email)
////			throws SQLException,NullPointerException {
////		String sql = "delete from where conference_id = :cid and email_address = :email";
////		try (Connection con = dbcp.getSql2o().open()) {
////			 con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
////		}
////
////	}
//	@Override
//	public void deleteConferenceEntryUsingPassword(int conferenceId, String email,String password)
//			throws SQLException,NullPointerException {
//		String sql = "update conference_entry set enterable=FALSE , available=FALSE where conference_id = :cid and email_address = :email and passphrase = :pass";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).addParameter("pass",password).executeUpdate();
//		}
//
//	}
//	@Override
//	public void deleteConferenceEntry(int conferenceId, String email)
//			throws SQLException,NullPointerException {
//		String sql = "update conference_entry set enterable=FALSE , available=FALSE where conference_id = :cid and email_address = :email";
//		try (Connection con = dbcp.getSql2o().open()) {
//			 con.createQuery(sql).addParameter("cid",conferenceId).addParameter("email",email).executeUpdate();
//		}
//	}
////	public boolean addAccount(String email, String pass) throws SQLException,NullPointerException {
////		boolean activeFlag = false;
////		try (Connection con = dbcp.getSql2o().open()) {
////			//String sql = "select account_id from account where email_address = :email and passphrase = :pass";
////			String insertSql = "insert into account(email_address, passphrase) " + "values (:email, :pass)";
////			activeFlag = con.createQuery(insertSql).addParameter("email",email).addParameter("pass",pass).executeScalar(boolean.class);
////		}
////		return activeFlag;
////	}
//
//	public List<ConferenceEntryPersonalBean> selectConferenceEntryPersonal(int conference_id) throws SQLException,NullPointerException{
//		List<ConferenceEntryPersonalBean> conenterpersonbeans;
//		String sql = "select conference_entry.conference_entry_id, conference_id, email_address, personal_id, name, roman_name, organization "
//				+ "from conference_entry "
//				+ "join personal "
//				+ "on conference_entry.conference_entry_id = personal.conference_entry_id where conference_id = :id and enterable = false and available = true";
//		try (Connection con = dbcp.getSql2o().open()){
//			conenterpersonbeans = con.createQuery(sql).addParameter("id", conference_id).executeAndFetch(ConferenceEntryPersonalBean.class);
//		}
//		return conenterpersonbeans;
//	}
//
//	@Override
//	public void insertConference(ConferenceEntryForCreateConferenceEntryBean con) throws SQLException,NullPointerException {
//		int id = con.getConference_id();
//		String mail = con.getEmail_address();
//		String pass = con.getPassphrase();
//		String encry = con.getTYPE().toString();
//		boolean enter = false;
//		boolean enterp = false;
//		boolean ava = true;
//		String role = "聴講者";
//
//		try (Connection conn = dbcp.getSql2o().open()) {
//			String sql = "insert into conference_entry(conference_id,email_address,passphrase,encrypter,enterable,"
//					+ "enterable_presenter,available,conference_role"
//					+ ") values (:cid, :mail, :pass, :encry, :enterable, "
//					+ ":enterp, :available, :role)";
//			conn.createQuery(sql)
//			.addParameter("cid",id).addParameter("mail",mail).addParameter("pass",pass)
//			.addParameter("encry",encry).addParameter("enterable",enter).addParameter("enterp",enterp)
//			.addParameter("available",ava).addParameter("role",role).executeUpdate();
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public List<ConferenceEntryPersonalBean> selectEnterablePresenter(int conferenceId) throws SQLException,NullPointerException {
//		List<ConferenceEntryPersonalBean> conenterpersonbeans;
//		String role = "講演者";
//		String sql = "select conference_entry.conference_entry_id, conference_id, email_address, personal_id, name, roman_name, organization "
//				+ "from conference_entry "
//				+ "join personal "
//				+ "on conference_entry.conference_entry_id = personal.conference_entry_id "
//				+ "where conference_id = :id and enterable = true and enterable_presenter = false and conference_role = :role and available = true";
//		try (Connection con = dbcp.getSql2o().open()){
//			conenterpersonbeans = con.createQuery(sql).addParameter("id", conferenceId).addParameter("role", role).executeAndFetch(ConferenceEntryPersonalBean.class);
//		}
//		return conenterpersonbeans;
//	}











}