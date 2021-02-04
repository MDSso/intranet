package com.mds.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.dao.SalesDao;

@Service
public class SalesService implements ISalesService{

	@Autowired
	SalesDao dao;
	
	@Override
	public Map<String, Object> DOWTake() {
		
		return dao.DOWTake();
	}

}
