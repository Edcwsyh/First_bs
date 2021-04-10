package com.iflysse.helper.bean;

import com.iflysse.helper.controller.CacheController;
import com.iflysse.helper.controller.TermController;

public class Time extends TimeBase{

	private Integer weeks;
	private Byte timeQuantum;

	public Time(Integer id, Integer subject, Integer timeQuantum, String classroom, Integer weeks) {
		super(id, subject, classroom);
		this.weeks = weeks;
	}
	
	public Time(TimeVO timeVO) {
		super(timeVO.getId(), timeVO.getSubject(), timeVO.getClassroom() );
		Byte startWeek = timeVO.getStarWeek();
		Byte endWeek = CacheController.termBuffer.getWeeks();
		endWeek = endWeek <= timeVO.getEndWeek() ? endWeek : timeVO.getEndWeek();
		do {
			this.weeks |= 1 << (startWeek - 1);
		} while (startWeek < endWeek );
		//将额外周添加到this.weeks中
		addWeeks( timeVO.getAddWeek() );
		//删除weeks中的已存在周
		deleteWeeks( timeVO.getDeleteWeek() );
		//设置上课时间
		setTimeQuantum(timeVO.getWeek(), timeVO.getHowTime());
	}
	
	public void addWeeks(String weekStr) {
		int temp = 0;
		for( int index = 0; index < weekStr.length(); ++index ) {
			switch ( weekStr.charAt(index) ) {
				case 0: case 1 : case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
					temp = temp * 10 + weekStr.charAt(index) - '0';
					break;
				case ',':
					this.weeks |= 1 << (temp - 1);
					temp = 0;
					break;
				case ' '://略过空字符
					break;
				default :
					//throw;
			}
		}
	}
	
	public void deleteWeeks(String weekStr) {
		int temp = 0;
		for( int index = 0; index < weekStr.length(); ++index ) {
			switch ( weekStr.charAt(index) ) {
				case 0: case 1 : case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
					temp = temp * 10 + weekStr.charAt(index) - '0';
					break;
				case ',':
					this.weeks &= ~(1 << (temp - 1) );
					temp = 0;
					break;
				case ' '://略过空字符
					break;
				default :
					//throw;
			}
		}
	}

	public Integer getWeeksValue() {
		return weeks;
	}

	public void setWeeksValue(Integer weeks) {
		this.weeks = weeks;
	}

	public String getWeeks() {
		StringBuilder result = new StringBuilder();
		int startWeek = 0;
		for( int index = 0 ; index < CacheController.termBuffer.getWeeks(); ++index) {
			if ( (weeks & (1 << index)) != 0 ) {
				startWeek = startWeek == 0 ? index : startWeek;
			} else {
				result.append("第" + startWeek + "周");
				if ( index - 1 > startWeek ) {
					result.append("到第" + (index - 1) + "周");
				}
				result.append(", ");
			}
		}
		return result.toString();
	}

	public Byte getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Byte week, Byte howTime) {
		timeQuantum = week;
		timeQuantum = (byte) (timeQuantum * 10 + howTime);
	}
	
	public Byte getWeek() {
		return (byte) (timeQuantum / 10);
	}
	
	public Byte getHowTime() {
		return (byte) (timeQuantum % 10);
	}
}
