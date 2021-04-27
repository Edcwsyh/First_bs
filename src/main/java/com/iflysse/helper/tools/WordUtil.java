package com.iflysse.helper.tools;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.bean.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class WordUtil {
	/**
	 * 支持中文字体
	 */
	private static BaseFont bfChinese = null; 
	/**
	 * 标题的字体大小
	 */
	private static final int FONT_TITLE_SIZE = 15;
	/**
	 * 正文的字体大小
	 */
	private static final int FONT_TEXT_SIZE = 12;
	/**
	 * 表格的字体大小
	 */
	private static final int FONT_TABLE_SIZE = 10;
	
	/**
	 * 标题字体类型
	 */
	private static final Font TITLE_FONT = new Font(bfChinese, FONT_TITLE_SIZE, Font.BOLD, BaseColor.BLACK);
	/**
	 * 正文字体类型
	 */
	private static final Font TEXT_FONT = new Font(bfChinese, FONT_TEXT_SIZE, Font.NORMAL, BaseColor.BLACK);
	/**
	 * 表格字体类型
	 */
	private static final Font TABLE_FONT = new Font(bfChinese, FONT_TABLE_SIZE, Font.NORMAL, BaseColor.BLACK);
	
	private ByteArrayOutputStream byteOut;
	private Document document;

	public void setDocument(Document document) {
		this.document = document;
	}

	public static void init() {
		try {
			bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建一个标题段落
	 * @param text 标题文本
	 * @return
	 */
	private static Paragraph create_title_paragraph( String text) {
		Paragraph paragraph = new Paragraph(text);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setFont(TITLE_FONT);
		return paragraph;
	}
	
	/**
	 * 创建一个正文段落
	 * @param text 正文文本
	 * @return
	 */
	private static Paragraph create_text_paragraph( String text) {
		Paragraph paragraph = new Paragraph(text);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setFont(TEXT_FONT);
		return paragraph;
	}
	
	/**
	 * 创建一个表格段落
	 * @param text 表格文本
	 * @return
	 */
	private static Paragraph create_table_paragraph( String text) {
		Paragraph paragraph = new Paragraph(text);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setFont(TABLE_FONT);
		return paragraph;
	}
	
	public static Word create_document_template( User user, Subject subject, String termName ) throws DocumentException {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, byteOut);
		document.open();
		document.addAuthor( user.getRealName() );
		document.addAuthor( user.getUsername() );
		//创建标题并添加到文档中
		document.add( create_title_paragraph(termName) );
		//添加课程基本信息
		document.add ( create_text_paragraph( 
				"课程名称:"  + subject.getName() + 
				"  课程性质:" + subject.getTypeString() + 
				"  授课教师:" + user.getRealName() 
				));
		document.add ( create_text_paragraph( 
				"授课班级:"  + subject.getKlass() + 
				"  授课总学时:" + subject.getTimeTotal() + 
				"  联系电话:" + user.getPhone()
				));
		Word word = new Word(document, byteOut);
		return word;
	}
	
	public static PdfPTable create_table ( List<CourseVO> courseList) throws Exception {
		//定义一个指向时间对象的临时指针
		Time time = null;
		//创建一个表格
		PdfPTable table = new PdfPTable(new float[] {1,1,1,8,1,1,1,1,1,1,2.25f});
		//设置表格为水平居中
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		//表格的宽度百分比 
		table.setWidthPercentage(100);
		//填入表格字段
		table.addCell("周\r\n次");
		table.addCell("星\r\n期");
		table.addCell("节\r\n次");
		table.addCell("授课内容");
		table.addCell("讲课");
		table.addCell("习题讨论");
		table.addCell("实验上机");
		table.addCell("作业安排");
		table.addCell("辅导安排");
		table.addCell("其他");
		table.addCell("执行情况");
		for ( CourseVO course : courseList ) {
			table.addCell( course.getWeek().toString() );
			table.addCell( course.getDayOfWeek() );
			table.addCell( course.getTimeCourseIndex() );
			table.addCell( course.getContent() );
			table.addCell(""); 	//讲课
			table.addCell("");	//习题讨论
			table.addCell("");	//实验上机
			table.addCell( course.getIsHomework() ? "✔" : "" );  //作业安排
			table.addCell(""); //辅导安排
			table.addCell(""); //其他
			table.addCell("执行情况"); //良好
		}
		return table;
	}
	
	public static Word create_document( User user, Subject subject, String termName, List<CourseVO> courseList) throws Exception {
		Word word = create_document_template(user, subject, termName);
		Document document = word.getDocument();
		document.add( create_table(courseList) );
		return word;
	}

}
