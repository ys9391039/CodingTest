package com.sample.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

public class ExcelUtil extends AbstractJExcelView {

	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fileName = (String)model.get("fileName");
		String sheetName = (String)model.get("sheetName");
		List<String> listColumn = (List<String>) model.get("listColumn");
		List<List<Object>> listData = (List<List<Object>>) model.get("listData");
		List<List<Object>> mergeData = (List<List<Object>>) model.get("mergeData");
		List<List<Object>> alignData = (List<List<Object>>) model.get("alignData");
		
		String timeZone = (String)model.get("freeMarkerTimeZone");
		
		/*
		if (timeZone == null || "".equals(timeZone)) {
			timeZone = "GMT+9:00";
		}
		*/
		
		//���GMT+�� ����
		timeZone = "GMT+9:00";
		
		fileName = createFileName(fileName, timeZone);
		setFileNameToResponse(request, response, fileName);
		
		WritableSheet sheet = workbook.createSheet(sheetName, 0);

        int colNum = 0;
        WritableCellFormat head = new WritableCellFormat();
        head.setBackground( Colour.GRAY_25 );
        head.setVerticalAlignment( VerticalAlignment.CENTRE );
        head.setBorder(Border.ALL, BorderLineStyle.THIN);
        head.setWrap(true);
        
        WritableCellFormat cell = new WritableCellFormat();
        cell.setBackground( Colour.WHITE );
        cell.setVerticalAlignment( VerticalAlignment.CENTRE );
        cell.setBorder(Border.ALL, BorderLineStyle.THIN);
        cell.setWrap(true);
        
        WritableCellFormat cellAlign = new WritableCellFormat();
        
        WritableCellFormat cellAlignL = new WritableCellFormat();
        cellAlignL.setBackground( Colour.WHITE );
        cellAlignL.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellAlignL.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellAlignL.setAlignment(Alignment.LEFT);
        cellAlignL.setWrap(true);
        
        WritableCellFormat cellAlignC = new WritableCellFormat();
        cellAlignC.setBackground( Colour.WHITE );
        cellAlignC.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellAlignC.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellAlignC.setAlignment(Alignment.CENTRE);
        cellAlignC.setWrap(true);
        
        WritableCellFormat cellAlignR = new WritableCellFormat();
        cellAlignR.setBackground( Colour.WHITE );
        cellAlignR.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellAlignR.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellAlignR.setAlignment(Alignment.RIGHT);
        cellAlignR.setWrap(true);
        
        NumberFormat intFormat = new NumberFormat("###0"); 
        WritableCellFormat cellInt = new WritableCellFormat(intFormat);
        cellInt.setBackground( Colour.WHITE );
        cellInt.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellInt.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellInt.setWrap(true);

        NumberFormat floatFormat = new NumberFormat("#,###.##"); 
        WritableCellFormat cellFloat = new WritableCellFormat(floatFormat);
        cellFloat.setBackground( Colour.WHITE );
        cellFloat.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellFloat.setAlignment(Alignment.RIGHT);
        cellFloat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFloat.setWrap(true);
        
        Locale locale = new Locale("en", "US");
        //DateFormat dateFormat = new DateFormat ("yyyy.mm.dd" ); 
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MMM dd, yyyy", locale ); 
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
       
        WritableCellFormat cellDate = new WritableCellFormat (); 
        cellDate.setBackground( Colour.WHITE );
        cellDate.setVerticalAlignment( VerticalAlignment.CENTRE );
        cellDate.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellDate.setWrap(true);
        
        CellView autoSizeCellView = new CellView();
        autoSizeCellView.setAutosize(true);
                
		for (String entry : listColumn) {
	        sheet.setColumnView(colNum, 17);
			sheet.addCell(new Label(colNum++, 0, entry, head));
        }
		
        int rowNum = 1;
		for (List<Object> row : listData) {
			colNum = 0;
			
			for ( int i = 0; i < row.size(); i++ ) {
				Object obj = row.get(i);
				cellAlign = cell;
				if (alignData != null){  // cell align
					for (int ii = 0; ii < alignData.size(); ii++){
						if ((Integer)alignData.get(ii).get(0) == i){  // cell align
							if ((Alignment)alignData.get(ii).get(1) == Alignment.RIGHT){
								cellAlign = cellAlignR;
							} else if ((Alignment)alignData.get(ii).get(1) == Alignment.LEFT){
								cellAlign = cellAlignL;
							} else if ((Alignment)alignData.get(ii).get(1) == Alignment.CENTRE){
								cellAlign = cellAlignC;
							}
						}
					}
				}
				if ( obj == null ) {
					sheet.addCell(new Label(i, rowNum, "   ", cellAlign));
				} else if ( obj instanceof String ) {
					sheet.addCell(new Label(i, rowNum, (String)obj, cellAlign));
				} else if ( obj instanceof Date ) {
					//dateFormat.format((Date)obj)
					//sheet.addCell(new DateTime(i+1, rowNum, (Date)obj , cellDate));
					sheet.addCell(new Label(i, rowNum, dateFormat.format((Date)obj), cellAlign));
				} else if ( obj instanceof Integer ) {
					sheet.addCell(new Number(i, rowNum, (Integer)obj, cellInt));
				} else if ( obj instanceof Long ) {
					sheet.addCell(new Number(i, rowNum, (Long)obj, cellInt));
				} else if ( obj instanceof Double ) {
					sheet.addCell(new Number(i, rowNum, (Double)obj, cellFloat));
				} else if ( obj instanceof Float ) {
					sheet.addCell(new Number(i, rowNum, (Float)obj, cellFloat));
				} else if ( obj instanceof BigDecimal ) {
					sheet.addCell(new Label(i, rowNum, obj.toString(), cellFloat));
				} else {
					sheet.addCell(new Label(i, rowNum, obj.toString(), cellAlign));
				}
			}
			rowNum++;
        }
		
		if (mergeData != null){  // cell merge
			for (List<Object> row : mergeData) {
				if (row.size() == 4){
					Object mergeCellX1 = row.get(0);
					Object mergeCellY1 = row.get(1);
					Object mergeCellX2 = row.get(2);
					Object mergeCellY2 = row.get(3);
					if ( mergeCellX1 instanceof Integer
						  && mergeCellY1 instanceof Integer   //JTest
						  && mergeCellX2 instanceof Integer
						  && mergeCellY2 instanceof Integer) {
						sheet.mergeCells((Integer)mergeCellX1, (Integer)mergeCellY1, (Integer)mergeCellX2, (Integer)mergeCellY2);
					} 
				}
	        }
		}
	}	

	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
		String fileNameEnc = fileName;
        try {
			fileNameEnc = new String(fileName.getBytes("euc-kr"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE 5.5") >= 0) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition", "filename=\""+fileNameEnc+"\"");
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileNameEnc+"\"");
		}
	}

	private String createFileName(String fileName, String timeZone) {
		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		fileFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

		return new StringBuilder( fileName )
			.append("-").append(fileFormat.format(new Date())).append(".xls").toString();
	}

}
