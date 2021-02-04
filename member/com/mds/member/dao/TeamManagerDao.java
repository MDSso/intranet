package com.mds.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

@Repository
public class TeamManagerDao implements ITeamManagerDao {
	
	
	private JdbcTemplate template;
	
	@Autowired
	public TeamManagerDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	//같은팀의 모든 출석 리스트
	@Override
	public List<AttendanceVO> TeamAttList(PagingVO paging, Member member) {
		List<AttendanceVO> teamattlist=null;
		
		String sql= "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM attendance WHERE userteam = ? ORDER BY cdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		teamattlist=template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getUserTeam());
				ps.setInt(2,paging.getStart());
				ps.setInt(3,paging.getEnd());
		
			}
		} ,new RowMapper<AttendanceVO> (){
			public AttendanceVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				AttendanceVO Attendance = new AttendanceVO();
				Attendance.setMemId(rs.getString("memid"));
				Attendance.setUserName(rs.getString("username"));
				Attendance.setcDate(rs.getDate("cdate"));
				Attendance.setOffWork(rs.getString("offwork"));
				Attendance.setOnWork(rs.getString("onwork"));
				Attendance.setStatus(rs.getString("status"));
				Attendance.setReason(rs.getString("reason"));
				return Attendance;
			}
			
		});
		
		return teamattlist;
	}

	//팀의 모든 출석리스트 개수
	@Override
	public int TeamAttTotal(Member member) {
		
		String sql = "SELECT COUNT(*) FROM attendance WHERE userteam = ?";
		int atttotal = template.queryForObject(sql,Integer.class,member.getUserTeam());
		return atttotal;
	}
	
	//출석 조회 리스트
	@Override
	public List<AttendanceVO> TeamAttSearch(PagingVO paging, Member member, AttSearchVO search) {
		List<AttendanceVO> attsearchlist = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM attendance WHERE userteam = ? AND UPPER(username) LIKE UPPER(?) AND cdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD') ORDER BY cdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		attsearchlist = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getUserTeam());
				ps.setString(2, "%" + search.getUserName() + "%");
				ps.setString(3,search.getStart());
				ps.setString(4,search.getEnd());
				ps.setInt(5, paging.getStart());
				ps.setInt(6, paging.getEnd());
				
			}
		},new RowMapper<AttendanceVO> (){
			public AttendanceVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				AttendanceVO Attendance = new AttendanceVO();
				Attendance.setMemId(rs.getString("memid"));
				Attendance.setUserName(rs.getString("username"));
				Attendance.setcDate(rs.getDate("cdate"));
				Attendance.setOffWork(rs.getString("offwork"));
				Attendance.setOnWork(rs.getString("onwork"));
				Attendance.setStatus(rs.getString("status"));
				Attendance.setReason(rs.getString("reason"));
				return Attendance;
			}		
		});
		return attsearchlist;
	}
	
	//출석 조회 리스트 개수
	@Override
	public int TeamAttSearchTotal(Member member,AttSearchVO search) {
		int searchtotal;
		String sql = "SELECT COUNT(*) FROM attendance WHERE userteam = ? AND UPPER(username) LIKE UPPER(?) AND cdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')";
		searchtotal = template.queryForObject(sql, Integer.class,member.getUserTeam(), "%"+search.getUserName() + "%",search.getStart(),search.getEnd());
		return searchtotal;
	}
	
	//출석 상태 수정
	@Override
	public AttendanceVO AttUpdate(AttendanceVO attendance) {
		List<AttendanceVO> sattendance=null;
		String sql = "SELECT * FROM attendance WHERE memid = ? AND cdate = ?";
		sattendance = template.query(sql,new RowMapper<AttendanceVO>() {
			@Override
			public AttendanceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AttendanceVO attendances=new AttendanceVO();
				attendances.setUserName(rs.getString("username"));
				attendances.setReason(rs.getString("reason"));
				attendances.setStatus(rs.getString("status"));
				attendances.setcDate(rs.getDate("cdate"));
				attendances.setMemId(rs.getString("memid"));
				return attendances;
			}
			
	}, attendance.getMemId(),attendance.getcDate());
		return sattendance.get(0);
	}

	//모든 자산 리스트
	@Override
	public List<PropertyVO> PropertyList(PagingVO paging) {
		List<PropertyVO> propertys = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM property ORDER BY idx desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		
		propertys = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,paging.getStart());
				ps.setInt(2,paging.getEnd());
		
			}
		} ,new RowMapper<PropertyVO> (){
			public PropertyVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				PropertyVO property = new PropertyVO();
				property.setPronum(rs.getInt("idx"));
				property.setImgpath(rs.getString("imgpath"));
				property.setProName(rs.getString("proName"));
				property.setSerialNum(rs.getString("serialNum"));
				property.setTeam(rs.getString("Team"));
				property.setPurcday(rs.getDate("purcday"));
				
				return property;
			}
			
		});
		
		return propertys;
	}
	
	//종 자산 수
	@Override
	public int PropertyTotal() {
		int totallist;
		String sql= "SELECT COUNT(*) FROM property";
		totallist = template.queryForObject(sql,Integer.class);
		
		return totallist;
	}
	
	@Override
	public List<PropertyVO> PropertySearchList(PagingVO paging, PropertyVO property) {
		List<PropertyVO> propertys = null;
		String sql="SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM property WHERE UPPER(proname) LIKE UPPER(?) AND UPPER(serialnum) LIKE UPPER(?) AND UPPER(team) LIKE UPPER(?) ORDER BY idx asc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		propertys = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,"%"+property.getProName()+"%");
				ps.setString(2,"%"+property.getSerialNum()+"%");
				ps.setString(3,"%"+property.getTeam()+"%");
				ps.setInt(4,paging.getStart());
				ps.setInt(5, paging.getEnd());
		
			}
		},  new RowMapper<PropertyVO>() {

			@Override
			public PropertyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PropertyVO pro = new PropertyVO();
				pro.setImgpath(rs.getString("imgpath"));
				pro.setProName(rs.getString("proname"));
				pro.setPronum(rs.getInt("idx"));
				pro.setSerialNum(rs.getString("serialnum"));
				pro.setTeam(rs.getString("team"));
				pro.setPurcday(rs.getDate("purcday"));
				return pro;
			}
			
		});
		return propertys;
	}
	
	//조회 리스트 수
	@Override
	public int PropertySearchTotal(PropertyVO property) {
		int searchtotalist;
		String sql="SELECT COUNT(*) FROM property WHERE UPPER(proname) LIKE UPPER(?) AND UPPER(serialnum) LIKE UPPER(?) AND UPPER(team) LIKE UPPER(?)";
		searchtotalist = template.queryForObject(sql,Integer.class,"%"+property.getProName()+"%","%"+property.getSerialNum()+"%","%"+property.getTeam()+"%");
		
		return searchtotalist;
	}

	@Override
	public void attUpdateOK(AttendanceVO attendance) {
		String sql= "UPDATE attendance SET reason = ?, status = ? WHERE memid = ? AND cdate = ?";
		template.update(sql,attendance.getReason(),attendance.getStatus(),attendance.getMemId(),attendance.getcDate());
	}

	
	
	
	
	
	
}
