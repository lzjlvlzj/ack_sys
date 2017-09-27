package org.ack.admin.web.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.admin.web.template.EmployeeLogExcel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ExcelBuilderView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 数据
		List<EmployeeLogExcel> logList = (List<EmployeeLogExcel>) model.get("logList");
		
		String fileName = (String)model.get("fileName");
		String sheetName = (String)model.get("sheetName");
		String[] headers = (String[])model.get("headers");
		int len = headers.length;
		//设置头信息
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/ms-excel");  
        response.setHeader("Content-Disposition", "inline; filename="+new String(fileName.getBytes(),"iso8859-1"));  
        
        XSSFWorkbook book = (XSSFWorkbook) workbook;  
        XSSFSheet sheet = book.createSheet(sheetName);
		
        XSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < len; i++) {  
            row.createCell(i).setCellValue(headers[i]);  
        }  
        
        for (int i = 0; i < logList.size(); i++) {  
        	EmployeeLogExcel ee = logList.get(i);  
  
            row = sheet.createRow(i + 1);
            int num = i + 1;
            row.createCell(0).setCellValue(num); 
            row.createCell(1).setCellValue(ee.getDate()); 
            row.createCell(2).setCellValue(ee.getDepartmentName());  
            row.createCell(3).setCellValue(ee.getUserName());  
            row.createCell(4).setCellValue(ee.getLogContent());  
            row.createCell(5).setCellValue(ee.getCoefficient());  
        }  
	}

	
}
