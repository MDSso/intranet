package com.mds.pjt.config;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
 
public class MakeExcel {
    public void download(HttpServletRequest request, HttpServletResponse response,
                    Map<String, Object> bean, String fileName, String templateFile)
                    throws ParsePropertyException, InvalidFormatException {
 
        // 템플릿 엑셀파일의 위치.
        String tempPath = request.getSession().getServletContext().getRealPath("/resources/excel");
        
        try {
        	//엑셀 템플릿 지정
            InputStream is = new BufferedInputStream(new FileInputStream(tempPath + "\\" + templateFile));
            XLSTransformer xls = new XLSTransformer();
            
            //엑셀내부에서 사용될데이터 값과 엑셀 템플릿을 이용하여 workbook 생성
            Workbook workbook = xls.transformXLS(is, bean);
            
            
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
            
            OutputStream os = response.getOutputStream();
            
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


