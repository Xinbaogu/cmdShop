import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;


public class ReadCustomerExcel {
    public Customer[] readExcel(InputStream in) {
        Customer customers[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            customers = new Customer[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Customer customer = new Customer();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        customer.setUsername(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        customer.setPassword(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        customer.setAddress(this.getValue(cell));//给address属性赋值
                    } else if (k == 3) {
                        customer.setPhone(this.getValue(cell));//给phone属性赋值
                    }
                }
                customers[j-1] = customer;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellType();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                DecimalFormat df = new DecimalFormat("#");
                value=df.format(cell.getNumericCellValue());
                //System.out.println("处理后的结果"+value);
                break;

                //字符串截取
                /*value = cell.getNumericCellValue() + "";
                int index=value.lastIndexOf(".");
                value=value.substring(0,index);*/
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
