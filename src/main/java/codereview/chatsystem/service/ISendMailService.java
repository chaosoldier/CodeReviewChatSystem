package codereview.chatsystem.service;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author Yuta Uehara
 *
 */
@ImplementedBy(SendMailService.class)
public interface ISendMailService {
	public final String fromEmailAddress = "cist.gakkai@gmail.com";
	/**
	 * メール送信　BCC CC両方を使う
	 * @param title タイトル
	 * @param body 本文
	 * @param toAddressList　toで送るメールアドレス cist.gakkai@gmail.comで良い
	 * @param ccAddressList ccで送るメールアドレス
	 * @param bccAddressList bccで送るメールアドレスが必要
	 */
	public boolean sendMail(String title,String subject,List<String> toAddressList,List<String> ccAddressList,List<String> bccAddressList);
	/**
	 * fromEmailAddressを宛先にして、メール送信を行う。指定はBCCのみ
	 * @param title タイトル
	 * @param body 本文
	 * @param bccAddressList bccで送るメールアドレスが必要
	 */
	public boolean sendMailForBcc(String title, String body,List<String> bccAddressList);
}