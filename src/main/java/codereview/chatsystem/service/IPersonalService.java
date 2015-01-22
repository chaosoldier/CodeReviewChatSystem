package codereview.chatsystem.service;

import java.sql.Timestamp;
import java.util.List;




import codereview.chatsystem.bean.PersonalBean;

import com.google.inject.ImplementedBy;

/**
 * @author yuta uehara
 *
 */

@ImplementedBy(PersonalService.class)
public interface IPersonalService {

	/**
	 * アカウントIDを指定してpersonal tableのデータを取得
	 * @param accountId
	 * @return if cannnot get bean return null
	 */
	public PersonalBean getPersonalBean(int accountId);
//	public PersonalBean getPersonalBeanByConferenceEntryId(int conferenceEntryId);
//	public List<PersonalBean> getAllPersonalBean();
//	public String getEmailAddress(int account_id);
//	public void insertPersonalData(int conference_entry_id,String name,String roman_name,String organization,String receipt_name);
//	public void updatePersnalData(int personal_id,int conference_entry_id,String name,String roman_name,String organization,String receipt_name);
//	public void deletePersonalData(int personal_id);
//	public List<PersonalBean> getConferenceEntryMember(List<Integer> list);
	/**
	 * アカウントIDを指定してアバターを更新
	 * @param account_id
	 * @param data
	 * @param file_name
	 * @return　失敗した時false
	 */
	public boolean updateAvatar(int account_id, byte[] data,String file_name);
}

