package qid.file;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * ClassName:ExcelFileParser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年12月29日 上午9:26:21 <br/>
 * 
 * @version
 * @since JDK 1.8
 * @see
 */
public class FileReadExcel {

	/**
	 * 创建工作簿对象
	 * @author djun
	 * @param path
	 * @return
	 */
	public static Workbook getWb(String path) {
		try {
			return WorkbookFactory.create(new File(path));
		} catch (Exception e) {
			throw new RuntimeException("读取EXCEL文件出错", e);
		}
	}
	/**
	 * 获取sheet页
	 * @author djun
	 * @param wb	工作簿对象
	 * @param sheetIndex	读取的sheet页索引
	 * @return
	 */
	public static Sheet getSheet(Workbook wb, int sheetIndex) {
		if (wb == null) {
			throw new RuntimeException("工作簿对象为空");
		}
		int sheetSize = wb.getNumberOfSheets();
		if (sheetIndex < 0 || sheetIndex > sheetSize - 1) {
			throw new RuntimeException("工作表获取错误");
		}
		return wb.getSheetAt(sheetIndex);
	}
	
	/**
	 * 读取内容
	 * @author djun
	 * @param sheet	读取的sheet页索引
	 * @param startLine		开始行号
	 * @param endLine		结束行号
	 * 如果开始行号和结束行号都是-1的话，则全表读取
	 * @return
	 */
	public static List<List<String>> getExcelRows(Sheet sheet, int startLine, int endLine) {
		List<List<String>> list = new ArrayList<List<String>>();
		if (startLine == -1)
			startLine = 0;
		if (endLine == -1) {
			endLine = sheet.getLastRowNum() + 1;
		} else {
			endLine += 1;
		}
		for (int i = startLine; i < endLine; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				System.out.println("该行为空，直接跳过");
				continue;
			}
			int rowSize = row.getLastCellNum();
			List<String> rowList = new ArrayList<String>();
			for (int j = 0; j < rowSize; j++) {
				Cell cell = row.getCell(j);
				String temp = "";
				if (cell == null) {
					System.out.println("该列为空，赋值双引号");
					temp = "NULL";
				} else {
					int cellType = cell.getCellType();
					switch (cellType) {
					case Cell.CELL_TYPE_STRING:
						temp = cell.getStringCellValue().trim();
						temp = StringUtils.isEmpty(temp) ? "NULL" : temp;
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						temp = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						temp = String.valueOf(cell.getCellFormula().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
							temp = sdf.format(cell.getDateCellValue());
						} else {
							temp = new DecimalFormat("#.######").format(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						temp = "NULL";
						break;
					case Cell.CELL_TYPE_ERROR:
						temp = "ERROR";
						break;
					default:
						temp = cell.toString().trim();
						break;
					}
				}
				rowList.add(temp);
			}
			list.add(rowList);
		}
		return list;
	}

	public static void main(String a[]) {
		String path = "C:\\Users\\djun\\Desktop\\test.xlsx";
		Workbook wb = getWb(path);
		Sheet sheet = getSheet(wb, 0);
		List<List<String>> list = getExcelRows(sheet, -1, -1);
		for (int i = 0; i < list.size(); i++) {
			List<String> row = list.get(i);
			if(row.size() == 0 || row.get(0).getBytes().length != row.get(0).length()) continue;//过滤掉第一列以中文开头的列   所有字段列是以数字开头
			for (int j = 0; j < row.size(); j++) {
				System.out.print(row.get(j) + "\t");
			}
		}
	}

}
