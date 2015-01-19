package codereview.chatsys.service;

import java.sql.SQLException;
import java.util.List;


import lombok.extern.log4j.Log4j;

import com.google.inject.Inject;


/**
 *
 * @author Ryuusuke Kobayashi
 * @author yuta uehara
 */
@Log4j
public class ConferenceService implements IConferenceService {

//	@Inject
//	private IConferenceDAO conference;
//
//	@Inject
//	private IConferenceEntryDAO cedao;
//
//	@Override
//	public List<ConferenceBean> getAllConferenceBeanList() {
//
//		List list = null;
//		try {
//			list = conference.getAllConferencesData();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}
//
//	@Override
//	public ConferenceBeanWithTime getSelectConferenceBeanWithTime(int id) {
//
//		ConferenceBeanWithTime conferencedata = null;
//		try {
//			conferencedata = conference.SelectConferenceWithTime(id);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//		return conferencedata;
//	}
//
//	@Override
//	public ConferenceBean getSelectConferenceBean(int id) {
//		ConferenceBean conferencedata = null;
//		try {
//			conferencedata = conference.SelectConference(id);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//		return conferencedata;
//	}
//
//
//
//	private List<ConferenceBean> availableConferenceBeans;
//	//TODO 不要？
////	@Override
////	public List<ConferenceBean> getAllAvailableConferenceBean(int account_id) {
////		availableConferenceBeans = new ArrayList<ConferenceBean>();
////		try {
////			for (Integer availabeleConferenceId : cedao.
////					.selectAllAvailableConferenceId(account_id)) {
////				availableConferenceBeans.add(conference
////						.SelectConference(availabeleConferenceId));
////
////
////			}
////		} catch (NullPointerException | SQLException e) {
////			e.printStackTrace();
////		}
////		return availableConferenceBeans;
////	}
//
//	@Override
//	public void updateConferenceBean(ConferenceBean conf) {
//
//
//		try {
//
//			conference.updateConference(conf);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Inject
//	private IConferenceDAO cdao;
//	@Override
//	public boolean isConferenceAiliasAvailable(String aliasParam) {
//		try {
//			cdao.selectConferenceAlias(aliasParam);
//			return true;
//		} catch (NullPointerException | SQLException e) {
//			return false;
//		}
//	}
//
//
//	@Override
//	public void deleteConferenceBean(int id) {
//		try {
//			conference.deleteConference(id);
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public int getConferenceIdByAilias(String aliasParam) {
//		int cid;
//		try {
//			cid = cdao.selectConferenceAlias(aliasParam);
//			return cid;
//		} catch (NullPointerException | SQLException e) {
//			return -1;
//		}
//	}
//
//	@Override
//	public void insertConferenceBean(ConferenceBean conf) {
//		try {
//			cdao.insertConference(conf);
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}


}
