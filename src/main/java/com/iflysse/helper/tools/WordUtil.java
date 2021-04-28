package com.iflysse.helper.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.User;

public class WordUtil {
	
	private XWPFDocument docx;
	
	private WordUtil() {
		docx = new XWPFDocument();
		CTSectPr sectPr = docx.getDocument().getBody().addNewSectPr();
		CTPageMar pageMar = sectPr.addNewPgMar();
		pageMar.setLeft(BigInteger.valueOf(720L));
		pageMar.setTop(BigInteger.valueOf(1440L));
		pageMar.setRight(BigInteger.valueOf(720L));
		pageMar.setBottom(BigInteger.valueOf(1440L));
	}
	
	public XWPFDocument get_docx() {
		return docx;
	}

	private static final String FONT_TYPE = "宋体";
	/**
	 * 标题字体大小
	 */
	private static final int FONT_TITLE_SIZE = 16;
	
	/**
	 * 正文字体大小
	 */
	private static final int FONT_TEXT_SIZE = 12;
	/**
	 * 表格字体大小
	 */
	private static final int FONT_CELL_SIZE = 11;
	/**
	 * 说明的文字大小
	 */
	private static final int FONT_INSTRUCTIONS_SIZE = 10;
	/**
	 * 列的数量
	 */
	private static final int NUMBER_COLUMN = 11;
	/**
	 * 行的模板数量
	 */
	private static final int NUMBER_ROW_TEMPLATE = 5;
	/**
	 * 表格的列宽度
	 */
	private static final int WIDTHS[] = {1,1,1,8,1,1,1,1,1,1,2};
	/**
	 * 表格的字段
	 */
	private static final String FIELD[] = {
			"周次", "星期","节次", "授课内容", "讲课",
			"习题讨论", "实验上机", "作业安排", "辅导安排", 
			"其他", "执行情况"
	};
	
	/**
	 * 说明(段落1)
	 */
	private static final String INSTRUCTIONS_1 = "1.执行情况栏：课后填写，若按计划执行打√表示，"
			+ "若未执行请简要说明，如“因公调课”、“因私调课”、“因假期调课”等；";
	/**
	 * 说明(段落2)
	 */
	private static final String INSTRUCTIONS_2 = "2.教学计划进度应以课程教学大纲为依据，对本课程的教学内容、"
			+ "教学形式以及课后作业和辅导进行全面计划和具体安排，是教师组织教学（备课、上课、课后辅导等）及教学运行监控的依据。";
	/**
	 * word的图片资源路径
	 */
	private static final String PATH_IMAGE = "src/main/resources/public/images/南宁学院.jpg";
	/**
	 * 签字信息
	 */
	private static final String SIGNATURE = "教研室主任或专业（课程）负责人签字：                                    日期：";
	
	/**
	 * 创建一个标题段落
	 * @param text
	 * @return
	 */
	private XWPFParagraph create_title_paragraph( String text ) {
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setFontSize(FONT_TITLE_SIZE);
		run.setFontFamily(FONT_TYPE);
		run.setText( text );
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		return paragraph;
	}
	
	/**
	 * 创建一个正文段落
	 * @param text
	 * @return
	 */
	private XWPFParagraph create_text_paragraph( String text ) {
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setFontSize(FONT_TEXT_SIZE);
		run.setFontFamily(FONT_TYPE);
		run.setText( text );
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		return paragraph;
	}
	
	/**
	 * 设置单元格中的文本内容
	 * @param cell
	 * @param text
	 * @param paragraphAlignment
	 * @return 被设置的段落对象
	 */
	private XWPFParagraph set_cell_text( XWPFTableCell cell, String text,ParagraphAlignment paragraphAlignment) {
		return set_cell_text(cell, text, 0, paragraphAlignment);
	}
	
	/**
	 * 设置单元格中的文本内容
	 * @param cell 单元格对象
	 * @param text 文本
	 * @param index 段落的索引
	 * @param paragraphAlignment 对齐方式
	 * @return 被设置的段落对象
	 */
	private XWPFParagraph set_cell_text( XWPFTableCell cell, String text, int index ,ParagraphAlignment paragraphAlignment) {
		XWPFParagraph paragraph = cell.getParagraphArray(index);
		//若不存在段落基本元素,就创建一个基本元素,否则获取第一感基本元素
		XWPFRun run = paragraph.getRuns().size() == 0 ? paragraph.createRun() : paragraph.getRuns().get(0);
		run.setFontSize(FONT_CELL_SIZE);
		run.setFontFamily(FONT_TYPE);
		run.setText( text );
		paragraph.setAlignment(paragraphAlignment);
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		return paragraph;
	}
	
	/**
	 * 在单元格中创建一个新的段落
	 * @param cell 单元格
	 * @param text 文本内容
	 * @param fontSize 字体大小
	 * @param paragraphAlignment 对齐方式
	 * @return
	 */
	private XWPFParagraph create_cell_text( XWPFTableCell cell, String text, int fontSize ,ParagraphAlignment paragraphAlignment) {
		XWPFParagraph paragraph = cell.addParagraph();
		XWPFRun run = paragraph.createRun();
		run.setFontSize(fontSize);
		run.setFontFamily(FONT_TYPE);
		run.setText( text );
		paragraph.setAlignment(paragraphAlignment);
		cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		return paragraph;
	}
	
	/**
	 * 创建一个表格模板
	 * @param user
	 * @param subject
	 * @param termName
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public void create_word_template( User user, Subject subject, String termName ) throws IOException, InvalidFormatException {
		InputStream picture = new FileInputStream(PATH_IMAGE);
		XWPFParagraph imageParagraph = docx.createParagraph();
		XWPFRun imageRun = imageParagraph.createRun();
		imageRun.addPicture(picture, Document.PICTURE_TYPE_JPEG, "南宁学院.jpg", Units.toEMU(128), Units.toEMU(42));
		imageParagraph.setAlignment(ParagraphAlignment.CENTER);
		picture.close();
		create_title_paragraph(termName);
		create_text_paragraph(
				"课程名称 : " + subject.getName() + 
				"  课程性质 : " + subject.getTypeString() + 
				"  授课教师 : " + user.getRealName() 
				);
		create_text_paragraph( 
				"授课班级 : " + subject.getKlass() + 
				"  授课总学时 : " + subject.getTimeTotal() + 
				" 联系电话" + user.getPhone() 
				);
	}
	
	/**
	 * 跨列合并表格
	 * @param table 需要合并的表格
	 * @param row 表格所在行数
	 * @param fromRow 合并起始单元格位置
	 * @param toRow 合并结束单元格位置
	 */
	public  void merge_cells_column(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if ( cellIndex == fromCell ) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
	
	/**
	 * 跨行合并表格
	 * @param table 需要合并的表格
	 * @param col 表格所在列数
	 * @param fromRow 合并起始单元格位置
	 * @param toRow 合并结束单元格位置
	 */
	public void merge_cells_row(XWPFTable table, int col, int fromRow, int toRow) {  
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);  
            if ( rowIndex == fromRow ) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }
	
	/**
	 * 设置单元格的宽度
	 * @param cell 单元格对象
	 * @param width 宽度(厘米)
	 */
	private void set_cell_width(XWPFTableCell cell, int width) {
		CTTcPr ctTcPr = cell.getCTTc().isSetTcPr() ? cell.getCTTc().getTcPr() : cell.getCTTc().addNewTcPr();
		CTTblWidth ctTblWidth = ctTcPr.addNewTcW();
		ctTblWidth.setW( BigInteger.valueOf( width * 567) );
		ctTblWidth.setType(STTblWidth.DXA);
	}
	
	/**
	 * 创建一个表格模板
	 * @param courseNumber 课程的数量
	 * @return
	 */
	private XWPFTable create_table_template(int courseNumber) {
		//创建表格
		int rowNumber = NUMBER_ROW_TEMPLATE + courseNumber;
		XWPFTable table = docx.createTable(rowNumber, NUMBER_COLUMN);
		table.setTableAlignment(TableRowAlign.CENTER);
		CTTblPr tblPr =  table.getCTTbl().getTblPr();
		CTTblLayoutType t = tblPr.isSetTblLayout()?tblPr.getTblLayout():tblPr.addNewTblLayout();
		t.setType(STTblLayoutType.FIXED);
		merge_cells_column(table, 1, 4, 9);
		for ( int i = 0; i < 4; ++i) {
			merge_cells_row(table, i, 0, 1);
		}
		merge_cells_column(table, rowNumber - 1, 0, NUMBER_COLUMN - 1);
		merge_cells_column(table, rowNumber - 2, 0, 2);
		merge_cells_column(table, rowNumber - 2, 3, NUMBER_COLUMN - 1);
		merge_cells_column(table, rowNumber - 3, 0, 2);
		merge_cells_column(table, rowNumber - 3, 3, NUMBER_COLUMN - 1);
		XWPFTableCell cell = null;
		for (int index = 0; index < NUMBER_COLUMN; ++index) {
			cell = table.getRow(0).getCell(index);
			//cell.setText( FIELD[index] );
			set_cell_width(cell, WIDTHS[index]);
			set_cell_text(cell, FIELD[index], ParagraphAlignment.CENTER);
			//cell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
			//cell.setParagraph( create_text_paragraph( FIELD[index] ) );
		}
		set_cell_text(table.getRow(1).getCell(4), "请🗸表示", ParagraphAlignment.CENTER);
		set_cell_text(table.getRow(rowNumber - 3).getCell(0), "使用教材" , ParagraphAlignment.CENTER);
		set_cell_text(table.getRow(rowNumber - 2).getCell(0), "参考书目" , ParagraphAlignment.CENTER);
		cell = table.getRow(rowNumber - 1).getCell(0);
		//为最后一个单元格设置文本
		set_cell_text(cell, "说明:", ParagraphAlignment.LEFT);
		cell.getParagraphArray(0).getRuns().get(0).setFontSize(FONT_INSTRUCTIONS_SIZE);
		create_cell_text(cell, INSTRUCTIONS_1, FONT_INSTRUCTIONS_SIZE, ParagraphAlignment.LEFT);
		create_cell_text(cell, INSTRUCTIONS_2, FONT_INSTRUCTIONS_SIZE, ParagraphAlignment.LEFT);
		return table;
	}
	
	/**
	 * 创建一个表格
	 * @param courseList 课程列表
	 * @throws Exception
	 */
	private void create_table(List<CourseVO> courseList) throws Exception {
		XWPFTable table = create_table_template(courseList.size() );
		//加上偏移的行数
		CourseVO course = null;
		Iterator<CourseVO> iter = courseList.listIterator();
		for ( int index = 2; iter.hasNext(); ++index) {
			course = iter.next();
			set_cell_text(table.getRow(index).getCell(0), course.getWeek().toString() , ParagraphAlignment.CENTER);
			set_cell_text(table.getRow(index).getCell(1), course.getDayOfWeek() , ParagraphAlignment.CENTER);
			set_cell_text(table.getRow(index).getCell(2), course.getTimeCourseIndex() ,ParagraphAlignment.CENTER);
			set_cell_text(table.getRow(index).getCell(3), course.getContent() ,ParagraphAlignment.CENTER);
			set_cell_text(table.getRow(index).getCell(7), course.getIsHomework() ? "🗸" : "" , ParagraphAlignment.CENTER);
		}
	}
	
	/**
	 * 创建一个word文档
	 * @param user 用户对象
	 * @param subject 科目信息
	 * @param termName 学期名称
	 * @param courseList 科目所属课程表
	 * @return 返回一个word文档
	 * @throws Exception
	 */
	public static XWPFDocument create_word( User user, Subject subject, String termName, List<CourseVO> courseList ) throws Exception {
		WordUtil wordUtil = new WordUtil();
		wordUtil.create_word_template(user, subject, termName);
		wordUtil.create_table(courseList);
		wordUtil.create_text_paragraph(SIGNATURE);
		List<XWPFParagraph> paragraphs =  wordUtil.docx.getParagraphs();
		XWPFParagraph paragraph = paragraphs.get( paragraphs.size() - 1 );
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		return  wordUtil.get_docx();
	}

}
