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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class CityMatch58 {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\bin.deng\\Desktop\\city.properties");
		List<City> city = mojiCty(file);

		XSSFSheet sheet = read58City();
		System.out.println(sheet.getLastRowNum()); // 435

		List<City> result = extract58CityCode(city, sheet);

		System.out.println("匹配城市" + result.size());
//		writeToExcel("C:\\Users\\bin.deng\\Desktop\\城市参数2.xlsx", result);
//		writeToTxt("C:\\Users\\bin.deng\\Desktop\\城市参数2.txt", result);
	}

	private static void writeToTxt(String path, List<City> result) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		for (City city : result) {
			FileUtils.writeStringToFile(file,
					String.format("%s  %s  %s\n", city.getCityId(), city.getCityName(), city.getCode()), true);
		}
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
			} else {
//				System.out.println(name);
			}
			rownum++;
			row = sheet.getRow(rownum);
		}
		return result;
	}

	private static City find2(String name, List<City> citys) {
		List<City> cs = new ArrayList<>();
		// String cityName = null;
		for (City city : citys) {
			if (city.getCityName().equals(name)) {
				return city;
			}
			// 城市名之后是市或者县的匹配度高
			if (city.getCityName().indexOf(name) < 0) {
				continue;
			}
			String[] split = city.getCityName().split(name);
			if (split.length == 1) {
				// cityName = name;
				// cs.add(city);
				return city;
			} else {
				try {
					if ((split[1].indexOf("市") >= 0 || split[1].indexOf("县") >= 0) && city.getCityId() != 0) {
						// cs.add(city);
						return city;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (cs.size() > 1) {
			for (City city : cs) {
				System.out.println(city.getCityName());
			}
		} else if (cs.size() == 1) {
			return cs.get(0);
		}
		return null;
	}

	/**
	 * 墨迹的城市名中包含58的城市名
	 * 
	 * provinceId 对应上级的 fid, 不是cityId 省的 provinceId: -1 ,
	 * 
	 * @param name
	 *            58的城市名
	 * @param citys
	 *            墨迹的成绩
	 */
	private static City find(String name, List<City> citys) {
		List<City> cs = new ArrayList<>();
		for (City city : citys) {
			if (city.getCityName().indexOf(name) > -1 && city.getCityId() != 0) {
//				return city;
				cs.add(city);
			}
		}
		if(cs.size() > 1) {
			for (City city : cs) {
				System.out.print(city.getCityName()+"   ");
			}
			System.out.println();
		}
		return null;
	}

	private static List<City> mojiCty(File file) throws IOException {
		String readFileToString = FileUtils.readFileToString(file);
		// JSONArray array = JSONArray.fromObject(readFileToString);
		List<City> listCity = new ArrayList<>();
		JSONArray jsonArr = JSON.parseArray(readFileToString);
		for (int i = 0; i < jsonArr.size(); i++) {
			City city = JSON.toJavaObject(jsonArr.getJSONObject(i), City.class);
			listCity.add(city);
		}
		System.out.println(listCity.size());
		return listCity;
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
			PoiUtil.createCell(row, 0, city.getCityId() + "");
			PoiUtil.createCell(row, 1, city.getFid() + "");
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
