package test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;

public class CityMatch58 {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\bin.deng\\Desktop\\city.properties");
		List<City> city = mojiCty(file);

		XSSFSheet sheet = read58City();
		System.out.println(sheet.getLastRowNum()); // 435

		List<City> result = extract58CityCode(city, sheet);
		System.out.println("匹配城市" + result.size());
		writeToExcel("C:\\Users\\bin.deng\\Desktop\\城市参数2.xlsx", result);

	}

	private static List<City> extract58CityCode(List<City> citys, XSSFSheet sheet) {
		int rownum = 1;
		XSSFRow row = sheet.getRow(rownum);
		List<City> result = new ArrayList<City>();
		while (row != null) {
			String name = row.getCell(0).getStringCellValue();
			String code = row.getCell(1).getStringCellValue();
			City city = find(name, citys);
			if (city != null) {
				city.setCode(code);
				result.add(city);
			} else  {
				System.out.println(name);
			}
			rownum++;
			row = sheet.getRow(rownum);
		}
		return result;
	}

	/**
	 * 墨迹的城市名中包含58的城市名
	 * @param name
	 * @param citys
	 * @return
	 */
	private static City find(String name, List<City> citys) {
		for (City city : citys) {
			if (city.getCityName().indexOf(name) > -1) {
				return city;
			}
		}
		return null;
	}

	private static List<City> mojiCty(File file) throws IOException {
		String readFileToString = FileUtils.readFileToString(file);
		JSONArray array = JSONArray.fromObject(readFileToString);
		System.out.println(array.size());
		List<City> list = array.toList(array, City.class);
		System.out.println(list.size());
		return list;
	}

	private static XSSFSheet read58City() throws IOException {
		XSSFWorkbook workbook = PoiUtil.readExcel(new File("C:\\Users\\bin.deng\\Desktop\\城市参数.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	private static void writeToExcel(String path, List<City> cities) throws FileNotFoundException, IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		XSSFWorkbook hwb = new XSSFWorkbook();
		XSSFSheet sheet = hwb.createSheet();
		int rowIndex = 0;
		for (City city : cities) {
			XSSFRow row = sheet.createRow(rowIndex++);
			PoiUtil.createCell(row, 0, city.getCityId()+"");
			PoiUtil.createCell(row, 1, city.getFid()+"");
			PoiUtil.createCell(row, 2, city.getCityName());
			PoiUtil.createCell(row, 3, city.getCode());
			
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			hwb.write(os);
			os.flush();
		} finally {
			os.close();
		}

	}

}
