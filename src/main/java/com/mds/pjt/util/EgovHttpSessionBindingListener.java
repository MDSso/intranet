package com.mds.pjt.util;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class EgovHttpSessionBindingListener implements HttpSessionBindingListener{
	
	//이미 로그인된 ID 인지 확인 후 맞다면 해당 사용자 정보를 지우고  해당 ID에 대한 새로운 새션정보 Map에 저장
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		 if (EgovMultiLoginPreventor.findByLoginId(event.getName())){
	            EgovMultiLoginPreventor.invalidateByLoginId(event.getName());
	        }
	        EgovMultiLoginPreventor.loginUsers.put(event.getName(), event.getSession());
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		EgovMultiLoginPreventor.loginUsers.remove(event.getName(), event.getSession());
	}

}

