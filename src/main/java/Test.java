import java.text.SimpleDateFormat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {
	private static SimpleDateFormat sdfSS = new SimpleDateFormat("hhmmss");

	public static void main(String[] args) {
//		Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		int sourceMedia = 28;
		int auditState = 2;
		int uploaded = 1;
		
		String optStr = "";
		if (sourceMedia == 28) {		//百度,状态审核之前不允许改
			if (uploaded != 0 && auditState != 2) {
				optStr += "<a href=\"javascript:uploadToAdx(' + sourceMedia + ',' + appUploadStatus + ');\">上传</a>";
			} else {
				optStr += "'<span style=\"color:#CCC; \">上传</span>'";
			}
		}
		else if (uploaded != 0) {
			optStr += "'<a href=\"javascript:uploadToAdx(' + sourceMedia + ',' + appUploadStatus + ','+auditState+','+uploaded+');\">上传</a>'";
		}else {
			optStr += "'<span style=\"color:#CCC; \">上传</span>'";
		}

		System.out.println(optStr);
	}

}