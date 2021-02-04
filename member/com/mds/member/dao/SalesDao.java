package com.mds.member.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class SalesDao implements ISalesDao{

	private JdbcTemplate template;
	
	@Autowired
	public SalesDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Map<String, Object> DOWTake() {
		
		Map<String,Object> dowtake = new HashMap<String,Object>();
		
		String sql = "select (select take from sales where to_char(sdate, 'DY')='월' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as MON, (select take from sales where to_char(sdate, 'DY')='화' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as TUE, (select take from sales where to_char(sdate, 'DY')='수' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as WEN, (select take from sales where to_char(sdate, 'DY')='목' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as TUR, (select take from sales where to_char(sdate, 'DY')='금' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as FRI, (select take from sales where to_char(sdate, 'DY')='토' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as SAT, (select take from sales where to_char(sdate, 'DY')='일' and to_char(sdate, 'YYYYMMDD') between to_char(sysdate-6, 'YYYYMMDD') and to_char(sysdate, 'YYYYMMDD')) as SUN from sales where rownum=1";
		
		dowtake = template.queryForMap(sql);
		
		return dowtake;
	}

	@Override
	public void AutoInsertTake() {
		int crease = (int)(Math.random() * (10000 - 5000 + 1)) + 5000; // 매출액 랜덤값
		Date date = new Date();
		String sql="INSERT INTO sales (take, indecrease,sdate) values (?,?,?)";
		template.update(sql,crease,0,date);
	}		
}
