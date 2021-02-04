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
import com.mds.member.EpListVO;
import com.mds.member.ExpenditureVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

@Repository
public class ExpenDitureDao implements IExpenditureDao{

	private JdbcTemplate template;
	
	@Autowired
	public ExpenDitureDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	//지출결의 전체 리스트
	@Override
	public List<EpListVO> epListSelect(PagingVO paging, Member member) {
		List<EpListVO> eplists = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM eplist WHERE memid = ? ORDER BY rgdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		eplists = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getMemId());
				ps.setInt(2,paging.getStart());
				ps.setInt(3,paging.getEnd());
		
			}
		} ,new RowMapper<EpListVO> (){
			public EpListVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				EpListVO eplist = new EpListVO();
				eplist.setIdx(rs.getInt("idx"));
				eplist.setMemId(rs.getString("memid"));
				eplist.setRgDate(rs.getDate("rgdate"));
				eplist.setUserName(rs.getString("username"));
				eplist.setUserTeam(rs.getString("userteam"));
				eplist.setTprice(rs.getInt("tprice"));
				return eplist;
			}
			
		});
		return eplists;
	}
	
	//지출결의 전체 리스트 개수
	@Override
	public int eplisttotal(Member member) {
		String sql = "SELECT COUNT(*) FROM eplist WHERE memid = ?";
		int total = template.queryForObject(sql, Integer.class, member.getMemId());
		return total;
	}
	

	@Override
	public List<EpListVO> epSearchList(PagingVO paging, Member member, AttSearchVO attsearch) {
		List<EpListVO> epsearchlist = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM eplist WHERE memid = ? AND rgdate between to_date(? ,'YYYY-MM-DD') and to_date(? ,'YYYY-MM-DD') ORDER BY rgdate desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		System.out.println(attsearch.getStart());
		epsearchlist = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getMemId());
				ps.setString(2, attsearch.getStart());
				ps.setString(3, attsearch.getEnd());
				ps.setInt(4,paging.getStart());
				ps.setInt(5,paging.getEnd());
		
			}
		} ,new RowMapper<EpListVO> (){
			public EpListVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				EpListVO eplist = new EpListVO();
				eplist.setIdx(rs.getInt("idx"));
				eplist.setMemId(rs.getString("memid"));
				eplist.setRgDate(rs.getDate("rgdate"));
				eplist.setUserName(rs.getString("username"));
				eplist.setUserTeam(rs.getString("userteam"));
				eplist.setTprice(rs.getInt("tprice"));
				return eplist;
			}
			
		});
		return epsearchlist;
	}

	@Override
	public int epsearchtotal(Member member, AttSearchVO attsearch) {
		String sql= "SELECT COUNT(*) FROM eplist WHERE memid = ? AND rgdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')";
		int searchtotal = template.queryForObject(sql,Integer.class,member.getMemId(),attsearch.getStart(),attsearch.getEnd());
		System.out.println(searchtotal);
		return searchtotal;
	}

	
	//지출결의 작성
	@Override
	public void expenditurInsert(Member member, ExpenditureVO expenditure) {
		int tprice=0;
		int epsize =expenditure.getExpenditureVoList().size();
		String sql1 =  "INSERT INTO eplist (memid,username,rgdate,userteam,tprice,idx) values(?,?,?,?,?,EXPENDITURE_IDX.nextval)";
		String sql2 = "SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'EXPENDITURE_IDX'";
		int idx = template.queryForObject(sql2,Integer.class);
		String sql = "INSERT INTO expenditure (usedate,wtUse, price, pouse, memid, userteam,userName,idx) values (?,?,?,?,?,?,?,?)";
		for(int i = 0; i < epsize; i++) {
			template.update(sql,expenditure.getExpenditureVoList().get(i).getUseDate(),expenditure.getExpenditureVoList().get(i).getWtUse(),expenditure.getExpenditureVoList().get(i).getPrice(),expenditure.getExpenditureVoList().get(i).getPoUse(),member.getMemId(),member.getUserTeam(),member.getUserName(),idx);
			
		}
		for(int i = 0; i< epsize; i++) {
			tprice = tprice + expenditure.getExpenditureVoList().get(i).getPrice();
		}
		template.update(sql1,member.getMemId(),member.getUserName(),expenditure.getRgDate(),member.getUserTeam(),tprice);
		
	}

	//지출 상세
	@Override
	public List<ExpenditureVO> epDetail(EpListVO eplist) {
		List<ExpenditureVO> expenditures = null;
		String sql = "SELECT * FROM expenditure WHERE idx = ? ";
		expenditures = template.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, eplist.getIdx());
			}
		} ,new RowMapper<ExpenditureVO> (){
			public ExpenditureVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				ExpenditureVO expenditure = new ExpenditureVO();
				expenditure.setIdx(rs.getInt("idx"));
				expenditure.setMemId(rs.getString("memid"));
				expenditure.setRgDate(rs.getDate("rgdate"));
				expenditure.setUseDate(rs.getString("usedate").toString().substring(0, 10));
				expenditure.setWtUse(rs.getString("wtuse"));
				expenditure.setPrice(rs.getInt("price"));
				expenditure.setPoUse(rs.getString("pouse"));
				return expenditure;
			}
			
		});
		return expenditures;
	}

	@Override
	public void epRemove(EpListVO eplist) {
		String sql1 =  "DELETE eplist WHERE idx = ?" ;
		String sql2 =  "DELETE expenditure WHERE idx = ?" ;
		template.update(sql1,eplist.getIdx());
		template.update(sql2,eplist.getIdx());
	}

	@Override
	public List<ExpenditureVO> epUpdate(EpListVO eplist) {
		String sql = "SELECT * FROM expenditure WHERE idx = ?";
		List<ExpenditureVO> expenditures = template.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, eplist.getIdx());
			}
		} ,new RowMapper<ExpenditureVO> (){
			public ExpenditureVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				ExpenditureVO expenditure = new ExpenditureVO();
				expenditure.setIdx(rs.getInt("idx"));
				expenditure.setUseDate(rs.getString("usedate"));
				expenditure.setWtUse(rs.getString("wtuse"));
				expenditure.setPrice(rs.getInt("price"));
				expenditure.setPoUse(rs.getString("pouse"));
				return expenditure;
			}
			
		});
		return expenditures;
	}

	@Override
	public void epUpdateOK(ExpenditureVO expenditure,Member member) {
		String sql1 = "DELETE expenditure WHERE idx = ?" ;
		String sql2 = "INSERT INTO expenditure (usedate,wtUse, price, pouse, memid, userteam,userName,idx) values (?,?,?,?,?,?,?,?)";
		String sql3 = "UPDATE eplist SET tprice = ? WHERE idx = ?";
		int tprice = 0;
		template.update(sql1,expenditure.getIdx());
		int epsize =expenditure.getExpenditureVoList().size();
		for(int i = 0; i < epsize; i++) {
			template.update(sql2,expenditure.getExpenditureVoList().get(i).getUseDate(),expenditure.getExpenditureVoList().get(i).getWtUse(),expenditure.getExpenditureVoList().get(i).getPrice(),expenditure.getExpenditureVoList().get(i).getPoUse(),member.getMemId(),member.getUserTeam(),member.getUserName(),expenditure.getIdx());
			tprice = tprice + expenditure.getExpenditureVoList().get(i).getPrice();
		}
		template.update(sql3,tprice,expenditure.getIdx());
	}

	@Override
	public int totalPrice(EpListVO eplist) {
		String sql = "SELECT tprice FROM eplist WHERE idx = ?";
		int tprice = template.queryForObject(sql, Integer.class,eplist.getIdx());
		return tprice;
	}

}
