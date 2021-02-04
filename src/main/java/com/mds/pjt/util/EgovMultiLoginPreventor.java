package com.mds.pjt.util;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class EgovMultiLoginPreventor {
	public static ConcurrentHashMap<String, HttpSession> loginUsers = new ConcurrentHashMap<String, HttpSession>();
	 
    public static boolean findByLoginId(String loginId){
        return loginUsers.containsKey(loginId);
    }
    
    //loginUsers Map을 뒤져서 해당 사용자 데이터를 지움
    public static void invalidateByLoginId(String loginId){
        Enumeration<String> e = loginUsers.keys();
        while (e.hasMoreElements()){
            String key = (String) e.nextElement();
            if (key.equals(loginId)){
                loginUsers.get(key).invalidate();
            }
        }
    }
}


