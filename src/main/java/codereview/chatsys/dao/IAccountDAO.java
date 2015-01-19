package codereview.chatsys.dao;

import java.sql.SQLException;

import codereview.chatsys.definition.Encrypter;

import com.google.inject.ImplementedBy;
/**
 * @author Yuta Uehara
 */
@ImplementedBy(AccountDAO.class)
public interface IAccountDAO {
	
	/**
	 * ログイン可能であるか調査
	 * @param email
	 * @param password 暗号化される前のパスワード
	 * @return　成功した時true
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public boolean login(String email,String password) throws SQLException, NullPointerException;
//	public int searchAccount(String email, String pass) throws SQLException,NullPointerException;
	/**
	 * 暗号化アルゴリズムのTYPEを取得
	 * @param email_address
	 * @return enum判定用のTYPE 一致しない場合はnull
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public Encrypter affirmEncrypter(String email) throws SQLException,NullPointerException;
//	/**
//	 *
//	 * パスワード再発行用
//	 * @param email
//	 * @return
//	 * @throws SQLException
//	 * @throws NullPointerException
//	 */
//	public int searchAccountForRecreatePassword(String email)throws SQLException,NullPointerException;
//	public boolean isAccountActive(String email) throws SQLException,NullPointerException;
////	public void test() throws SQLException;
//	public Encrypter searchAccountEncrypter(String email) throws SQLException,NullPointerException;
//	/**
//	 * パスワード更新用
//	 * @param accountId
//	 * @param pass
//	 * @param currentAccountVersion
//	 * @throws SQLException
//	 */
//	public void updatePassword(String email,String pass,Encrypter currentEncrypter)throws SQLException;
//	public void updateEmailAddress(String email,String address)throws SQLException;
//	public boolean insertAccount(String email, String pass, String encrypter) throws SQLException,NullPointerException;
//	public String selectEmailAddress(int accountId) throws SQLException,NullPointerException;
//	public int selectAccountId(String email) throws SQLException,NullPointerException;
//
//	/**
//	 * アカウントのdelete文（account_idが1の時は削除されずに終了する）
//	 * @param accountId
//	 * @param currentAccountVersion
//	 * @throws SQLException
//	 */
//
//	public void deleteAccount(int account_id) throws SQLException;

}