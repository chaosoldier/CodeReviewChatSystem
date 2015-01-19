package codereview.chatsys.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import codereview.chatsys.IDBCP;


import org.sql2o.Connection;

import com.google.inject.Inject;


/**
 * @author Ryuusuke Kobayashi
 */

public class ConferenceDAO implements IConferenceDAO{


	@Inject
	private IDBCP dbcp;

//	private List<ConferenceBean> list;
//
//	@Override
//	public List<ConferenceBean> getAllConferencesData() throws SQLException{
//
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT * FROM conference";
//			list = con.createQuery(sql).executeAndFetch(ConferenceBean.class);
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	@Override
//	public List<ConferenceBean> searchConferences() throws SQLException{
//
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "SELECT conference_id, caption FROM conference";
//			list = con.createQuery(sql).executeAndFetch(ConferenceBean.class);
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	@Override
//	public ConferenceBeanWithTime SelectConferenceWithTime(int id) throws SQLException,NullPointerException{
//
//		ConferenceBeanWithTime conference = new ConferenceBeanWithTime();
//
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select * from conference where conference_id = :id";
//			conference = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(ConferenceBeanWithTime.class);
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//		return conference;
//	}
//
//	@Override
//	public ConferenceBean SelectConference(int id) throws SQLException,
//			NullPointerException {
//
//		ConferenceBean conference = new ConferenceBean();
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select * from conference where conference_id = :id";
//			conference = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(ConferenceBean.class);
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//		return conference;
//	}
//
//
//
//	@Override
//	public void insertConference(ConferenceBean con) throws SQLException,NullPointerException {
//		String cap = con.getCaption();
//		String acc = con.getAccess();
//		Timestamp stat = null;
//		try {
//			stat = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getStart_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enat = null;
//		try {
//			enat = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEnd_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enst = null;
//		try {
//			enst = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEntry_start_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enen = null;
//		try {
//			enen = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEntry_end_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp past = null;
//		try {
//			past = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getPaper_start_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp paen = null;
//		try {
//			paen = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getPaper_end_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		String info = con.getInformation();
//		boolean auto = con.isAuto_enterable();
//		String soci = con.getSociety();
//
//		try (Connection conn = dbcp.getSql2o().open()) {
//			String sql = "insert into conference(caption,place,access,start_at,end_at,"
//					+ "entry_start_at,entry_end_at,paper_start_at,paper_end_at,information,"
//					+ "auto_enterable,society) values (:cap, :acc, :stat, :enat, :enst, "
//					+ ":enen, :past, :paen, :info, :auto, :soci)";
//			conn.createQuery(sql).addParameter("cap",cap).addParameter("acc",acc)
//			.addParameter("stat",stat).addParameter("enat",enat).addParameter("enst",enst)
//			.addParameter("enen",enen).addParameter("past",past).addParameter("paen",paen)
//			.addParameter("info",info).addParameter("auto",auto).addParameter("soci",soci).executeUpdate();
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public void updateConference(ConferenceBean con) throws SQLException,NullPointerException {
//		int id = con.getConference_id();
//		String cap = con.getCaption();
//		String acc = con.getAccess();
//		Timestamp stat = null;
//		try {
//			stat = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getStart_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enat = null;
//		try {
//			enat = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEnd_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enst = null;
//		try {
//			enst = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEntry_start_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp enen = null;
//		try {
//			enen = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getEntry_end_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp past = null;
//		try {
//			past = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getPaper_start_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		Timestamp paen = null;
//		try {
//			paen = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(con.getPaper_end_at()).getTime());
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		String info = con.getInformation();
//		boolean auto = con.isAuto_enterable();
//		String soci = con.getSociety();
//
//		try (Connection conn = dbcp.getSql2o().open()) {
//			String sql = "update conference set caption = "
//					+ ":cap, access = :acc, start_at = :stat, end_at = :enat, entry_start_at = :enst,"
//					+ "entry_end_at = :enen, paper_start_at = :past, paper_end_at = :paen,"
//					+ "information = :info, auto_enterable = :auto, society = :soci"
//					+ " where conference_id = :id";
//			conn.createQuery(sql).addParameter("id",id).addParameter("cap",cap).addParameter("acc",acc)
//			.addParameter("stat",stat).addParameter("enat",enat).addParameter("enst",enst)
//			.addParameter("enen",enen).addParameter("past",past).addParameter("paen",paen)
//			.addParameter("info",info).addParameter("auto",auto).addParameter("soci",soci).executeUpdate();
//
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//
//	}
//
//
//	@Override
//	public void deleteConference(int id) throws SQLException,NullPointerException {
//
//
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "delete from conference where conference_id = :id";
//			con.createQuery(sql).addParameter("id",id).executeUpdate();
//
//		}
//	}
//
//	@Override
//	public int selectConferenceAlias(String alias) throws SQLException,
//			NullPointerException {
//		int cid = 0;
//		try (Connection con = dbcp.getSql2o().open()) {
//			String sql = "select conference_id from conference where alias = :alias";
//			cid = con.createQuery(sql).addParameter("alias",alias).executeScalar(Integer.class);
//		}
//		return cid;
//
//	}

}
