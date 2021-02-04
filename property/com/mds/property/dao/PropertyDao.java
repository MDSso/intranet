package com.mds.property.dao;

import java.sql.Date;
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
import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

@Repository
public class PropertyDao implements IPropertyDao {
	
	private JdbcTemplate template;
	Date date = new Date(System.currentTimeMillis());
	
	@Autowired
	public PropertyDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	//property 등록
	@Override
	public int propertyInsert(PropertyVO property) {
		int result = 0;
		final String sql = "INSERT INTO PROPERTY (idx,imgpath, proname, serialnum, team, purcday) values (PROPERTY_IDX.nextval,?,?,?,?,?)";
		result = template.update(sql, property.getImgpath(), property.getProName(), property.getSerialNum(), property.getTeam(), date);
		return result;
	}

	@Override
	public PropertyVO propertySelect(PropertyVO property) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void propertyUpdate(PropertyVO property) {
		
		final String sql = "UPDATE property SET proname = ?, serialnum = ?, team = ?, imgpath = ? WHERE idx = ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, property.getProName());
				pstmt.setString(2, property.getSerialNum());
				pstmt.setString(3, property.getTeam());
				pstmt.setString(4, property.getImgpath());
				pstmt.setInt(5, property.getPronum());
			}
		});
	}
	
	
	//property 삭제
	@Override
	public void propertyDelete(PropertyVO property) {
		
		final String sql = "DELETE property WHERE idx = ? " ;
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, property.getPronum());
			}
		});

	}

	//property 리스트 페이지
	@Override
	public List<PropertyVO> propertyList(PagingVO paging,Member member) {
		
		List<PropertyVO> propertys = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM property WHERE team = ? ORDER BY idx desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		
		propertys = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,member.getUserTeam());
				ps.setInt(2,paging.getStart());
				ps.setInt(3,paging.getEnd());
		
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

	//property 상세
	@Override
	public PropertyVO propertyDetail(final PropertyVO property) {
		List<PropertyVO> propertyinfo = null;
		String sql = "SELECT * FROM property WHERE idx = ?";
		
		propertyinfo = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,property.getPronum());
		
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
				
			
		
		return propertyinfo.get(0);
	}

	//property search
	@Override
	public List<PropertyVO> propertySearch(PropertyVO property,PagingVO paging,Member member) {
		List<PropertyVO> propertys = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM property WHERE UPPER(proname) LIKE UPPER(?) AND UPPER(serialnum) LIKE UPPER(?) AND team =? ORDER BY idx asc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		propertys =  template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,"%"+property.getProName()+"%");
				ps.setString(2,"%"+property.getSerialNum()+"%");
				ps.setString(3,member.getUserTeam());
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

	
	//전체 리스트 카운트
	@Override
	public int totallist(Member member) {
		int totallist;
		String sql= "SELECT COUNT(*) FROM property WHERE team = ?";
		totallist = template.queryForObject(sql,Integer.class,member.getUserTeam());
		
		return totallist;
	}

	//서치 리스트 카운트
	@Override
	public int searchtotallist(PropertyVO property,Member member) {
		int searchtotalist;
		String sql="SELECT COUNT(*) FROM property WHERE UPPER(proname) LIKE UPPER(?) AND UPPER(serialnum) LIKE UPPER(?) AND team = ?";
		searchtotalist = template.queryForObject(sql,Integer.class,"%"+property.getProName()+"%","%"+property.getSerialNum()+"%",member.getUserTeam());
		
		return searchtotalist;
	}
	
	
}
