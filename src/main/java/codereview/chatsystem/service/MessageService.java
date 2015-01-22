package codereview.chatsystem.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.log4j.Log4j;

import org.eclipse.jetty.util.log.Log;
import org.postgresql.util.PSQLException;

import sun.util.logging.resources.logging;
import codereview.chatsystem.bean.CheckMessageBean;
import codereview.chatsystem.bean.MessageBean;
import codereview.chatsystem.bean.MessageFileBean;
import codereview.chatsystem.bean.ReadMessageBean;
import codereview.chatsystem.dao.ICheckMessageDAO;
import codereview.chatsystem.dao.IMessageDAO;
import codereview.chatsystem.dao.IMessageFileDAO;
import codereview.chatsystem.dao.IReadMessageDAO;

import com.google.inject.Inject;
import com.sun.mail.smtp.SMTPTransport;

/**
 * @see jp.ac.chitose.ILoginService
 * @author Yuta Uehara
 * @author Harunori Shibata
 */

@Log4j
public class MessageService implements iMessageService {
	@Inject
	IMessageDAO mdao;
	@Inject
	IMessageFileDAO mfdao;

	@Override
	public int setMessage(MessageBean mbean) {
		try {
			if (mdao.insertMessage(mbean) > 0) {
				return mdao.insertMessage(mbean);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean setMessageFile(MessageFileBean mfbean) {
		try {
			mfdao.insertMessageFile(mfbean);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	List<MessageBean> mbeanList;

	@Override
	public List<MessageBean> getAllMessageBeans() {
		try {
			return mdao.selectAllMessage();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Inject
	IReadMessageDAO iReadMsseageService;

	@Override
	public void setReadMessage(int account_id) {
		List<ReadMessageBean> rmbeans = new ArrayList<ReadMessageBean>();
		try {
			for (MessageBean mbean : mdao.selectAllMessage()) {
				// メッセージリストから既読リストを作成している
				rmbeans.add(new ReadMessageBean(mbean.getMessage_id(),
						account_id, new Timestamp(System.currentTimeMillis())));
			}
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReadMessageBean bean : rmbeans) {
			try {
				// 作成した既読リストを元にSQL
				// log.info(bean.getInserted_at().toString());
				iReadMsseageService.insertReadMessage(bean);
			} catch (NullPointerException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int countReadPerMessage(int message_id) {
		try {
			return iReadMsseageService.countReadPerMessage(message_id);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Inject
	ICheckMessageDAO iChgeckMsseageService;
	@Override
	public void setCheckMessage(CheckMessageBean cmbean) {
		try {
			iChgeckMsseageService.insertCheckMessage(cmbean);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int countCheckPerMessage(int message_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCheckedMessage(int message_id, int account_id) {
		try {
			return iChgeckMsseageService.isCheckedMessage(message_id, account_id);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isThisAcountMessage(int message_id, int account_id) {
		// TODO Auto-generated method stub
		return false;
	}

}
