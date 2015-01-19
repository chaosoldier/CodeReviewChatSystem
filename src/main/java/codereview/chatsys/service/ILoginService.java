package codereview.chatsys.service;

import java.lang.annotation.Documented;
import java.sql.SQLException;
import java.util.List;



import com.google.inject.ImplementedBy;

/**
 * @author yuta uehara
 *
 */
@ImplementedBy(LoginService.class)
public interface ILoginService {
	
//	/**
//	 * 現在のEncrypter
//	 */
//	public final Encrypter CURRENT_TYPE = Encrypter.TYPE2;
//	
//	/**
//	 * {@value}：EncrypterType2の設定
//	 */
//	public final int TYPE2_STRETCH_COUNT = 10000;
//	/**
//	 * 再設定時　パスワード生成用　記号は含まれていない
//	 */
//	public final String allwedPasswordStringIfReCreate = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	/**
//	 * 新規アカウント発行　パスワード照合用　記号は含まれていない
//	 */
//	public final String allwedPasswordSpecialCharacterIfCreate = "¥$¥@¥#¥$¥%¥&¥'¥(¥)¥=¥~¥|¥-¥^¥@¥[¥;¥:¥]¥,¥.¥/¥`¥{¥+¥*¥}¥>¥?¥_";
//	public final String allwedPasswordStringIfCreate = "0-9a-zA-Z"+allwedPasswordSpecialCharacterIfCreate;
//
//
//	/**
//	 * 大会参加のログイン
//	 * @param email
//	 * @param pass
//	 * @param conferenceId 大会ID
//	 * @return 照合に成功し、参加状態がTRUEで論理削除がTRUEであればtrue
//	 */
//	public boolean login(String email,String pass,int conferenceId);
//	/**
//	 * システム管理者のログイン あえて明示的にメソッドを分けている
//	 * @param email
//	 * @param pass
//	 * @return 照合に成功し、論理削除されていなければtrue
//	 */
//	public boolean loginAsSystemAccount(String email, String pass);
//
//	/**
//	 * 一般ユーザの権限を取得
//	 * @param conferenceId
//	 * @param email
//	 * @return GeneralUserAuthrities
//	 * 追加条件１　参加状態と論理削除が両方TRUEで講演者参加がtrueのときは講演者の権限
//	 * 追加条件２　参加状態と論理削除が両方TRUEで講演者参加がfalseのときは聴講者の権限
//	 */
//	public GeneralUserAuthrities getGeneralUserAuthrities(int conferenceId,
//			String email);
//	/**
//	 * 	
//	 * 一般ユーザ用　MySessionで使用するためのConferenceEntryBeanを取得する
//	 * @param conferenceId
//	 * @param email
//	 * @return ConferenceEntryBean Returns{@code null}if 失敗したとき
//	 */
//	public ConferenceEntryBean getConferenceEntryBean(int conferenceId ,String email);
//	/**
//	 * 	
//	 * 管理者用　MySessionで使用するためのConferenceEntryBeanを取得する
//	 * @param conferenceId
//	 * @return conference_entry_id=account_id,conference_id=-1,email_address=account.email_address Returns{@code null}if 失敗したとき
//	 */
//	public ConferenceEntryBean getConferenceEntryBean(String email);
//	/**
//	 * 一度取得したらMySessionでaccountIdを管理する
//	 * @param email
//	 * @param pass
//	 * @return　account_id
//	 */
//	@Deprecated
//	public int getAccountId(String email,String pass);
//	
//	//TODO 削除予定
//	/**
//	 *　一般ユーザの権限を確認する
//	 * @param accountId
//	 * @param conferenceId
//	 * @return　conference_account_role(日本語名)
//	 */
////	public String checkGeneralUserAuthrity(int conferenceId,int accountId);
//	
//	/**
//	 * 大会に入れるかどうかを検査する 参加状態と論理削除の両方がTRUEであるか
//	 * @param conferenceId
//	 * @param accountId
//	 * @return 大会のページに入る権限があればtrue
//	 * @throws SQLException
//	 * @throws NumberFormatException
//	 */
//	public boolean checkAllowdConference(int conferenceId,String email);
//
////	/**
////	 * ユーザの種別を確認する
////	 * @param accountId
////	 * @return　account_role(日本語名)
////	 */
////	public String checkAccoutRole(int accountId);
//	
//	public final String errorMsgEmailInvaild = "emailaddressに問題がある";
//	public final String errorMsgPassHasEmailInvaild = "パスワードにemailaddressの一部が含まれている";
//	public final String errorMsgPassHasCharInvaild = "パスワードに許可されていない文字列が含まれている";
//
//	/**
//	 * 新規アカウント作成フォームでパスワードを新規作成するためのパスワードを暗号化メソッド
//	 * @param pass
//	 * @param email
//	 * @return　bean.password:暗号化されたパスワード bean.TYPE：暗号化に使用したEncrypterTYPE bean.errorMsg：エラー文の内容
//	 * if　失敗した時　bean.password:空文字　bean.TYPE：null bean.errorMsg：エラー文の内容
//	 */
//	public PasswordForNewAccountCreateFormPageBean convertPassForCreateNewAccount(String pass,String emailAddress);
//	
//	/**
//	 * @TODO
//	 * パスワードを暗号化する実験用なので
//	 * 本稼働時、サービスを削除する
//	 * @param pass
//	 * @param email
//	 * @param TYPE
//	 * @return　暗号化されたパスワード
//	 *  暗号化に失敗したとき、encrypterが不正だったとき「dWeFJZviuZYoLPaGfKPU3P8XLaDmvgsx」を返す
//	 */
//	public String convertPass(String pass,String email,Encrypter TYPE);
//	
//	/**
//	 * パスワード再発行管理者用　emailAddressが存在してアカウントが有効であればパスワードを再発行する
//	 * @param emailAddress
//	 * @return　生のパスワードが返る 
//	 * 
//	 * ・8文字から16文字のパスワードを作成
//	 * ・パスワードにメールアドレスの@より前が含まれていたらパスワード再作成
//	 * ・0-9 a-z A-Zのいずれも含まれていたらパスワード再作成
//	 * 
//	 */
//	public String createNewPassword(String emailAddress);
//	/**
//	 * パスワード再発行一般ユーザ用　アカウントが存在してアカウントが有効であればパスワードを再発行する
//	 * @param accountId
//	 * @param emailAddress
//	 * @return　生のパスワードが返る 
//	 * 
//	 * ・8文字から16文字のパスワードを作成
//	 * ・パスワードにメールアドレスの@より前が含まれていたらパスワード再作成
//	 * ・0-9 a-z A-Zのいずれも含まれていたらパスワード再作成
//	 * 
//	 */
//	public String createNewPassword(int confereceId,String emailAddress);
//	
	
}