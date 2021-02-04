package com.mds.pjt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Hollydays {
	
	public Map<String,String> getHollyday(Date date) throws IOException{
		//얻어온 공휴일 정보를 저장할  Map
		Map<String,String> hollyday = new HashMap<String,String>();
		JSONParser jsonParse = new JSONParser();
		//특일정보 api의 조회에 사용될 파라미터 년,월 변수 선언
		String solyear = null;
		String solmonth = null;
		//마지막 출석일자와 금일간 달 간격을 저장(ex: 마지막 출석 01/29 금일 02/01 일경우 1저장)  
		long diff = getdiffmonth(date);
		//마지막 출석일자와 금일간 달 간격 과 마지막 출석일자를 매개로하여 api 조회의 파라미터로 사용될 년도,월을 구해 저장 
		String [] datelist = dates(diff,date);
		
		for(int k = 0; k<=diff; k++) {
			//위에서 얻은 년,월 정보를 각각 저장함
			solyear = datelist[k].substring(0,4);
			solmonth = datelist[k].substring(4,6);
			
			//--------api조회를 통해 JSON 형식의 공휴일 정보 획득 ------------
			BufferedReader br;
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=5MZIgRYBV0v2%2B0UTTdICRDn3FEJqb2KqZQtBAWmTycEjq26ibAuWzqOntUiX%2BX%2BaGlQflK3o%2Bbn6kCqBBrkRag%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(solyear, "UTF-8")); /*연*/
	        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(solmonth, "UTF-8")); /*월*/
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*월*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String jsondata=br.readLine();
	        //----------------------------------------------------
        
        
			try {
				JSONObject jsonObj = (JSONObject) jsonParse.parse(jsondata);
				JSONObject response = (JSONObject)jsonObj.get("response");//response
				JSONObject body = (JSONObject)response.get("body");
				JSONObject items = (JSONObject)body.get("items");//response
				JSONArray array = (JSONArray)items.get("item");
			
				for(int i=0; i < array.size(); i++) { 
					JSONObject hollydayObject = (JSONObject) array.get(i);
					String key = hollydayObject.get("locdate")+"";
					String value= hollydayObject.get("dateName")+"";
					hollyday.put(key, value); } 
				
			}catch (ParseException e) {
				e.printStackTrace();
			}catch (ClassCastException e) {
				JSONObject jsonObj;
				try {
					jsonObj = (JSONObject) jsonParse.parse(jsondata);
					JSONObject response = (JSONObject)jsonObj.get("response");//response
					JSONObject body = (JSONObject)response.get("body");
					JSONObject items = (JSONObject)body.get("items");//response
					JSONObject item = (JSONObject)items.get("item");
					hollyday.put(item.get("locdate")+"", item.get("dateName")+"");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(ClassCastException e1) {
					
				}
			}
		}
		
		return hollyday;
	}	
	
	public long getdiffmonth(Date date) {
		long diffMonth = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
				String toDateStr = format.format(date);
				String fromDateStr = format.format(new Date());
				
				
				try {
					Date toDate = format.parse(toDateStr);
					Date fromDate = format.parse(fromDateStr);
					
					long baseDay = 24 * 60 * 60 * 1000; 	// 일
					long baseMonth = baseDay * 30;		// 월
					
					// from 일자와 to 일자의 시간 차이를 계산한다.
					long calDate = fromDate.getTime() - toDate.getTime();
					
					// from 일자와 to 일자의 시간 차 값을 하루기준으로 나눠 준다
					 diffMonth = calDate / baseMonth;
				
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				return diffMonth;
	}

	public String[] dates(long diff,Date date) {
		String [] arr = new String[((int)diff)+1];
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		SimpleDateFormat mm = new SimpleDateFormat("MM");
		String yye = yy.format(date);
		String mme = mm.format(date);
		
		for(int i = 0; i<=diff; i++) {
			if(i==0) {
				arr[i] = yye+mme;
			}
			else {
			if(Integer.parseInt(mme) < 9) {
				mme = "0"+ (Integer.parseInt(mme) + 1);
			}
			else if(Integer.parseInt(mme) > 11) {
				yye = Integer.parseInt(yye) + 1 + "";
				mme = "01";
			}
			else {
				mme = Integer.parseInt(mme) + 1 + "";
			}
			
			arr[i] = yye+mme;}
		}
		
		return arr;
	}
}
	
