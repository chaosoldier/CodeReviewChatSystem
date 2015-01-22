package codereview.chatsystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
/**
* @see jp.ac.chitose.ILoginService
* @author Yuta Uehara
* @author Harunori Shibata
*/

public class SendMailService implements ISendMailService{

	public boolean sendMail(String title, String body,
			List<String> toAddressList, List<String> ccAddressList,
			List<String> bccAddressList) {
		try {
			// プロパティの設定
			Properties props = System.getProperties();
			// ホスト
			props.put("mail.smtp.host", "smtp.gmail.com");
			// 認証（する）
			props.put("mail.smpt.auth", "true");
			// ポート指定（サブミッションポート）
			props.put("mail.smtp.port", "587");
			// STARTTLSによる暗号化（する）
			props.put("mail.smtp.starttls.enable", "true");

			// セッションの取得
			Session session = Session.getInstance(props);

			// MimeMessageの取得と設定
			Message msg = new MimeMessage(session);
			// 送信者設定
			msg.setFrom(new InternetAddress("cist.gakkai@gmail.com"));
			// 宛先設定
			ArrayList toList = new ArrayList();
			for (int i = 0; i < toAddressList.size(); i++) {
				toList.add((Address) new InternetAddress(toAddressList.get(i)));
			}
			ArrayList ccList = new ArrayList();
			for (int i = 0; i < ccAddressList.size(); i++) {
				ccList.add((Address) new InternetAddress(ccAddressList.get(i)));
			}
			ArrayList bccList = new ArrayList();
			for (int i = 0; i < bccAddressList.size(); i++) {
				bccList.add((Address) new InternetAddress(bccAddressList.get(i)));
			}
			msg.setRecipients(Message.RecipientType.TO,
					(Address[]) toList.toArray(new Address[toList.size()]));
			msg.setRecipients(Message.RecipientType.CC,
					(Address[]) ccList.toArray(new Address[ccList.size()]));
			msg.setRecipients(Message.RecipientType.BCC,
					(Address[]) bccList.toArray(new Address[bccList.size()]));
			// タイトル設定
			msg.setSubject(title);
			// 本文設定
			msg.setText(body);
			// 送信日時設定
			msg.setSentDate(new Date());

			// 送信
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			try {
				t.connect("smtp.gmail.com", "cist.gakkai", "cistgakkai");
				t.sendMessage(msg, msg.getAllRecipients());
				return true;
			} finally {
				t.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendMailForBcc(String title, String body,
			List<String> bccAddressList) {
	try {
		// プロパティの設定
		Properties props = System.getProperties();
		// ホスト
		props.put("mail.smtp.host", "smtp.gmail.com");
		// 認証（する）
		props.put("mail.smpt.auth", "true");
		// ポート指定（サブミッションポート）
		props.put("mail.smtp.port", "587");
		// STARTTLSによる暗号化（する）
		props.put("mail.smtp.starttls.enable", "true");

		// セッションの取得
		Session session = Session.getInstance(props);

		// MimeMessageの取得と設定
		Message msg = new MimeMessage(session);
		// 送信者設定
		msg.setFrom(new InternetAddress(fromEmailAddress));
		// 宛先設定
		ArrayList toList = new ArrayList();
		toList.add((Address)new InternetAddress("cist.b212.uehara@gmail.com"));
		toList.add((Address)new InternetAddress(fromEmailAddress));
		ArrayList bccList = new ArrayList();
		for (int i = 0; i < bccAddressList.size(); i++) {
			bccList.add((Address) new InternetAddress(bccAddressList.get(i)));
		}
		msg.setRecipients(Message.RecipientType.TO,
				(Address[]) toList.toArray(new Address[toList.size()]));
		msg.setRecipients(Message.RecipientType.BCC,
				(Address[]) bccList.toArray(new Address[bccList.size()]));
		// タイトル設定
		msg.setSubject(title);
		// 本文設定
		msg.setText(body);
		// 送信日時設定
		msg.setSentDate(new Date());

		// 送信
		SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
		try {
			t.connect("smtp.gmail.com", "cist.gakkai", "cistgakkai");
			t.sendMessage(msg, msg.getAllRecipients());
			return true;
		} finally {
			t.close();
		}

	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}
}
