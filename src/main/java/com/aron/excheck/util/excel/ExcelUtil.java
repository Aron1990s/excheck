package com.aron.excheck.util.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description Excel Operation
 * @Author aron
 * @Date 2019/5/16 14:51
 **/
public class ExcelUtil {
    protected final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * read excel format xls
     * @param filepath
     * @param filename
     * @param sheetNum
     * @return
     */
    public static HSSFSheet readHSSFExcel(String filepath, String filename, int sheetNum) {
        HSSFSheet sheet = null;
        try{
            File target = new File(filepath, filename);
            FileInputStream fi = new FileInputStream(target);
            HSSFWorkbook wb = new HSSFWorkbook(fi);
            sheet = wb.getSheetAt(sheetNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sheet;
    }

    /**
     * read excel format xlsx
     * @param filepath
     * @param filename
     * @param sheetNum
     * @return
     */
    public static XSSFSheet readXSSFExcel(String filepath, String filename, int sheetNum) {
        XSSFSheet sheet = null;
        try{
            File target = new File(filepath, filename);
            FileInputStream fi = new FileInputStream(target);
            XSSFWorkbook wb = new XSSFWorkbook(fi);
            sheet = wb.getSheetAt(sheetNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sheet;
    }
}
