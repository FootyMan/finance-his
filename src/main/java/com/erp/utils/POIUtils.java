package com.erp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtils {

	public final static String DATE_OUTPUT_PATTERNS = "yyyy/MM/dd HH:mm";
	public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_OUTPUT_PATTERNS);

	public static List<List<Object>> readXlsx(InputStream is, int startRow, int endCell) {
		List<List<Object>> ans = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			for (int sheetNum = 0; sheetNum < xssfWorkbook.getNumberOfSheets(); sheetNum++) {
				XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null)
						continue;
					List<Object> list = new ArrayList<>();
					if (endCell == -1) {
						endCell = row.getLastCellNum();
					}
					for (int cellNum = 0; cellNum < endCell; cellNum++) {
						XSSFCell cell = row.getCell(cellNum);

						if (cell == null) {
							list.add("");
							continue;
						}
						System.out.println(cell);
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							String value = cell.getStringCellValue();
							list.add(value);
						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							Object value = "";
							if (DateUtil.isCellDateFormatted(cell)) {
								Date theDate = cell.getDateCellValue();
								value = simpleDateFormat.format(theDate);
								list.add(value.toString());
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								String temp = cell.getStringCellValue();
								// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
								if (temp.indexOf(".") > -1) {
									value = String.valueOf(new Double(temp)).trim();
								} else {
									value = temp.trim();
								}
								list.add(value);
							}

						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
							boolean value = cell.getBooleanCellValue();
							list.add(value);
						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR
								|| cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							String value = "";
							list.add(value);
						}
					}
					ans.add(list);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ans;
	}
	public static List<List<Object>> readXlsx2003(InputStream is, int startRow, int endCell) {
		List<List<Object>> ans = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			HSSFWorkbook xssfWorkbook = new HSSFWorkbook(is);
			for (int sheetNum = 0; sheetNum < xssfWorkbook.getNumberOfSheets(); sheetNum++) {
				HSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
					HSSFRow row = sheet.getRow(rowNum);
					if (row == null)
						continue;
					List<Object> list = new ArrayList<>();
					if (endCell == -1) {
						endCell = row.getLastCellNum();
					}
					for (int cellNum = 0; cellNum < endCell; cellNum++) {
						HSSFCell cell = row.getCell(cellNum);

						if (cell == null) {
							list.add("");
							continue;
						}
						System.out.println(cell);
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							String value = cell.getStringCellValue();
							list.add(value);
						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							Object value = "";
							if (DateUtil.isCellDateFormatted(cell)) {
								Date theDate = cell.getDateCellValue();
								value = simpleDateFormat.format(theDate);
								list.add(value.toString());
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								String temp = cell.getStringCellValue();
								// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
								if (temp.indexOf(".") > -1) {
									value = String.valueOf(new Double(temp)).trim();
								} else {
									value = temp.trim();
								}
								list.add(value);
							}

						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
							boolean value = cell.getBooleanCellValue();
							list.add(value);
						}
						if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR
								|| cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							String value = "";
							list.add(value);
						}
					}
					ans.add(list);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ans;
	}

	// 判断后缀为xls的excel文件的数据类型
	@SuppressWarnings("deprecation")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "---";
		}
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			double cur = hssfCell.getNumericCellValue();
			long longVal = Math.round(cur);
			Object inputValue = null;
			if (Double.parseDouble(longVal + ".0") == cur)
				inputValue = longVal;
			else
				inputValue = cur;
			return String.valueOf(inputValue);
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK
				|| hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR) {
			return "---";
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	// 字符串修剪 去除所有空白符号 ， 问号 ， 中文空格
	private static String Trim_str(String str) {
		if (str == null)
			return null;
		return str.replaceAll("[\\s\\?]", "").replace("　", "");
	}

}
