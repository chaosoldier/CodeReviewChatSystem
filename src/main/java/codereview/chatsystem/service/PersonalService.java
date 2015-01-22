package codereview.chatsystem.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;








import codereview.chatsystem.bean.PersonalBean;
import codereview.chatsystem.dao.IPersonalDAO;

import com.google.inject.Inject;

/**
 * @author yuta uehara
 *
 */
public class PersonalService implements IPersonalService {
	@Inject
	IPersonalDAO personalDao;

	@Override
	public PersonalBean getPersonalBean(int accountId) {
		PersonalBean bean = null;
		
		try {
			bean = personalDao.selectPersonal(accountId);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return bean;
	}
//
//	@Inject
//	IPersonalDAO personalDao;
//
//	@Override
//	public PersonalBean getPersonalBean(int personal_id) {
//		PersonalBean bean = null;
//		try {
//			bean = personalDao.selectPersonalData(personal_id);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//		return bean;
//	}
//	@Override
//	public PersonalBean getPersonalBeanByConferenceEntryId(int ConferenceEntryId) {
//		PersonalBean bean = null;
//		try {
//			bean = personalDao.selectPersonalDataByConfereEntryId(ConferenceEntryId);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//		return bean;
//	}
//
//	@Override
//	public List<PersonalBean> getAllPersonalBean() {
//		List<PersonalBean> list = null;
//		try {
//			list=personalDao.selectAllPersonalData();
//		} catch (SQLException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		for(int i=0;i<list.size();i++){
//			if(list.get(i).getName().equals("退会者")){
//				list.remove(i);
//			}
//		}
//		return list;
//	}
//
//
//	@Inject
//	IAccountDAO accountDao;
//
//	@Override
//	public String getEmailAddress(int account_id) {
//		String emailAddreses = "";
//		try {
//			emailAddreses = accountDao.selectEmailAddress(account_id);
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		return emailAddreses;
//	}
//
//	@Override
//	public void insertPersonalData(int conference_entry_id, String name,
//			String roman_name, String organization, String receipt_name) {
//		try {
//			personalDao.insertPersonalData(conference_entry_id, name, roman_name, organization, receipt_name);
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void updatePersnalData(int personal_id,int conference_entry_id,String name,String roman_name,String organization,String receipt_name) {
//		try {
//			personalDao.updatePersonalData(personal_id,conference_entry_id,name,roman_name,organization,receipt_name);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	@Override
//	public void deletePersonalData(int personal_id) {
//		try {
//			personalDao.deletePersonalData(personal_id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	@Override
//	public List<PersonalBean> getConferenceEntryMember(List<Integer> list) {
//		List<PersonalBean> plist = new ArrayList<>();
//		PersonalBean temp;
//		try {
//			for(int i:list){
//				plist.add(personalDao.selectPersonalData(i));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return plist;
//	}

	@Override
	public boolean updateAvatar(int account_id, byte[] data, String file_name) {
		try {
			personalDao.updateAvatar(account_id, data,new Timestamp(System.currentTimeMillis()), file_name);
			return true;
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}


}
