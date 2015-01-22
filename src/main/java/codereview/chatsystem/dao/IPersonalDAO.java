package codereview.chatsystem.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;



import codereview.chatsystem.bean.PersonalBean;

import com.google.inject.ImplementedBy;

@ImplementedBy(PersonalDAO.class)
public interface IPersonalDAO {

	public void insertpaper() throws SQLException,NullPointerException,IOException;
	public void serectpaper() throws SQLException,NullPointerException, IOException;
	/**
	 * アカウントIDを指定してpersonal tableのデータを取得
	 * @param accountId
	 * @return 
	 */
	public PersonalBean selectPersonal(int accountId) throws SQLException,IOException;
	public void DeleteAllPaper(int id);
	public List<PersonalBean> SelectAllPaper();
	public void updateAvailablePaper(int paper_id)
			throws SQLException, NullPointerException;
	/**
	 * アカウントIDを指定してアバターを更新
	 * @param account_id
	 * @param data
	 * @param updated_at
	 * @param file_name
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public void updateAvatar(int account_id, byte[] data,Timestamp updated_at,String file_name)throws SQLException, NullPointerException;
	public void insertpaper(int publication_id,byte[] data,Timestamp updata_at,String caption,String summary,String dataname) throws SQLException,NullPointerException, IOException;
}
