package qid.excel.singularity.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import qid.excel.singularity.util.ExcelUtilGET;


public class ExportTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		
		User uuu1 = new User();
		uuu1.setId("1");
		uuu1.setName("张三");
		uuu1.setUserName("zhangsan");
		uuu1.setPassWord("zhangsan1");
		uuu1.setSex(1);
		uuu1.setDuty(1);
		uuu1.setState(1);
		uuu1.setiCd("111011111255545548");
		uuu1.setBirthDate("2016-09-13");
		uuu1.setEmal("zhangsan@water.com");
		uuu1.setPhone("13333333333");
		uuu1.setOrgName("水利局3部");
		uuu1.setRoleName("职员");
		uuu1.setTs("2016-09-13");
		uuu1.setData(3.14d);
		list.add(uuu1);
		
		User uuu2 = new User();
		uuu2.setId("2");
		uuu2.setName("张四");
		uuu2.setUserName("lisi");
		uuu2.setPassWord("lisi4");
		uuu2.setSex(1);
		uuu2.setDuty(1);
		uuu2.setState(2);
		uuu2.setiCd("254585554585554563");
		uuu2.setBirthDate("2015-06-26");
		uuu2.setEmal("lisi@water.com");
		uuu2.setPhone("14444444444");
		uuu2.setOrgName("水利局4部");
		uuu2.setRoleName("职员");
		uuu2.setTs("2016-09-13");
		uuu2.setData(1258.141d);
		list.add(uuu2);
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("d:\\用户信息.xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ExcelUtilGET<User> util = new ExcelUtilGET<User>(User.class);// 创建工具类.
		util.exportExcel(list, "员工信息", 65536, out,null ,null);// 导出
		System.out.println("----执行完毕----------");
	}

}
