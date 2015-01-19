package codereview.chatsys.service;

import java.sql.SQLException;
import java.util.List;


import com.google.inject.Inject;

public class ConferenceEntryService implements IConferenceEntryService {
//
//	@Inject
//	private IConferenceEntryDAO conference;
//
//
//	@Override
//	public List<ConferenceEntryBean> selectAllWaitedConferenceEntry(int conference_id) {
//		List<ConferenceEntryBean> conferenceentrydata = null;
//		try {
//			conferenceentrydata = conference.selectAllWaitedConferenceEntry(conference_id);
//
//		} catch (NullPointerException | SQLException e){
//			e.printStackTrace();
//		}
//		return conferenceentrydata;
//	}
//
//
//	@Override
//	public ConferenceEntryBean selectConferenceEntryBean(int conference_entry_id) {
//		ConferenceEntryBean conferenceEntryBean = null;
//		try {
//			conferenceEntryBean = conference.selectConferenceEntryBean(conference_entry_id);
//		} catch (NullPointerException | SQLException e){
//			e.printStackTrace();
//		}
//		return conferenceEntryBean;
//	}
//
//
////	@Override
////	public void updateConferenceEntryMailAddress(int conference_entry_id,
////			String email_address) {
////		try {
////			conference.updateMailAddress(conference_entry_id, email_address);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////	}
//
//	@Override
//	public void updateConferenceRole(int conference_entry_id,
//			String role) {
//		try {
//			conference.updaterole(conference_entry_id,role);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void updateConferencePass(int conferenceId, String email,
//			String pass, Encrypter TYPE) {
//		try {
//			conference.updatePassword(conferenceId, email, pass, TYPE);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void updateConferenceEnterable(int conferenceId, String email,boolean enterable) {
//		try {
//			conference.updateEnterable(conferenceId, email,enterable);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
//	};
//
//	@Override
//	public List<ConferenceEntryBean> selectConferenceEntryList(int conference_id) {
//		List<ConferenceEntryBean> conferenceentrydata = null;
//		try {
//			conferenceentrydata = conference.selectAllConferenceEntry(conference_id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return conferenceentrydata;
//	}
//
//	@Override
//	public List<ConferenceEntryPersonalBean> selectConferenceEntryPersonalBeans(int conference_id){
//		List<ConferenceEntryPersonalBean> conenterpersonbean = null;
//		try {
//			conenterpersonbean = conference.selectConferenceEntryPersonal(conference_id);
//		} catch (SQLException | NullPointerException e) {
//			e.printStackTrace();
//		}
//
//		return conenterpersonbean;
//	}
//	@Override
//	public List<ConferenceEntryBean> selectAllActiveConferenceEntryBeans(int conference_id){
//		List<ConferenceEntryBean> conenterpersonbean = null;
//		try {
//			conenterpersonbean = conference.selectAllActiveConferenceEntry(conference_id);
//		} catch (SQLException | NullPointerException e) {
//			e.printStackTrace();
//		}
//
//		return conenterpersonbean;
//	}
//
//	@Override
//	public void insertConferenceEntry(ConferenceEntryForCreateConferenceEntryBean Bean){
//
//		try {
//			conference.insertConference(Bean);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//	@Override
//	public List<ConferenceEntryPersonalBean> selectNotEnterablePresenter(int conference_id) {
//		List<ConferenceEntryPersonalBean> conenterpersonbean = null;
//		try {
//			conenterpersonbean = conference.selectEnterablePresenter(conference_id);
//		} catch (SQLException | NullPointerException e) {
//			e.printStackTrace();
//		}
//
//		return conenterpersonbean;
//	}
}
