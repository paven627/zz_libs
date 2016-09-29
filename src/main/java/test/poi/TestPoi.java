package test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestPoi {
	public static void main(String[] args) throws IOException {

		FileInputStream fin = null;
		Workbook hwb;
		fin = new FileInputStream(new File("D:\\百度云同步盘\\项目\\开发\\文档\\百度\\数据字典\\广告行业1.1.xls"));
		// try {
		// hwb = new XSSFWorkbook(fin); // 2007 以后
		// } catch (Exception e) {
		hwb = new HSSFWorkbook(fin); // 2007 以前
		// }

		Sheet sheet = hwb.getSheetAt(0);

		JSONArray all = new JSONArray();
		PoiUtil poi = new PoiUtil();

		int rowIndex = 1;
		Row row = sheet.getRow(rowIndex);
		while (row != null) {

			String parentId = poi.getExcelCellValue(row.getCell(0));
			String parentName = poi.getExcelCellValue(row.getCell(1));
			String childId = poi.getExcelCellValue(row.getCell(2));
			String childName = poi.getExcelCellValue(row.getCell(3));

			JSONObject parent = new JSONObject();
			parent.put("name", parentName);
			parent.put("id", parentId);

			JSONObject child = new JSONObject();
			child.put("parentNode", parentName);
			child.put("name", childName);
			child.put("id", childId);

			boolean found = false;
			JSONObject parentJson = null;
			for (int i = 0; i < all.size(); i++) {
				Object object = all.get(i);
				parentJson = (JSONObject) object;
				Object exsitName = parentJson.get("name");
				if (exsitName.equals(parentName)) {
					found = true;
					break;
				}
			}

			JSONArray items = null;
			if (found) {
				items = (JSONArray) parentJson.get("items");
			} else {
				all.add(parent);
				items = new JSONArray();
			}
			parent.put("items", items);
			items.add(child);

			rowIndex++;
			row = sheet.getRow(rowIndex);
		}
		System.out.println(all);

		if (fin != null) {
			fin.close();
		}
	}
}
