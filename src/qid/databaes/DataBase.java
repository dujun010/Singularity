package qid.databaes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

/**
 * 数据库操作类
 * 1.RunSqlFile	  			运行SQL文件并存入数据库
 * 2.configDataBaseTest		测试数据库连接
 * @author djun
 *
 */
public class DataBase {
	
	/**
	 * 运行SQL文件并存入数据库
	 * 
	 * @param filePath	文件路径+文件名
	 * @param driver	数据库驱动
	 * @param url		数据库地址
	 * @param userName	数据库名称
	 * @param passWord	数据库密码
	 * @throws IOException
	 */
	public static void RunSqlFile(String filePath,String driver,String url,String userName,String passWord) throws IOException{
		if(filePath.indexOf(".sql") <= 0) throw new IOException("File not found at the end of \".sql\" ");
		
		SQLExec sqlExec = new SQLExec();
		//设置数据库参数  
		sqlExec.setDriver(driver);  
		sqlExec.setUrl(url);  
		sqlExec.setUserid(userName);  
		sqlExec.setPassword(passWord);  
		//要执行的脚本  
		sqlExec.setSrc(new File(filePath));  
		//有出错的语句该如何处理  
		sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(  
		SQLExec.OnError.class, "abort")));  
		sqlExec.setPrint(true); //设置是否输出  
		//输出到文件 sql.out 中；不设置该属性，默认输出到控制台  
		//sqlExec.setOutput(new File("src/sql.out"));  
		sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错  
		sqlExec.execute();
	}
	
	/**
	 * 测试数据库连接
	 * @param driver 	数据库驱动
	 * @param url		数据库地址
	 * @param user		数据库名称
	 * @param password	数据库密码
	 * @param tableName 数据库表名
	 */
	public static void configDataBaseTest(String driver,String url,String user,String password,String tableName) {
//	    String driverClassName = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://192.168.1.4:3306/MYWATER?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
//		String user= "root";
//		String password = "jmsd123";
		
		try {
		    Class.forName(driver);
		} catch (ClassNotFoundException ex) {
		    System.out.println("加载错误！");
		}
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, password);
		    System.out.println("连接成功");
		    
		    String sql = "SELECT * FROM "+tableName;
		    PreparedStatement ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    //ResultSet rs=ps.executeQuery();
		    ps.executeQuery();
		    ResultSetMetaData md = ps.getMetaData();  //ps.getMetaData();//
		    for (int i = 1; i <= md.getColumnCount(); i++) {
		        System.out.println("name:"+md.getColumnName(i)+"   label:"+md.getColumnLabel(i));
		    }
		} catch (SQLException ex1) {
		    System.out.println(ex1);
		    System.out.println("失败");
	    }
	}
}
