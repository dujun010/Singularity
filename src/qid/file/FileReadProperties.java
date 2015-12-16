package qid.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取 .properties文件
 * 1.getPropertiesByKeyInObjectFile  根据Key读取value   文件在项目中
 * 2.getPropertiesByKey				   根据Key读取value   文件在不项目中
 * @author djun
 *
 */
public class FileReadProperties {
	private static Logger logger = Logger.getLogger(FileReadProperties.class);
	
	public static String filePath ;
	
	public FileReadProperties(String filePath) {
		super();
		FileReadProperties.filePath = filePath;
	}

	/**
	 * 根据key读取config.properties文件中的数据(文件在项目中)
	 * @param key config.properties的KEY值
	 * @return String value
	 */
	public String getPropertiesByKeyInObjectFile(String key){
		Properties pro = new Properties();
		String data = "";
		try{
			InputStream in = FileReadProperties.class.getResourceAsStream(filePath);
			pro.load(in);
			data = pro.getProperty(key).trim();
			in.close();
			return data;
		} catch(IOException e){
			e.printStackTrace();
			return data;
		}
	}
	
	/**
	 * 根据key读取config.properties文件中的数据(文件在不在项目中)
	 * @param key config.properties的KEY值
	 * @return String value
	 */
	public String getPropertiesByKey(String key){
		Map<Object, Object> map = readFileProperties();
		return (String) map.get(key) == null ? "" : (String) map.get(key);
	}
	
	public static Map<Object, Object> readFileProperties(){
		Map<Object, Object> map = new HashMap<Object, Object> ();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			fis = new FileInputStream(filePath);// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis,"UTF-8");// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
											// InputStreamReader的对象
			while ((str = br.readLine()) != null) {
				if(str.indexOf("=") > 0 && str.indexOf("#") < 0){
					map.put(str.split("=")[0].replaceAll(" ", ""),str.split("=")[1].replaceAll(" ", ""));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("找不到指定文件");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
