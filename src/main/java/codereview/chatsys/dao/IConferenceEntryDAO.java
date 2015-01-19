package codereview.chatsys.dao;

import java.sql.SQLException;
import java.util.List;



import com.google.inject.ImplementedBy;
/**
 * @author Yuta Uehara
 */
@ImplementedBy(ConferenceEntryDAO.class)
public interface IConferenceEntryDAO {

//	public void test() throws SQLException;
//
//	/**
//	 * 大会参加管理IDを取得
//	 *
//	 * @param conferenceId
//	 * @param email
//	 * @return conference_entry_id
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public int selectConferenceEntryID(int conferenceId,String email) throws SQLException,NullPointerException;
//
//	/**
//	 * 暗号化アルゴリズムのTYPEを取得
//	 * @param email_address
//	 * @return enum判定用のTYPE 一致しない場合はnull
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public Encrypter affirmEncrypter(int conferenceId,String email) throws SQLException,NullPointerException;
//	/**
//	 * 一般ユーザの権限を取得 definition.EnumConferenceRoleに格納
//	 * @param email_address
//	 * @return enum判定用のTYPE 一致しない場合はnull
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public GeneralUserAuthrities affirmConferenceAuthority(int conferenceId,String email) throws SQLException,NullPointerException;
//
//	/**
//	 * 大会参加管理IDが参加状態であるか
//	 *
//	 * @param conferenceId
//	 * @param email
//	 * @return enterableが返る
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public boolean affirmEnterable(int conferenceId,String email)throws SQLException,NullPointerException;
//	/**
//	 * 大会参加管理IDは講演会に参加できるか
//	 *
//	 * @param conferenceId
//	 * @param email
//	 * @return enterable_presenterが返る
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public boolean affirmEnterablePresenter(int conferenceId, String email)
//			throws SQLException, NullPointerException;
//	/**
//	 * 大会参加管理IDが論理削除されているか
//	 * 　旧isAccountActive(int accountId)
//	 *
//	 * @param conferenceId
//	 * @param email
//	 * @return availableが返る
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public boolean affirmAvailable(int conferenceId,String email)throws SQLException,NullPointerException;
//
//
//	/**
//	 * ある大会でアクティブ（アカウントが利用可能）なユーザのリストを取得
//	 * @param conferenceId
//	 * @return List<ConferenceEntryBean>参加状態と論理削除の両方がTRUEのリストを返す
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public List<ConferenceEntryBean> selectAllActiveConferenceEntry(int conferenceId)throws SQLException,NullPointerException;
//
//	/**
//	 * ある大会で承認待ち状態のユーザのリストを取得
//	 * @param conferenceId
//	 * @return List<ConferenceEntryBean>参加状態FALSEで論理削除がTRUEのリストを返す
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public List<ConferenceEntryBean> selectAllWaitedConferenceEntry(int conferenceId)throws SQLException,NullPointerException;
//	/**
//	 * ある大会で講演者承認待ち状態のユーザのリストを取得
//	 * @param conferenceId
//	 * @return List<ConferenceEntryBean>講演者で参加状態TRUEで講演参加状態がFALASEで論理削除がTRUEのリストを返す
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public List<ConferenceEntryBean> selectAllWaitedPresenterConferenceEntry(int conferenceId)throws SQLException,NullPointerException;
//
//	/**
//	 * ある大会ですべてのユーザのリストを取得
//	 * @param conferenceId
//	 * @return List<ConferenceEntryBean>リストを返す
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public List<ConferenceEntryBean> selectAllConferenceEntry(int conferenceId)throws SQLException,NullPointerException;
//
//	//LoginService用
//
//	/**
//	 * 大会参加管理Beanを取得
//	 *
//	 * @param conferenceId
//	 * @param email
//	 * @return ConferenceEntryBean
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public ConferenceEntryBean selectConferenceEntryBean(int conferenceId,String email) throws SQLException,NullPointerException;
//
//	/**
//	 * conference_entry_idを入力して大会参加管理Beanを取得
//	 *
//	 * @param conference_entry_id
//	 * @return ConferenceEntryBean
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public ConferenceEntryBean selectConferenceEntryBean(int conference_entry_id) throws SQLException,NullPointerException;
//
//	/**
//	 * ある大会参加管理IDはログイン可能か問い合わせる
//	 * @param conferenceId
//	 * @param email
//	 * @return 該当大会参加管理IDでパスワードがデータベースの値と一致すればTRUE
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public boolean login(int conferenceId,String email,String password) throws SQLException,NullPointerException;
//
//
//	//大会参加管理アカウントのCRUD
//
//	/**
//	 *ある大会参加管理IDのパスワードをアップデートする 最新のEncrypter.typeを指定する必要がある
//	 * @param conferenceId
//	 * @param email
//	 * @param pass
//	 * @throws SQLException
//	 */
//	public void updatePassword(int conferenceId,String email,String pass,Encrypter TYPE)throws SQLException;
//
////	/**
////	 *ある大会参加管理IDのメールアドレスをアップデートする
////	 * @param conferenceId
////	 * @param email
////	 * @param pass
////	 * @throws SQLException
////	 */
////	public void updateMailAddress(int conference_entry_id,String email)throws SQLException;
//
//	/**
//	 *ある大会参加管理IDの参加状態を変更する
//	 * @param conferenceId
//	 * @param email
//	 * @param enterable
//	 * @throws SQLException
//	 */
//	public void updateEnterable(int conferenceId,String email,boolean enterable)throws SQLException;
//
//	/**
//	 *ある大会参加管理IDの参加状態を変更する
//	 * @param conferenceId
//	 * @param email
//	 * @param available
//	 * @throws SQLException
//	 */
//	public void updateAvailable(int conferenceId,String email,boolean available)throws SQLException;
//	/**
//	 * ユーザがある大会参加管理IDの行を削除する(パスワードも一致させないと削除できない)
//	 * @param conferenceId
//	 * @param email
//	 * @param password
//	 * @throws SQLException
//	 */
//	public void deleteConferenceEntryUsingPassword(int conferenceId,String email,String password) throws SQLException,NullPointerException;
//
//	//システム管理者向け
//	//TODO論理削除時のデータ上書き
//	/**
//	 * 管理者がある大会参加管理IDの行を削除する(パスワード不要)
//	 * @param conferenceId
//	 * @param email
//	 * @throws SQLException
//	 */
//	public void deleteConferenceEntry(int conferenceId,String email) throws SQLException,NullPointerException;
////	public boolean addConferenceEntry(String email, String pass) throws SQLException,NullPointerException;
//
//	public void updaterole(int conferenceId,String role) throws SQLException;
//
//	public List<ConferenceEntryPersonalBean> selectConferenceEntryPersonal(int conference_id) throws SQLException,NullPointerException;
//
//	public void insertConference(ConferenceEntryForCreateConferenceEntryBean con) throws SQLException,NullPointerException;
//
//	public List<ConferenceEntryPersonalBean> selectEnterablePresenter(int conferenceId)throws SQLException,NullPointerException;
}
