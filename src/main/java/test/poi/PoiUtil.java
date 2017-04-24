package test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {

	public static XSSFWorkbook readExcel(File file) throws IOException {
		XSSFWorkbook hwb = null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			hwb = new XSSFWorkbook(fin);
		} finally {
			if (fin != null) {
				fin.close();
			}
		}
		return hwb;
	}

	public static XSSFCell createCell(XSSFRow row, int cellIndex, String cellContent) {
		XSSFCell cell = row.createCell(cellIndex, Cell.CELL_TYPE_STRING);
		cell.setCellType(Cell.CELL_TYPE_BLANK);
		cell.setCellValue(cellContent);
		return cell;
	}

	public static String getExcelCellValue(XSSFSheet sheet, Cell cell) {
		String ret = "";
		if (cell == null) {
			ret = "";
			return ret;
		}
		int rowIndex = cell.getRowIndex();
		int columnIndex = cell.getColumnIndex();
		if (isMergedRegion(sheet, rowIndex, columnIndex)) {
			return getMergedRegionValue(sheet, rowIndex, columnIndex);
		}
		ret = getExcelCellValue(cell);
		return ret;
	}

	public static boolean isMergedRegion(XSSFSheet sheet, int row, int column) {
		return getMergeRegionIndex(sheet, row, column) > -1;
	}

	public static int getMergeRegionIndex(XSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = (CellRangeAddress) sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return i;
				}
			}
		}
		return -1;
	}

	public static String getMergedRegionValue(XSSFSheet sheet, Cell cell) {
		return getMergedRegionValue(sheet, cell.getRowIndex(), cell.getColumnIndex());
	}

	public static String getMergedRegionValue(XSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					XSSFRow fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getExcelCellValue(fCell);
				}
			}
		}
		XSSFCell cell = sheet.getRow(row).getCell(column);
		String excelCellValue = getExcelCellValue(cell);
		return excelCellValue;
	}

	public static String getExcelCellValue(Cell cell) {
		String ret = "";
		if (cell == null) {
			ret = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			ret = cell.getStringCellValue().trim();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {

				Date dt = DateUtil.getJavaDate(cell.getNumericCellValue());
				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

				ret = sdfDate.format(dt);
			} else {
				double i = cell.getNumericCellValue();
				ret = i + "";
				DecimalFormat df = new DecimalFormat("0");// 使用DecimalFormat类对科学计数法格式的数字进行格式化
				String rs = "" + df.format(i);
				if (rs.endsWith(".0")) {
					ret = rs.replace(".0", "");
				} else {
					ret = rs;
				}
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			Double ret2 = cell.getNumericCellValue();
			if (ret2 != null) {
				String temp = ret2.toString();
				if (temp.indexOf(".") != -1) {
					ret = temp.substring(0, temp.indexOf("."));
				}
			}
			// ret = cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
			ret = "" + cell.getErrorCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			ret = "" + cell.getBooleanCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			ret = "";
		}
		return ret;
	}
}
