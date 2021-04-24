package com.iflysse.helper.tools;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;

public class ExcelUtil {
	
	private static final String WARNING = "填表注意：\r\n" + 
			"1.每半天最多4节课，上午：1,2,3,4节课；下午：5,6,7,8节课；晚上：9,10,11,12节课。多节连堂，需用英文状态下逗号隔开。\r\n" + 
			"2.日期格式，如：2020年3月12日。\r\n" + 
			"3.“授课内容”栏是课表审核的重点之一，要求填写每堂课详细的教学内容。\r\n" + 
			"4.“教学模式”栏是课表审核的重点之一，其中；“授课-直播”是指通过博思直播平台进行的授课；“授课-现场”是指正常理论课课堂教学，非边讲边练模式，该模式需录制教学视频；“学练-平台”是指辅导学生在博思平台或实验平台进行实践练习或实验；“学练-线下”是指辅导学生进行课程实践或者项目实践；“实训模式”是指讲师授课和学生动手实践一体的教学模式，边讲边练。\r\n" + 
			"5.“要求布置作业”是指在博思平台上布置在线作业，如选择“要求”，则讲师必须按计划布置，学生必须按时完成并上传至平台，讲师必须在规定时间内完成批阅。";
	//默认列宽为
	private static final int WIDTH_DEFAULT_COLUMN = 11; 
	//默认列高为
	private static final short HIGHT_DEFAULT_ROW = 200;
	//列的总数量
	private static final short NUMBER_COLUMN = 10;
	//字体类型
	private static final String FONT_TYPE = "微软雅黑";
	//字体大小-标题
	private static final short FONT_TITLE_SIZE = 14;
	//字体大小-字段
	private static final short FONT_FIELD_SIZE = 12;
	//字体大小-正文
	private static final short FONT_TEXT_SIZT = 10;
	
	//初始行的数量
	private static final int NUMBER_INIT_ROW = 4;
	
	/**
	 * 创建一个基本样式
	 * @param excel
	 * @return
	 */
	private static CellStyle create_style_base(SXSSFWorkbook excel) {
		CellStyle style = excel.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}
	/**
	 * 创建标题字体
	 * @param excel 需要创建的excel对象
	 * @return
	 */
	private static CellStyle create_style_title(SXSSFWorkbook excel) {
		CellStyle style = create_style_base(excel);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());// 设置背景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		//设置单元格垂直和居中,背景色以及字体
		Font font = excel.createFont();
		//使用默认字符集
		font.setCharSet(Font.DEFAULT_CHARSET);
		//设置为粗体
		font.setBold(true);
		//设置字体大小
		font.setFontHeightInPoints(FONT_TITLE_SIZE);
		//设置字体类型
		font.setFontName(FONT_TYPE);
		style.setFont(font);
		return style;
	}

	/**
	 * 创建标题字体
	 * @param excel 需要创建的excel对象
	 * @return
	 */
	private static CellStyle create_style_field(SXSSFWorkbook excel) {
		CellStyle style = create_style_base(excel);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());// 设置背景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		Font font = excel.createFont();
		//使用默认字符集
		font.setCharSet(Font.DEFAULT_CHARSET);
		//设置为粗体
		font.setBold(true);
		//设置字体大小
		font.setFontHeightInPoints(FONT_FIELD_SIZE);
		//设置字体类型
		font.setFontName(FONT_TYPE);
		style.setFont(font);
		return style;
	}
	
	/**
	 * 创建正文字体
	 * @param excel 需要创建的excel对象
	 * @return
	 */
	private static CellStyle create_style_text(SXSSFWorkbook excel) {
		CellStyle style = create_style_base(excel);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font = excel.createFont();
		//使用默认字符集
		font.setCharSet(Font.DEFAULT_CHARSET);
		//设置字体大小
		font.setFontHeightInPoints(FONT_TEXT_SIZT);
		//设置字体类型
		font.setFontName(FONT_TYPE);
		style.setFont(font);
		return style;
	}
	
	/**
	 * 合并sheet中从 (firstRow, firstCol) 到 (lastRow, lastCol)的单元格
	 * @param sheet 由主调函数创建, 不能为空
	 * @param region 由主调函数创建, 不能为空
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	private static void merge_cell(SXSSFSheet sheet, CellRangeAddress region, int firstRow , int lastRow, int firstCol, int lastCol) {
		if ( sheet == null || region == null) {
			throw new NullPointerException("Poi error : sheet和region不能为空!");
		}
		region.setFirstColumn(firstCol);
		region.setFirstRow(firstRow);
		region.setLastColumn(lastCol);
		region.setLastRow(lastRow);
		sheet.addMergedRegion(region);
	}
	
	/**
	 * 创建一个excel模板
	 * @return
	 */
	public static SXSSFWorkbook get_excel_template() {
		SXSSFWorkbook excel = new SXSSFWorkbook();
		SXSSFSheet sheet = excel.createSheet("课表");
		CellStyle titleStyle = create_style_title(excel), fieldStyle = create_style_field(excel), warningStyle = create_style_text(excel);
		warningStyle.setAlignment(HorizontalAlignment.LEFT);
		//设置单元格默认列宽和行高
		sheet.setDefaultColumnWidth(WIDTH_DEFAULT_COLUMN);
		sheet.setDefaultRowHeightInPoints(HIGHT_DEFAULT_ROW);
		//创建单元格样式
		
        List<SXSSFRow> rowList = null;
        //创建4行10列的表格
        try {
        	rowList = new ArrayList<SXSSFRow>(NUMBER_INIT_ROW);
		} catch (Exception e) {
			System.out.println("初始化错误");
		}
        
        System.out.println(rowList.size());
        rowList.add(sheet.createRow(0));
        rowList.add(sheet.createRow(1));
        rowList.add(sheet.createRow(2));
        rowList.add(sheet.createRow(3));
        SXSSFCell cell;
        for ( SXSSFRow iter : rowList ) {
        	for ( int index = 0; index < NUMBER_COLUMN; ++index ) {
        		cell = iter.createCell(index);
        		cell.setCellStyle(fieldStyle);
        	}
        }
        //设置填表注意单元格的格式
        rowList.get(0).getCell(0).setCellStyle(warningStyle);
        //设置标题单元格的格式
        rowList.get(1).getCell(0).setCellStyle(titleStyle);
		//设置各列列宽
		sheet.setColumnWidth(0, 21 * 255);
		sheet.setColumnWidth(5, 14 * 255);
		sheet.setColumnWidth(8, 19 * 255);
		sheet.setColumnWidth(9, 120 * 255);
		//设置各行行高
		rowList.get(0).setHeightInPoints(150);
		rowList.get(1).setHeightInPoints(31);
		rowList.get(2).setHeightInPoints(25);
		rowList.get(3).setHeightInPoints(25);
		//合并单元格
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, 0);
		merge_cell(sheet, region, 0, 0, 0, 9);
		merge_cell(sheet, region, 1, 1, 0, 9);
		merge_cell(sheet, region, 2, 2, 1, 6);
		merge_cell(sheet, region, 2, 2, 7, 8);
		//将"填表注意"写入第一行并设置其行高
		rowList.get(0).getCell(0).setCellValue(WARNING);
		//填入表的字段
		rowList.get(1).getCell(0).setCellValue("开课课表");
		rowList.get(2).getCell(0).setCellValue("开课名称");
		rowList.get(2).getCell(7).setCellValue("班级名称");
		rowList.get(3).getCell(0).setCellValue("日期");
		rowList.get(3).getCell(1).setCellValue("时段");
		rowList.get(3).getCell(2).setCellValue("节次");
		rowList.get(3).getCell(3).setCellValue("课程类型");
		rowList.get(3).getCell(4).setCellValue("教学模式");
		rowList.get(3).getCell(5).setCellValue("教室");
		rowList.get(3).getCell(6).setCellValue("讲师");
		rowList.get(3).getCell(7).setCellValue("助教");
		rowList.get(3).getCell(8).setCellValue("是否要求布置作业");
		rowList.get(3).getCell(9).setCellValue("授课内容");
		return excel;
	}

	/**
	 * 创建一张excel表格
	 * @param teacher 教师名称
	 * @param subect 科目基本信息
	 * @param courseList 课程列表
	 * @param term 科目所属的学期
	 * @return
	 */
	public static SXSSFWorkbook create_excel(String teacher, Subject subect, List<CourseVO> courseList) {
		try {
			SXSSFWorkbook excel = get_excel_template();
			SXSSFSheet sheet = excel.getSheet("课表");
			int index;
			List<SXSSFRow> rowList = new LinkedList<SXSSFRow>();
			CellStyle textStyle = create_style_text(excel);
			//设置课程名称和教授班级
			sheet.getRow(2).getCell(1).setCellValue( subect.getName() );
			sheet.getRow(2).getCell(9).setCellValue( subect.getKlass() );
			index = NUMBER_INIT_ROW;
			SXSSFRow row = null;
			for ( CourseVO iter : courseList) {
				row = sheet.createRow(index);
				row.createCell(0).setCellValue( iter.getSpecificTime() );
				row.createCell(1).setCellValue( iter.getTimeQuamtumString() );
				row.createCell(2).setCellValue( iter.getTimeCourseIndex() );
				row.createCell(3).setCellValue( subect.getType() );
				row.createCell(4).setCellValue( iter.getMode() );
				row.createCell(5).setCellValue( iter.getClassroom() );
				row.createCell(6).setCellValue( teacher );
				row.createCell(7).setCellValue( subect.getTa() );
				row.createCell(8).setCellValue( iter.getIsHomework() ? "要求" : "不要钱");
				row.createCell(9).setCellValue( iter.getContent() );
				rowList.add(row);
			}
			for ( SXSSFRow rowIter : rowList ) {
				for ( index = 0; index < NUMBER_COLUMN - 1; ++index ) {
					rowIter.getCell(index).setCellStyle(textStyle);
				}
				rowIter.getCell(index).setCellStyle( rowList.get(0).getCell(0).getCellStyle() );
			}
			return excel;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
