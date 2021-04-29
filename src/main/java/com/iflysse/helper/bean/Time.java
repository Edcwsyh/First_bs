package com.iflysse.helper.bean;

import com.iflysse.helper.tools.CacheUtil;
import com.iflysse.helper.tools.Constant;

public class Time extends TimeBase{

	/**
	 * 一学期中要上课的周数
	 */
	private Integer weeks;
	/**
	 * 一周中要上课的时间段, 这是一个两位的十进制数(个位和十位), 十位表示周几上课, 个位表示一天中的第几大节课
	 */
	private Byte timeQuantum;
	
	public Time() {
		super();
		weeks = null;
		timeQuantum = null;
	}

	public Time(Integer id, Integer subject, Byte timeQuantum, String classroom, Integer weeks) {
		super(id, subject, classroom);
		this.weeks = weeks;
		this.timeQuantum = timeQuantum;
	}
	
	public Time(TimeVO timeVO) {
		super(timeVO.getId(), timeVO.getSubject(), timeVO.getClassroom() );
		this.weeks = 0;
		this.timeQuantum = 0;
		Byte startWeek = timeVO.getStarWeek();
		Byte endWeek = CacheUtil.currTerm.getWeeks();
		endWeek = endWeek <= timeVO.getEndWeek() ? endWeek : timeVO.getEndWeek();
		do {
			this.weeks |= 1 << (startWeek - 1);
		} while (++startWeek <= endWeek );
		//将额外周添加到this.weeks中
		addWeeks( timeVO.getAddWeek() );
		//删除weeks中的已存在周
		addWeeks( timeVO.getDeleteWeek() );
		//设置上课时间
		setTimeQuantum(timeVO.getWeek(), timeVO.getHowTime());
	}
	
	public void addWeeks(String weekStr) {
		if ( weekStr == null) {
			return ;
		}
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
		if ( weekStr == null) {
			return ;
		}
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

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeksValue(Integer weeks) {
		this.weeks = weeks;
	}

	public String getWeekString() {
		StringBuilder result = new StringBuilder();
		int startWeek = -1;
		for( int index = 0 ; index < Constant.MAX_WEEK; ++index) {
			if ( (weeks & (1 << index)) != 0 ) {
				startWeek = startWeek == -1 ? index : startWeek;
			} else if (startWeek != -1 ){
				result.append("第" + ( startWeek + 1 ) + "周");
				if ( index - 1  > startWeek ) {
					result.append("到第" + (index) + "周");
				}
				result.append(", ");
				startWeek = -1;
			}
		}
		return result.toString();
	}

	public Byte getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Byte week, Byte howTime) {
		timeQuantum = (byte) (week * 10 + howTime);
	}
	
	public Byte getWeek() {
		return (byte) (timeQuantum / 10);
	}
	
	public Byte getHowTime() {
		return (byte) (timeQuantum % 10);
	}
	/**
	 * 
	 * @param time
	 * @return 若返回0则表示合并成功, 否则为合并失败, 返回值 > 1表示this时间段较为靠后, 反之则较为靠前
	 */
	public int merage(Time time) {
		int buf = this.timeQuantum - time.getTimeQuantum();
		if( buf == 0 ) {
			this.weeks |= time.getWeeks();
		}
		return buf;
	}
}
