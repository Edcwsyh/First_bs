package com.iflysse.helper.tools;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.User;

public class WordUtil {
	
	private XWPFDocument docx;
	
	private WordUtil() {
		docx = new XWPFDocument();
	}
	
	public XWPFDocument get_docx() {
		return docx;
	}

	/**
	 * 标题字体大小
	 */
	private static final int FONT_TITLE_SIZE = 16;
	
	/**
	 * 正文字体大小
	 */
	private static final int FONT_TEXT_SIZE = 12;
	/**
	 * 列的数量
	 */
	private static final int NUMBER_COLUMN = 11;
	/**
	 * 行的模板数量
	 */
	private static final int NUMBER_ROW_TEMPLATE = 6;
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
	 * 创建一个标题段落
	 * @param text
	 * @return
	 */
	private XWPFParagraph create_title_paragraph( String text ) {
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setFontSize(FONT_TITLE_SIZE);
		run.setFontFamily("宋体");
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
		run.setFontFamily("宋体");
		run.setText( text );
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		return paragraph;
	}
	
	public void create_word_template( User user, Subject subject, String termName ) {
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
	
	private XWPFTable create_table_template(int courseNumber) {
		//创建表格
		int rowNumber = NUMBER_ROW_TEMPLATE + courseNumber;
		XWPFTable table = docx.createTable(rowNumber, NUMBER_COLUMN);
		table.setTableAlignment(TableRowAlign.CENTER);
		table.setWidth(5 * 1440);
		merge_cells_column(table, 1, 4, 9);
		for ( int i = 0; i < 4; ++i) {
			merge_cells_row(table, i, 0, 1);
		}
		merge_cells_column(table, rowNumber - 1, 0, NUMBER_COLUMN - 1);
		merge_cells_column(table, rowNumber - 2, 0, 2);
		merge_cells_column(table, rowNumber - 2, 3, NUMBER_COLUMN - 1);
		merge_cells_column(table, rowNumber - 3, 0, 2);
		merge_cells_column(table, rowNumber - 3, 3, NUMBER_COLUMN - 1);
		CTTbl ttbl = table.getCTTbl(); 
		CTTblGrid tblGrid = ttbl.addNewTblGrid();
		CTTblGridCol gridCol = null;
		XWPFTableCell cell = null;
		for (int index = 0; index < NUMBER_COLUMN; ++index) {
			cell = table.getRow(0).getCell(index);
			cell.setText(FIELD[index]);
			
		}
		return table;
	}
	
	private void create_table(List<CourseVO> courseList) {
		XWPFTable table = create_table_template(courseList.size() );
		
	}
	
	public static XWPFDocument create_word( User user, Subject subject, String termName, List<CourseVO> courseList ) {
		WordUtil wordUtil = new WordUtil();
		wordUtil.create_word_template(user, subject, termName);
		wordUtil.create_table(courseList);
		return  wordUtil.get_docx();
	}

}
