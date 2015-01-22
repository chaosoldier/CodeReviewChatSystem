package codereview.chatsystem.service;

import java.lang.annotation.Documented;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;









import lombok.extern.log4j.Log4j;
import codereview.chatsystem.bean.AccountBean;
import codereview.chatsystem.dao.AccountDAO;
import codereview.chatsystem.dao.IAccountDAO;
import codereview.chatsystem.definition.Encrypter;

import com.google.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * @author yuta ueara
 *
 */
@Log4j
public class LoginService implements ILoginService {

	private static final long serialVersionUID = 4513853891541826353L;
//
	@Inject
	private IAccountDAO adao;
//	@Inject
//	private IConferenceEntryDAO cedao;
	@Override
	public boolean loginAsUserAccount(String email, String pass) {
		try {
			pass = convertPass(pass, email, adao.affirmEncrypter(email));
			if(adao.login(email, pass)){
				return true;
			}
				
			log.info(pass);
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if (adao.login(email, pass)) {
//			return true;
//			// 論理削除判定用
//			 if (accountDao.isAccountActive(accountId)) {
//				 loginEnable = true;
//			 }
//		
		return false;
	}
//
//	@Override
//	public boolean login(String email, String pass, int conferenceId) {
//		try {
//			if (!checkAllowdConference(conferenceId, email)) {
//				// 参加状態と論理削除が両方TRUEではない
//				return false;
//			}
//			// ログイン問い合わせ
//			if (cedao.login(
//					conferenceId,
//					email,
//					convertPass(pass, email,
//							cedao.affirmEncrypter(conferenceId, email)))) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public boolean loginAsSystemAccount(String email, String pass) {
//		try {
//			
//			pass = convertPass(pass, email, adao.searchAccountEncrypter(email));
//			if (adao.login(email, pass)) {
//				return true;
//				// 論理削除判定用
//				// if (accountDao.isAccountActive(accountId)) {
//				// loginEnable = true;
//				// }
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//
//	//TODO 削除予定
////	@Override
////	public String checkGeneralUserAuthrity(int conferenceId, int accountId) {
////		String GeneralUserAuthrity = "";
////		try {
////			GeneralUserAuthrity = entrymemDao.searchConferenceAccountRole(
////					conferenceId, accountId);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} catch (NullPointerException e) {
////			e.printStackTrace();
////
////		}
////		return GeneralUserAuthrity;
////	}
//
//	@Override
//	public boolean checkAllowdConference(int conferenceId, String email) {
//		try {
//			if (cedao.affirmEnterable(conferenceId, email)) {
//				if (cedao.affirmAvailable(conferenceId, email)) {
//					return true;
//				}
//			}
//			return false;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	@Deprecated
//	public int getAccountId(String email, String pass) {
//		int accounId = 0;
//		accounId = 1;
//		// TODO change method
//		// try {
//		//
//		// accounId = accountDao.searchAccount(
//		// email,
//		// convertPass(pass, email,
//		// accountDao.affirmEncrypter(email)));
//		// } catch (SQLException e) {
//		// e.printStackTrace();
//		// } catch (NullPointerException e) {
//		// e.printStackTrace();
//		//
//		// }
//		return accounId;
//	}
//
//	@Inject
//	private IPersonalDAO personalDao;
//
//
//	@Override
//	public PasswordForNewAccountCreateFormPageBean convertPassForCreateNewAccount(
//			String password, String emailAddress) {
//		String errorMsg = "";
//
//		final EmailValidator emailValidator = EmailValidator.getInstance();
//		if (!emailValidator.isValid(emailAddress)) {
//			errorMsg = errorMsgEmailInvaild;
//			return new PasswordForNewAccountCreateFormPageBean("", null,
//					errorMsg);
//		}
//		if (password.contains(emailAddress.substring(0,
//				emailAddress.indexOf("@")))) {
//			errorMsg = errorMsgPassHasEmailInvaild;
//			return new PasswordForNewAccountCreateFormPageBean("", null,
//					errorMsg);
//		}
//		int level = CheckPasswordSafety(password);
//		if (2 > level) {
//			errorMsg = String.valueOf(level)+"レベルです。英数字を混ぜたパスワードを作成してください";
//			return new PasswordForNewAccountCreateFormPageBean("", null,
//					errorMsg);
//		}
//		if (!password.matches("^["+allwedPasswordStringIfCreate+"]+$")) {
//			errorMsg = errorMsgPassHasCharInvaild;
//			return new PasswordForNewAccountCreateFormPageBean("", null,
//					errorMsg);
//		}
//		// 問題なければ返す
//		return new PasswordForNewAccountCreateFormPageBean(convertPass(
//				password, emailAddress, CURRENT_TYPE), CURRENT_TYPE, errorMsg);
//	}
//	 public int CheckPasswordSafety( String passwd )
//	    {
//	        if( "" == passwd || null == passwd )
//	        	return 0;
//	    	
//	        // 0～32と127は制御文字というもので、例えば10のLF は改行(Line Feed)のこと。33～126は記号、数字、アルファベットだよ。詳しいことは教科書やwebで調べてみよう。
//	        Pattern p = Pattern.compile("\\s");
//	        Matcher m = p.matcher(passwd);
//			if( m.find() )
//				return 0;
//
//	        int level = 0;
//
//	        // 小文字が含まれているか
//	        Pattern p2 = Pattern.compile("[a-zA-Z]");
//	        Matcher m2 = p2.matcher(passwd);
//	        if( m2.find() )
//	        	level++;
//
////	        // 大文字が含まれているか
////	        Pattern p3 = Pattern.compile("[A-Z]");
////	        Matcher m3 = p3.matcher(passwd);
////	        if( m3.find() )
////	        	level++;
//	        
//	        // 数字が含まれているか
//	        Pattern p4 = Pattern.compile("[0-9]");
//	        Matcher m4 = p4.matcher(passwd);
//	        if( m4.find() )
//	        	level++;
//	        
//	        // 記号が含まれているか
////	        Pattern p5 = Pattern.compile(".*[$@#].*");
//////	        Pattern p5 = Pattern.compile("\\W");
////	        Matcher m5 = p5.matcher(passwd);
////	        if( m5.find() )
////	        	level++;
//	        
//	        return level;
//	    }
//
	@Override
	public String convertPass(String pass, String email, Encrypter TYPE) {
		log.info(TYPE);
		try {
			if (TYPE == Encrypter.TYPE1) {
				return getSaltedPasswordVer1(pass, email);
			} else if (TYPE == Encrypter.TYPE2) {
				return getStretchedPasswordVer2(pass, email);
			} else {
				return "dWeFJZviuZYoLPaGfKPU3P8XLaDmvgsx";
			}
			// switch (TYPE.getEncrypter()) {
			// case TYPE1:
			// return getSaltedPasswordVer1(pass, email);
			// case TYPE2:
			// return getStretchedPasswordVer2(pass, email);
			// default:
			// // 不正なアカウント
			// return "dWeFJZviuZYoLPaGfKPU3P8XLaDmvgsx";
			// }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			log.info("失敗");
			// 空文字を返してまうのはまずいので
			return "dWeFJZviuZYoLPaGfKPU3P8XLaDmvgsx";
		}
	}

	/*
	 * バージョン2
	 * 
	 * salt＋ハッシュ化したパスワードを取得
	 */
	public static String getSaltedPasswordVer1(String password, String email) {
		// バージョン2
		String salt = getSha256(email);
		return getSha256(salt + password);
	}

	/*
	 * salt + ストレッチングしたパスワードを取得
	 */
	private static String getStretchedPasswordVer2(String password, String email) {
		// バージョン1
		String salt = getSha256(email);
		String hash = "";

		for (int i = 0; i < TYPE2_STRETCH_COUNT; i++) {
			hash = getSha256(hash + salt + password);
		}

		return hash;
	}

	/*
	 * @see getStretchedPasswordVer2() 文字列から SHA256 のハッシュ値を取得
	 */
	private static String getSha256(String target) {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				buf.append(String.format("%02x", digest[i]));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return buf.toString();
	}
//
//	private String CreatePassword(String emailAddress) {
//		String password;
//		// パスワード作成　※下でも再作成を行っている
//		password = RandomStringUtils.random(RandomUtils.nextInt(8, 16),
//				allwedPasswordStringIfReCreate);
//		// パスワードチェック
//		// パスワードにメールアドレスの@より前が含まれていたらだめ
//		int cntContainEmail = 0;
//		// 含まれてしまっている限り30回までパスワード再発行
//		while (password.contains(emailAddress.substring(0,
//				emailAddress.indexOf("@")))) {
//			password = RandomStringUtils.random(RandomUtils.nextInt(8, 16),
//					allwedPasswordStringIfReCreate);
//			if (cntContainEmail > 30) {
//				// TODO ログを残してSYS管理者に通告
//				log.info("アルゴリズムに問題が発生している可能性がある");
//				// TODO return文、要再検討
//				return "アルゴリズムに問題が発生している可能性がある";
//			}
//			cntContainEmail++;
//		}
//
//		// パスワードチェック
//		// 0-9　a-z　A-Z　のいずれも含むかつ８文字以上,16文字以下か判定
//		int cntCheckPass = 0;// while文の確認用
//		// 含まれてしまっている限り30回までパスワード再発行
//		while (!password
//				.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,16}$")) {
//			password = RandomStringUtils.random(RandomUtils.nextInt(8, 16),
//					allwedPasswordStringIfReCreate);
//			if (cntCheckPass > 30) {
//				// TODO ログを残してSYS管理者に通告
//				log.info("アルゴリズムに問題が発生している可能性がある");
//				// TODO return文、要再検討
//				return "アルゴリズムに問題が発生している可能性がある";
//			}
//			cntCheckPass++;
//		}
//		return password;
//	}
//
//	@Override
//	public String createNewPassword(int confereceId, String emailAddress) {
//		final EmailValidator emailValidator = EmailValidator.getInstance();
//		if (!emailValidator.isValid(emailAddress)) {
//			log.info("emailaddressに問題がある");
//			return "emailaddressに問題がある";
//		}
//		try {
//			// 問い合わせしてもいいか確認
//			if (checkAllowdConference(confereceId, emailAddress)) {
//				String password = CreatePassword(emailAddress);
//				// 問題がなければ
//				// 最新バージョンのアルゴリズムでパスワードをデータベースに設定
//				cedao.updatePassword(confereceId, emailAddress,
//						convertPass(password, emailAddress, CURRENT_TYPE),
//						CURRENT_TYPE);
//				return password;
//			} else {
//				// TODO return文、要再検討
//				return "アカウントが無効である";
//			}
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//			// TODO return文、要再検討
//			return "アカウントが無効である";
//		}
//	}
//
//	@Override
//	public String createNewPassword(String emailAddress) {
//		final EmailValidator emailValidator = EmailValidator.getInstance();
//		if (!emailValidator.isValid(emailAddress)) {
//			log.info("emailaddressに問題がある");
//			return "emailaddressに問題がある";
//		}
//		try {
//			if (adao.isAccountActive(emailAddress)) {
//				String password = CreatePassword(emailAddress);
//				// 問題がなければ
//				// 最新バージョンのアルゴリズムでパスワードをデータベースに設定
//				adao.updatePassword(emailAddress,
//						convertPass(password, emailAddress, CURRENT_TYPE),
//						CURRENT_TYPE);
//				return password;
//			} else {
//				// TODO return文、要再検討
//				return "アカウントが無効である";
//			}
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//			// TODO return文、要再検討
//			return "アカウントが無効である";
//		}
//	}
//
//	// TODO パスワードのチェックリストJUnitで使える？
//	// checkList CASE
//	// 0-9　a-z　A-Z　のいずれも含むかつ８文字以上,16文字以下か判定
//	// List<String> passCheckList = new ArrayList<String>();
//	// //ok case
//	// passCheckList.add("1bA45678");//8char 3type OK
//	// passCheckList.add("1cC456789");//9char OK
//	// passCheckList.add("1bcdefghiABCDEFG");//16char 3type OK
//	// passCheckList.add("1bcdefghiABCDEF");//15char 3type OK
//	// //bad case
//	// //special case
//	// passCheckList.add("");//8char
//	// passCheckList.add("       ");//8char
//	// passCheckList.add("　　　　　　　　");//8char
//	// //type check
//	// passCheckList.add("ABCDEFGH");//8char 1type
//	// passCheckList.add("abcdefgh");//8char 1type
//	// passCheckList.add("12345678");//8char 1type
//	// passCheckList.add("ABCDEFGh");//8char 2type
//	// passCheckList.add("abcdefg8");//8char 2type
//	// passCheckList.add("A2345678");//8char 2type
//	// //length check
//	// passCheckList.add("1aA4567");//7char 3type
//	// passCheckList.add("1bcdefghiABCDEFGH");//17char 3type not clear
//	//
//	// for(String checkedPass:passCheckList){
//	// log.info((checkedPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,16}$"))?
//	// "OK："+checkedPass:"NG："+checkedPass);
//	// }
//	@Override
//	public ConferenceEntryBean getConferenceEntryBean(int conferenceId,
//			String email) {
//		try {
//			return cedao.selectConferenceEntryBean(conferenceId, email);
//		} catch (NullPointerException | SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
	@Override
	public AccountBean getAccountBean(String email) {
		try {
			return adao.selectAccountBean(email);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean updatePassword(String email, String pass) {
		try {
			adao.updatePassword(email, convertPass(pass,email,CURRENT_TYPE), CURRENT_TYPE);
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}



}