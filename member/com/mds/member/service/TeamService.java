package com.mds.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.TeamVO;
import com.mds.member.dao.TeamDao;

@Service
public class TeamService implements ITeamService{
	
	@Autowired
	TeamDao dao;

	@Override
	public List<TeamVO> teams() {
		return dao.teams();
	}
	
}
	

