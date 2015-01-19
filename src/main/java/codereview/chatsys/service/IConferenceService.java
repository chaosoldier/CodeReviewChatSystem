package codereview.chatsys.service;

import java.util.List;


import com.google.inject.ImplementedBy;



/**
 * @author Ryuusuke Kobayashi[
 * @author yuta uehara
 *
 */

@ImplementedBy(ConferenceService.class)
public interface IConferenceService {
//
//	/**
//	 * 大会の情報を全てConferenceBeanで取得
//	 * @param id
//	 * @return
//	 */
//	public List<ConferenceBean> getAllConferenceBeanList();
//	/**
//	 * conferece_idを指定して大会の情報をConferenceBeanで取得
//	 * @param id
//	 * @return
//	 */
//
//	public ConferenceBeanWithTime getSelectConferenceBeanWithTime(int id);
//
//	public ConferenceBean getSelectConferenceBean(int id);
//
//	/**
//	 * 利用できる大会のIDを取得
//	 * @param account_id
//	 * @return
//	 */
//	//TODO 不要？
////	public List<Integer> getAllAvailableConferenceId(int account_id);
//	/**
//	 * 利用できる大会の情報をConferenceBeanで取得
//	 * @param account_id
//	 * @return
//	 */
//
//	//TODO 不要？
////	public List<ConferenceBean> getAllAvailableConferenceBean(int account_id);
//
//	public void updateConferenceBean(ConferenceBean conf);
//
//	/**
//	 * URLの大会名エイリアスは有効であるか
//	 * @param aliasParam
//	 * @return 返り値がtrueなら利用可能
//	 */
//	public boolean isConferenceAiliasAvailable(String aliasParam);
//	/**
//	 * URLの大会名エイリアスはからConferenceIdを取得
//	 * @param aliasParam
//	 * @return if該当なしthen-1
//	 */
//	public int getConferenceIdByAilias(String aliasParam);
//
//	public void insertConferenceBean(ConferenceBean conf);
//
//	public void deleteConferenceBean(int id);

}
