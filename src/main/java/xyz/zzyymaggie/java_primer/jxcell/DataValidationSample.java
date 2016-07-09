package xyz.zzyymaggie.java_primer.jxcell;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import xyz.zzyymaggie.java_primer.common.Constants;

import com.jxcell.CellFormat;
import com.jxcell.DataValidation;
import com.jxcell.View;

/**
 * use jxcell jar to generate excel with dropdown list
 * @author sozhang
 *
 */
public class DataValidationSample
{
    private String excelPath = Constants.TEMP_PATH + "datavalidation.xlsx";
    
    private List<String> mocklist(String prefix){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add(prefix + (i + 1));
        }
        return list;
    }
    
    private String joinItemList(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++){
            if(i < (list.size() - 1)){
                sb.append(list.get(i) + ",");
            }else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
    
    private String getValidationString(List<String> itemList) {
        String options = itemList.isEmpty()?"":itemList.get(0);
        for(int i=1; i<itemList.size(); i++) {
            String item = itemList.get(i);
            if(options.length() + item.length() > 256){
                break;
            }
            
            options += "\0"+item; 
        }
        return "\"" + options + "\"";
    }
    
    private void generateDropdownByString(View m_view) 
        throws Exception
    {
        List<String> itemList = mocklist("input");
        String source = getValidationString(itemList);
        m_view.setText(0, 0, joinItemList(itemList));
        DataValidation dataValidation = m_view.CreateDataValidation();
        dataValidation.setType(DataValidation.eUser);
        dataValidation.setFormula1(source);
        dataValidation.setErrorBoxText("value should be in " + joinItemList(itemList));
        m_view.setSelection("A3:A5");
        m_view.setDataValidation(dataValidation);
    }
    
    private void generateDropdownBySelection(View m_view) 
        throws Exception
    {
        //render data
        List<String> itemList = mocklist("apple");
        for(int i=0;i<itemList.size();i++) {
            m_view.setText(1, i, itemList.get(i));
        }
        String startStr = "$" + ExcelNumberUtil.excelColIndexToStr(1);
        String endStr = "$" + ExcelNumberUtil.excelColIndexToStr(itemList.size());
        //select data as validation selection
        DataValidation dataValidation = m_view.CreateDataValidation();
        dataValidation.setType(DataValidation.eUser);
        dataValidation.setFormula1(startStr + "$2" +":" + endStr + "$2");
        dataValidation.setErrorBoxText("value should be in " + joinItemList(itemList));
        m_view.setSelection("B3:D5");
        m_view.setDataValidation(dataValidation);
        
        
    }
    
    private void highlightDropdownCell(View m_view)
        throws Exception
    {
        // highlight the dropdown by string
        CellFormat cellformat = m_view.getCellFormat(2,0,4,0);
        cellformat.setPattern(CellFormat.PatternSolid);
        cellformat.setPatternFG(Color.blue);
        m_view.setCellFormat(cellformat, 2,0,4,0);
        
        // highlight the dropdown by selection
        cellformat = m_view.getCellFormat(2,1,4,3);
        cellformat.setPattern(CellFormat.PatternSolid);
        cellformat.setPatternFG(Color.green);
        m_view.setCellFormat(cellformat, 2,1,4,3);
    }
    
    public void create(){
        View m_view = new View();
        try
        {
            generateDropdownByString(m_view);
            generateDropdownBySelection(m_view);
            highlightDropdownCell(m_view);
            m_view.writeXLSX(excelPath);
            com.jxcell.designer.Designer.newDesigner(m_view);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        DataValidationSample sample = new DataValidationSample();
        sample.create();
    }
}