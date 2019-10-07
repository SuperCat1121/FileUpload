package com.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		XSSFWorkbook wb = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(new File("C:/Users/user2/Desktop/test.xlsx"));
			wb = new XSSFWorkbook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Sheet test = wb.getSheetAt(0);
		int LastRowNum = test.getLastRowNum();
		
		List<String> monthList = new ArrayList<String>();
		List<Double> numList = new ArrayList<Double>();
		for(int i=1;i<=LastRowNum;i++) {
			monthList.add(test.getRow(i).getCell(0).getStringCellValue());
			numList.add(test.getRow(i).getCell(1).getNumericCellValue());
		}
		
		model.addAttribute("monthList", monthList);
		model.addAttribute("numList", numList);
		
		return "NewFile";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(Model model, HttpServletRequest request) throws Exception {
		System.out.println(request.getRealPath("upload"));
		String savePath = "C:/Users/user2/Desktop/Eclipse-jee/eclipse/WorkSpace/WorkSpace_Python/test/src/main/webapp/resources/upload/";
		String fileName = "";
		try {
			MultipartRequest multi = 
					new MultipartRequest(request, savePath ,10 * 1024, "UTF-8", new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("upfile");
			System.out.println(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileName", savePath+fileName);
		return "upload";
	}
}










