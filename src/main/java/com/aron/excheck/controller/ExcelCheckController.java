package com.aron.excheck.controller;

import com.aron.excheck.entity.template.TemplateInfo;
import com.aron.excheck.entity.template.TemplatePrinciple;
import com.aron.excheck.service.imp.templateImp.TemplateInfoServiceImp;
import com.aron.excheck.service.imp.templateImp.TemplatePrincipleServiceImp;
import com.aron.excheck.util.excel.ExcelUtil;
import com.aron.excheck.util.file.FileUtil;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelCheck
 * @Description Excel Check Controller
 * @Author aron
 * @Date 2019/5/9 14:48
 **/
@RestController
@RequestMapping("excelValidateCtl")
public class ExcelCheckController {
    protected final static Logger logger = LoggerFactory.getLogger(ExcelCheckController.class);

    @Autowired
    private TemplateInfoServiceImp templateInfoService;

    @Autowired
    private TemplatePrincipleServiceImp templatePrincipleService;

    @Value("${excelUploadPath}")
    private String excelUploadPath;

    /**
     * excelFormatCheck
     * @param file
     * @return
     */
    @PostMapping("excelFormatCheck")
    public Object excelFormatCheck(@RequestParam(value = "excel", required = false) MultipartFile file, @RequestParam(value = "param", required = false) MultipartFile param) {
        Map<String, Object> result = new HashMap<>();
        Map<String, List<String>> validateResult = new HashMap();
        List<List<String>> validateResultLists = new ArrayList<>();
        try {
            if (null != file && !file.isEmpty()) {
                Gson gson = new Gson();
                Map<String, String> mapParams = new HashMap<String, String>();
                mapParams = gson.fromJson(param.getOriginalFilename(), mapParams.getClass());//转换传递的参数
                String templateId = mapParams.get("templateId");//获取模板id
                TemplateInfo templateInfo = templateInfoService.findByTemplateId(templateId);//获取模板信息
                TemplatePrinciple templatePrinciple = new TemplatePrinciple();
                templatePrinciple.setTemplate_id(templateId);
                List<TemplatePrinciple> templatePrinciples = templatePrincipleService.findRecordsById(templatePrinciple);//获取模板校验规则列表
                String fileFinalName = FileUtil.fileUpload(file, excelUploadPath, templateInfo.getRemark());//存储校验文件
                Sheet sheet = null;
                String sheetFormat = fileFinalName.substring(fileFinalName.lastIndexOf(".")+1);
                if(sheetFormat.equals("xls")){
                    sheet = ExcelUtil.readHSSFExcel(excelUploadPath, fileFinalName, 0);//读取excel
                }else if (sheetFormat.equals("xlsx")){
                    sheet = ExcelUtil.readXSSFExcel(excelUploadPath, fileFinalName, 0);//读取excel
                }
                int rowNum = sheet.getLastRowNum() + 1;
                SimpleDateFormat dateFormat = null;
                //初始化校验结果
                for (TemplatePrinciple subTempPrin : templatePrinciples) {
                    List<String> reList = new ArrayList<>(rowNum * 2);//确定arrayList长度避免扩容带来的性能损耗
                    validateResult.put(subTempPrin.getColumn_number(), reList);//存储至最终结果list中
                    validateResultLists.add(reList);
                    if (subTempPrin.getColumn_type().equals("2")) {//时间格式校验
                        dateFormat = new SimpleDateFormat(subTempPrin.getColumn_format());
                    }
                    for (int i = 1; i < rowNum; i++) {
                        Row row = sheet.getRow(i); //获取该行数据
                        Cell subValidateCell = row.getCell(Integer.parseInt(subTempPrin.getColumn_number()));//获取需要校验的单元格
                        try {
                            validateCellDateType(subValidateCell, dateFormat);
                        } catch (Exception e) {
                            reList.add("第" + i + "行第" + (Integer.parseInt(subTempPrin.getColumn_number())+1) + "列格式不正确");
                        }
                    }
                }
                result.put("validateResult", validateResultLists);
                result.put("code", 1);
                result.put("msg", "excel校验完成");
            } else {
                result.put("code", -2);
                result.put("msg", "未上传excel");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", "excel校验接口异常");
        }
        return result;
    }

    //校验时间型单元格
    private static String validateCellDateType(Cell cell, SimpleDateFormat dateFormat) throws Exception {
        String result = "";
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = dateFormat.format(cell.getDateCellValue());
                } else {
                    result = dateFormat.format(dateFormat.parse((cell + "").trim()));
                }
                break;
            case STRING:
                result = dateFormat.format(dateFormat.parse((cell + "").trim()));
                break;
            case FORMULA:
                result = "error";
                break;
            case BLANK:
                result = "error";
                break;
            case BOOLEAN:
                result = "error";
                break;
            case ERROR:
                result = "error";
                break;
            default:
                result = "error";
                break;
        }
        if (result.equals("error")) throw new Exception("非时间格式");
        return result;
    }

    public static void main(String[] args) {
        String sss = "2019-01-01 00:11:11";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println(simpleDateFormat.format(simpleDateFormat.parse(sss)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
