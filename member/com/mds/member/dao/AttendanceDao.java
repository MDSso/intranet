package com.mds.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

@Repository
public class AttendanceDao implements IAttendanceDao{

	private JdbcTemplate template;
	
	
	
	@Autowired
	public AttendanceDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	//모든 출석 리스트
	@Override
	public List<AttendanceVO> AttendanceList(PagingVO paging,Member member) {
		List<AttendanceVO> Attendances;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM attendance WHERE memid = ? ORDER BY cdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		Attendances = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getMemId());
				ps.setInt(2,paging.getStart());
				ps.setInt(3,paging.getEnd());
		
			}
		} ,new RowMapper<AttendanceVO> (){
			public AttendanceVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				AttendanceVO Attendance = new AttendanceVO();
				Attendance.setUserName(rs.getString("username"));
				Attendance.setcDate(rs.getDate("cdate"));
				Attendance.setOffWork(rs.getString("offwork"));
				Attendance.setOnWork(rs.getString("onwork"));
				Attendance.setStatus(rs.getString("status"));
				return Attendance;
			}
			
		});
		return Attendances;
	}
	
	//모든 출석 리스트 개수
	@Override
	public int totalList(Member member) {
		int totallist;
		String sql= "SELECT COUNT(*) FROM attendance WHERE memid = ?";
		totallist = template.queryForObject(sql,Integer.class,member.getMemId());
		
		return totallist;
	}
	
	//조회 출석 리스트 개수
	@Override
	public int searchList(Member member, AttSearchVO attendance) {
		int searchlist;
		String sql= "SELECT COUNT(*) FROM attendance WHERE memid = ? AND cdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')";
		searchlist = template.queryForObject(sql,Integer.class,member.getMemId(),attendance.getStart(),attendance.getEnd());
		return searchlist;
	}
	
	
	
	//마지막 출석일
	public Date LastOnAttendance(Member member) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		Date lastonatt;
		String sql= "SELECT onwork FROM attendance WHERE memid = ? AND cdate = ?";
		try {
		lastonatt = template.queryForObject(sql,Date.class,member.getMemId(),cdate);}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
		return lastonatt;
	}

	//마지막 출석의 퇴근일
	@Override
	public Date LastOffAttendance(Member member) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		Date lastoffatt;
		String sql= "SELECT offwork FROM attendance WHERE memid = ? AND cdate = ?";
		try {
		lastoffatt = template.queryForObject(sql,Date.class,member.getMemId(),cdate);}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
		return lastoffatt;
	}

	//출석
	@Override
	public void onWork(Member member) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		String sql = "INSERT INTO attendance (memid,onwork,status,cdate,username,userteam) values (?,?,?,?,?,?)";
		template.update(sql,member.getMemId(),date,"정상출근",cdate,member.getUserName(),member.getUserTeam());
	}
	
	//퇴근
	@Override
	public void offWork(String memId) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		String sql = "UPDATE attendance SET offwork = ? WHERE memid = ? AND cdate = ?";
		template.update(sql,date,memId,cdate);
		
	}

	//마지막으로 출석한 날 구하기
	@Override
	public String LastCdate(Member member) {
		Date lastoffatt;
		String cdate;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String sql= "SELECT * FROM(SELECT cdate FROM attendance WHERE memid = ? ORDER BY cdate DESC) WHERE ROWNUM = 1";
		try {
			//마지막 출석일자 구하기
			lastoffatt = template.queryForObject(sql,Date.class,member.getMemId());
			cdate = df.format(lastoffatt);
		}
		catch (EmptyResultDataAccessException e) {
			//마지막 출석일자가 없는경우 최초 출석을 하지않은 유저(신규유저)로 간주하여 null return
			System.out.println("최초 출석을 하지 않았습니다.");
			return null;
		}
		Date lastattday = lastoffatt;
		String cdaystring = df.format(new Date());
		Date currentday = null;
		try {
			currentday = df.parse(cdaystring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(currentday);
		cal.add(Calendar.DATE,-1);
		Date yesterday = cal.getTime();
		
		//마지막 출석일자가 어제 이거나 오늘과 같다면 null return 
		if(((lastattday.compareTo(yesterday) == 0)) || (lastattday.compareTo(currentday)==0)) {
			return null;
		}
		
		return cdate;
	}

	//출석 없을 시 자동 입력
	@Override
	public void AutoInsert(Member member,long diff,Date LastDate,Map<String,String> hollydaymap) {
		String sql = "INSERT INTO attendance (memid,status,cdate,username,userteam) values (?,?,?,?,?)";
		String dow = null ;
		//평일일경우 "결근" 주말의경우 "주말"로 결근 사유를 저장해 주기위해 배열 생성
		String[] week = {"주말","결근","결근","결근","결근","결근","주말"};
		
		/*-----------마지막으로 출근이 찍힌 날짜의 다음날부터 받아온 공휴일 Map의 데이터와 비교하여 공휴일인경우 "공휴일명"(ex: 광복절),
		 	평일일경우 "결근", 주말일경우 "주말"이 DB에 저장되게 한다------------------------------------------------------*/
		for(int t=1; t < diff; t++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(LastDate);
			cal.add(Calendar.DATE,t);
			Date date = cal.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String key = df.format(date);
			if(hollydaymap !=null ) {
				if((hollydaymap.get(key) != null)) {
					dow = hollydaymap.get(key);
				}
				else {
					dow=week[cal.get(Calendar.DAY_OF_WEEK)-1];
				}
			}
			
			
			template.update(sql,member.getMemId(),dow,cal.getTime(),member.getUserName(),member.getUserTeam());
		}
		//--------------------------------------------------------------------------------------------------
	}

	
	//조회
	@Override
	public List<AttendanceVO> AttSearchList(PagingVO paging, Member member,AttSearchVO attendance) {
		List<AttendanceVO> attendances = null;		
		String sql= "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM attendance WHERE memid = ? AND cdate between to_date(? ,'YYYY-MM-DD') and to_date(? ,'YYYY-MM-DD') ORDER BY cdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		System.out.println(attendance.getStart());
		attendances = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getMemId());
				ps.setString(2,attendance.getStart());
				ps.setString(3,attendance.getEnd());
				ps.setInt(4,paging.getStart());
				ps.setInt(5,paging.getEnd());
		
			}
		} ,new RowMapper<AttendanceVO> (){
			public AttendanceVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				AttendanceVO Attendance = new AttendanceVO();
				Attendance.setUserName(rs.getString("username"));
				Attendance.setcDate(rs.getDate("cdate"));
				Attendance.setOffWork(rs.getString("offwork"));
				Attendance.setOnWork(rs.getString("onwork"));
				Attendance.setStatus(rs.getString("status"));
				return Attendance;
			}
			
		});
		
		return attendances;
	}

}
