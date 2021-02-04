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
import com.mds.member.Member;
import com.mds.property.PagingVO;

@Repository
public class MemberDao implements IMemberDao {

	private JdbcTemplate template;
	
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	//맴버 등록
	@Override
	public void memberInsert(Member member) {
		final String sql = "INSERT INTO MEMBER (idx,username, userteam, etdate, memid, mempw, confirmpw, zipcode, snaddress, dtaddress, memmail, memgd, mempo, phonenum) values (MEMBER_IDX.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		template.update(sql, member.getUserName(), member.getUserTeam(), member.getEtDate(), member.getMemId(), member.getMemPw(), member.getConfirmPw(), member.getZipCode(), member.getSnAddress(), member.getDtAddress(), member.getMemMail(), member.getMemGd(), member.getMemPo(),member.getPhoneNum());
	}

	//로그인 
	@Override
	public Member memberSelect(final Member member) {
		
		List<Member> members = null;
		final String sql = "SELECT * FROM member WHERE memId = ?";
		
		members=template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, member.getMemId());
			}
		},  new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("mempw"));
				mem.setUserName(rs.getString("username"));
				mem.setUserTeam(rs.getString("userteam"));
				mem.setMemGd(rs.getString("memgd"));
				return mem;
			}
			
		});
				
		if(members.isEmpty()) return null;
		
		return members.get(0);
		
	}

	//유저 수정 완료
	@Override
	public void memberUpdate(Member member,int idx) {
		String sql = "UPDATE member SET username = ?, mempo = ?, phonenum = ?, userteam = ?, zipcode = ?, snaddress = ?, dtaddress = ?, memmail = ?, memgd = ?, etdate = ? WHERE idx = ?";
		template.update(sql,member.getUserName(),member.getMemPo(),member.getPhoneNum(),member.getUserTeam(),member.getZipCode(),member.getSnAddress(),member.getDtAddress(),member.getMemMail(),member.getMemGd(),member.getEtDate(),idx);
	}

	@Override
	public void memberDelete(int idx) {
		
		String sql = "DELETE member WHERE idx = ? " ;
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, idx);
			}
		});
		
	}

	@Override
	public List<Member> memberListAll(PagingVO paging) {
		List<Member> members;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM member ORDER BY idx desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		members = template.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,paging.getStart());
				ps.setInt(2,paging.getEnd());
		
			}
		} ,new RowMapper<Member> (){
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member();
				member.setIdx(rs.getInt("idx"));
				member.setUserName(rs.getString("username"));
				member.setMemPo(rs.getString("mempo"));
				member.setUserTeam(rs.getString("userteam"));
				member.setPhoneNum(rs.getString("phonenum"));
				member.setEtDate(rs.getDate("etdate"));
				
				return member;
			}
			
		});
		
		return members;
	}

	@Override
	public int totalList() {
		int totallist;
		String sql= "SELECT COUNT(*) FROM member";
		totallist = template.queryForObject(sql,Integer.class);
		
		return totallist;
	}

	@Override
	public int searchList(Member member) {
		int searchlist;
		String sql="SELECT COUNT(*) FROM member WHERE UPPER(username) LIKE UPPER(?) AND UPPER(userteam) LIKE UPPER(?) AND UPPER(mempo) LIKE UPPER(?)";
		searchlist = template.queryForObject(sql,Integer.class,"%"+member.getUserName()+"%","%"+member.getUserTeam()+"%","%"+member.getMemPo()+"%");
		return searchlist;
	}

	@Override
	public List<Member> memberSerchList(Member member, PagingVO paging) {
		List<Member> members = null;
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT FROM (SELECT * FROM member WHERE UPPER(username) LIKE UPPER(?) AND UPPER(userteam) LIKE UPPER(?) AND UPPER(mempo) LIKE UPPER(?) ORDER BY idx desc ) A ) WHERE RNUM > ? AND RNUM <= ?";
		members = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,"%"+member.getUserName()+"%");
				ps.setString(2,"%"+member.getUserTeam()+"%");
				ps.setString(3,"%"+member.getMemPo()+"%");
				ps.setInt(4,paging.getStart());
				ps.setInt(5, paging.getEnd());
		
			}
		},  new RowMapper<Member>() {  

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setIdx(rs.getInt("idx")); 
				member.setUserName(rs.getNString("username"));
				member.setEtDate(rs.getDate("etdate"));
				member.setPhoneNum(rs.getString("phonenum"));
				member.setMemPo(rs.getString("mempo"));
				member.setUserTeam(rs.getString("userteam"));
				
				return member;
			}
			
		});
		
		return members;
	}

	@Override
	public Member memDetailSelect(final int idx) {
		List<Member> member = null;
		String sql = "SELECT * FROM MEMBER WHERE idx = ?";
		
		member = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,idx);
		
			}
		},  new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setIdx(rs.getInt("idx"));
				member.setUserName(rs.getString("username"));
				member.setMemPo(rs.getString("mempo"));
				member.setPhoneNum(rs.getString("phonenum"));
				member.setUserTeam(rs.getString("userTeam"));
				member.setEtDate(rs.getDate("etdate"));
				member.setZipCode(rs.getString("zipcode"));
				member.setSnAddress(rs.getString("snaddress"));
				member.setDtAddress(rs.getString("dtaddress"));
				member.setMemMail(rs.getString("memMail"));
				member.setMemGd(rs.getString("memgd"));
				return member;
			}
			
		});
		return member.get(0);
	}

	//아이디 리스트
	@Override
	public List<Member> memIdList() {
		List<Member> idList = null;
		String sql = "SELECT memId FROM MEMBER";
		
		idList = template.query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setMemId(rs.getString("memid"));
				
				return member;
			}
			
		});
		
		return idList;
	}
	
	


	

}
