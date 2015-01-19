package codereview.chatsys.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.log4j.Log4j;

@Log4j
public class FileService implements IFileService {
	/**
	 * @return パスを返す　ディレクトリを環境ごとに追加する必要あり
	 */
	@Override
	public String getRootPath(){
		String osName = System.getProperty("os.name");
		String buildpath=null;
		if(osName.indexOf("Windows")>=0){
		  // Windowsであったときの処理
			buildpath = "C://contents//8u//";
		} else if(osName.indexOf("Linux")>=0){
			buildpath = "/contents/8u/";
		  // Linuxであったときの処理
		} else if(osName.indexOf("Mac")>=0){
		  // MacOSであったときの処理
			buildpath = "/contents/8u/";
		} else {
		  // その他の環境だったときの処理
			buildpath = "/contents/8u/";
		}
		return buildpath;
	}

	/**
	 * @param dir　ex) uploaded/ or download/
	 *             ¥を使ったパスを指定しないでください
	 *             パス指定した先のフォルダが存在していないとエラーになります
	 * @return フルパスを返す
	 */
	@Override
	public String getFullPath(String dir){
		String osName = System.getProperty("os.name");
		StringBuilder buildpath = new StringBuilder();
		if(osName.indexOf("Windows")>=0){
		  // Windowsであったときの処理
			buildpath.append("C://contents//8u//");
			buildpath.append(dir.replaceAll("/","//"));
		} else if(osName.indexOf("Linux")>=0){
			buildpath.append("/contents/8u/");
			buildpath.append(dir);
		  // Linuxであったときの処理
		} else if(osName.indexOf("Mac")>=0){
		  // MacOSであったときの処理
			buildpath.append("/contents/8u/");
			buildpath.append(dir);
		} else {
		  // その他の環境だったときの処理
			buildpath.append("/contents/8u/");
			buildpath.append(dir);
		}
		return buildpath.toString();
	}
	@Override
	public String createHash(String readFilePath, String type) {
		File readFile = new File(readFilePath);
		String digestStr;
		try {
			if (type.equals("sha-256")) {
				digestStr = printDigest(getFileDigest(readFile));
			} else if (type.equals("md5")) {
				digestStr = readInputStreamMd5(
						new FileInputStream(readFilePath), 1024);
			} else {
				log.info("type is not set");
				return "No-Digest";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "No-Digest";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "No-Digest";
		}
		return digestStr;
	}

	@Override
	public String createHashFile(String readFilePath, String outputFilePath,
			String type) {
	File outputFile = new File(outputFilePath);
	String digestStr = createHash(readFilePath, type);
	PrintStream ps = null;
	try {
		// Streamを作成します。
		ps = new PrintStream(new FileOutputStream(outputFile), false,
				"windows-31j");
		ps.println(digestStr);
	} catch (IOException e) {
		e.printStackTrace();;
	} finally {
		if (ps != null) {
			try {
				ps.flush();
			} catch (Exception e) {
			}
			try {
				ps.close();
			} catch (Exception e) {
			}
		}
	}
	return digestStr;
	}
	private static final int BUFFER_SIZE = 256;

	/**
	 * ファイルを読み込みながら、MD5も生成する.
	 * 
	 * @param is
	 *            ファイル読み込みのInputStreamオブジェクト
	 * @param available
	 *            確保するメモリサイズ.
	 * @return ファイルの中身とMD5文字列を格納したDTO
	 * @throws NoSuchAlgorithmException
	 *             アルゴリズムが発見できない
	 * @throws IOException
	 *             ファイル読み込みエラー
	 */
	public static String readInputStreamMd5(InputStream is, int available)
			throws NoSuchAlgorithmException, IOException {
		String resultStr;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		if (is == null) {
			throw new IllegalArgumentException("InputStream must not null");
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			is = new DigestInputStream(is, md);

			byte[] buffer = new byte[BUFFER_SIZE];
			while (is.read(buffer, 0, buffer.length) != -1) {
				os.write(buffer, 0, buffer.length);
			}
			os.flush();

			// MD5を取得.
			byte[] hash = md.digest();
			resultStr = hashByteToMD5(hash);
		} finally {
			if (os != null) {
				os.close();
			}
			is.close();
		}
		return resultStr;
	}

	/**
	 * MD5変換.
	 * 
	 * @param source
	 *            変換したい文字列
	 * @return MD5変換後の文字列
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalArgumentException
	 */
	public static String convertToMD5(String source)
			throws NoSuchAlgorithmException, IllegalArgumentException {
		if (source == null || source.length() == 0) {
			throw new IllegalArgumentException("You must input String");
		}

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(source.getBytes());
		byte[] hash = md.digest();

		return hashByteToMD5(hash);
	}

	/**
	 * Byte配列を文字列に変換する. 0x10以下の時は、0をつけて表示する.
	 * 
	 * @param hash
	 *            MD5変換後のbyte配列
	 * @return MD5変換後の文字列
	 */
	public static String hashByteToMD5(byte[] hash) {
		StringBuilder builder = new StringBuilder();
		for (int idx = 0; idx < hash.length; idx++) {
			if ((0xff & hash[idx]) < 0x10) {
				builder.append("0" + Integer.toHexString((0xff & hash[idx])));
			} else {
				builder.append(Integer.toHexString((0xff & hash[idx])));
			}
		}
		return builder.toString();
	}

	/** Algorithm */
	private static final String ALGORITHM = "SHA-256";

	/**
	 * チェックサムファイルを生成します。
	 * 
	 * @param src
	 *            生成元のファイル
	 * @param sum
	 *            生成するチェックサムファイル
	 */

	/**
	 * ファイルからダイジェストを生成します。
	 * 
	 * @param path
	 *            ダイジェストを生成するファイル
	 * @return ダイジェスト(16進数)
	 */
	private static byte[] getFileDigest(File path) throws Exception {

		// ファイルの中身からダイジェストを生成する
		MessageDigest md = MessageDigest.getInstance(ALGORITHM);

		FileInputStream in = null;
		try {
			in = new FileInputStream(path);

			// dat配列の先頭からlenまでのダイジェストを計算する
			byte[] dat = new byte[256];
			int len;
			while ((len = in.read(dat)) >= 0) {
				md.update(dat, 0, len);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return md.digest();
	}

	/**
	 * ダイジェスト(16進数)からダイジェスト(文字列)を生成します。
	 * 
	 * @param digest
	 *            ダイジェスト(16進数)
	 * @return ダイジェスト(文字列)
	 */
	private static String printDigest(byte[] digest) {
		String sSum = "";
		for (int i = 0; i < digest.length; i++) {

			// byte型では128～255が負値になっているので補正
			int d = digest[i];
			if (d < 0) {
				d += 256;
			}

			// 0～15は16進数で1桁のため、2桁になるよう頭に0を追加
			if (d < 16) {
				sSum += "0";
			}
			sSum += Integer.toString(d, 16);
		}

		return sSum;
	}
}
