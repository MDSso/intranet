package com.mds.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mds.member.TeamVO;

@Repository
public class TeamDao implements ITeamDao{
	
	private JdbcTemplate template;
	
	@Autowired
	public TeamDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<TeamVO> teams() {
		List<TeamVO> teamlist = null;
		String sql = " SELECT teamName FROM TEAM";
		
		teamlist = template.query(sql, new RowMapper<TeamVO> (){
			public TeamVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				TeamVO team = new TeamVO();
				team.setTeamName(rs.getString("teamName"));
				return team;
			}
			
		});
		
		return teamlist;
	}
	
}
