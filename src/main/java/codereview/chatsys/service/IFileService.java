package codereview.chatsys.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface IFileService {
	/**
	 * @return パスを返す　ディレクトリを環境ごとに追加する必要あり
	 */
	public String getRootPath();
	/**
	 * @param dir　ex) uploaded/ or download/
	 *             ¥を使ったパスを指定しないでください
	 *             パス指定した先のフォルダが存在していないとエラーになります
	 * @return フルパスを返す
	 */
	public String getFullPath(String dir);
	/**
	 * @param readFilePath
	 *            検査対象のファイルパス（拡張子含む）
	 * @param type
	 *            sha-256：sha-256の処理を行う(.sha256 推奨) 
	 *            　       shasum -a 256 filename onMac
	 *            md5:sha-256の処理を行う(.md5 推奨) 
	 *            
	 * @throws IOException
	 * 
	 * @return ハッシュ
	 * 
	 */
	public String createHash(String readFilePath, String type);
	/**
	 * @param readFilePath
	 *            検査対象のファイルパス（拡張子含む）
	 * @param outputFilePath
	 *            　出力したいファイルパス（拡張子含む）
	 * @param type
	 *            sha-256：sha-256の処理を行う(.sha256 推奨) 
	 *            　       shasum -a 256 filename onMac
	 *            md5:sha-256の処理を行う(.md5 推奨) 
	 *            
	 * @throws IOException
	 * 
	 *  @return ハッシュ
	 * 
	 */
	public String createHashFile(String readFilePath, String outputFilePath,
			String type);
	

}
