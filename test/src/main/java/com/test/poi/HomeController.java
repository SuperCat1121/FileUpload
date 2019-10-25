package com.test.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println(request.getSession().getServletContext().getRealPath("/upload"));
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		String fileName = "";
		try {
			MultipartRequest multi = 
					new MultipartRequest(request, savePath ,10 * 1024, "UTF-8", new DefaultFileRenamePolicy());
			// upfile : input type=file 인 태그의 이름
			fileName = multi.getFilesystemName("file");
			System.out.println(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileName", fileName);
		return "upload";
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		String filePath = request.getSession().getServletContext().getRealPath("upload") + "\\" + fileName;
		System.out.println("filePath : " + filePath);
		File file = new File(filePath);
		
		// mimeType : 메인타입/서브타입 (데이터 표준 포맷)
		// ex) test/plain, image/jpeg
		String mimeType = request.getSession().getServletContext().getMimeType(filePath);
		System.out.println("mime : " + mimeType);
		if(mimeType == null) {
			// application/octet-stream : 모든 경우를 위한 기본값
			//                            알려지지 않은 파일 타입은 이 타입으로 사용
			// test/plain : 텍스트 파일을 위한 기본값
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
		String encoding = new String(fileName.getBytes("UTF-8"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; filename = " + encoding);
		
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream outStream = response.getOutputStream();
		
		byte b[] = new byte[4096];
		int data = 0;
		while((data = in.read(b, 0, b.length)) != -1) {
			outStream.write(b, 0, data);
		}
		
		outStream.flush();
		outStream.close();
		in.close();
		
		return null;
	}
}










