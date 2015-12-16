package qid.file;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * 读取ecxel并存入数据库
 * @author DuJun
 *
 */
public class FileExcelToDataBase {
	
	
	
	public static void main(String[] args) {
		List<String> listSql = readXml("C:/Users/djun/Desktop/地下水导入基本信息.xls", null, null);
		System.out.println("-------------");
//		readXml("d:/test2.xls");
		System.out.println(listSql.size());
 	}
	
	
	
	@SuppressWarnings("resource")
	public static List<String> readXml(String fileName,String table,String SQLColumn){
		List<String> sqlList = new ArrayList<String>();
		
		boolean isE2007 = false;	//判断是否是excel2007格式
		if(fileName.endsWith("xlsx"))
			isE2007 = true;
		try {
			InputStream input = new FileInputStream(fileName);	//建立输入流
			Workbook wb  = null;
			//根据文件格式(2003或者2007)来初始化
			if(isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);		//获得第一个表单
			Iterator<Row> rows = sheet.rowIterator();	//获得第一个表单的迭代器
			while (rows.hasNext()) {
				Row row = rows.next();	//获得行数据
				if(row.getRowNum() == 0) continue;
				System.out.println("Row #" + row.getRowNum());	//获得行号从0开始
				Iterator<Cell> cells = row.cellIterator();	//获得第一行的迭代器
				StringBuffer sb = new StringBuffer("(");//SQL语句
				while (cells.hasNext()) {
					Cell cell = cells.next();
					System.out.println("Cell #" + cell.getColumnIndex());
					switch (cell.getCellType()) {	//根据cell中的类型来输出数据
					case HSSFCell.CELL_TYPE_NUMERIC:
						if(HSSFDateUtil.isCellDateFormatted(cell)){//判断是否为时间	否则为数字
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							sb.append("'"+sdf.format(cell.getDateCellValue())+"',");
//							System.out.println(sdf.format(cell.getDateCellValue()));
						}else {
							sb.append(cell.getNumericCellValue()+",");
//							System.out.println(cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_STRING:
						sb.append("'"+cell.getStringCellValue()+"',");
//						System.out.println(cell.getStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						sb.append(cell.getBooleanCellValue()+",");
//						System.out.println(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						sb.append("'"+cell.getCellFormula()+"',");
//						System.out.println(cell.getCellFormula());
						break;
					default:
						sb.append("null,");
//						System.out.println("unsuported sell type");
					break;
					}
				}
				sb.append(")");
				
				
				//处理SQL语句
				String sql1 = "INSERT INTO "+ table + SQLColumn!=null && !SQLColumn.equals("") ? SQLColumn : "";				
				String sql = sql1+ "VALUES" +sb.toString().substring(0, sb.toString().length()-2)+")";
				sqlList.add(sql);
				System.out.println(sql);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sqlList;
	}
	
	public void configDataBase(){
		
	}
	
	
	
	
	
}