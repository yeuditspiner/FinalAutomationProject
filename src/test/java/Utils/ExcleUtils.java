package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataForExcle.LoginData;
import DataForExcle.RegistrationData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcleUtils {

    //for Registration
    public List<RegistrationData> readRegistrationExcelFile(String fileName) {
        int i = 0;
        List<RegistrationData> lst = new ArrayList<RegistrationData>();

        FileInputStream f = null;
        XSSFWorkbook workbook = null;
        try {
            f = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(4);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip the headers row
        rowIterator.next();

        while (rowIterator.hasNext()) {

            String str = " ";
            RegistrationData newRegistrationData = new RegistrationData();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell cell = null;

            //firstName
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setFirstName(str);

            //lastname
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setLastName(str);

            //dateOfBirth
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setDateOfBirth(str);

            //gender
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setGender(str);

            //telephon
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setContactTelephone(str);

            //email
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setEmail(str);

            //password
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setPassword(str);

            //confirmPassword
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newRegistrationData.setConfirmPassword(str);

            //Error
            cell = cellIterator.next();
            newRegistrationData.setError(cell.getStringCellValue());
            lst.add(newRegistrationData);
        }

        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    //  for Login:

    public List<LoginData> readLoginExcelFile(String fileName) {
        int i = 0;
        List<LoginData> lst = new ArrayList<LoginData>();

        FileInputStream f = null;
        XSSFWorkbook workbook = null;
        try {
            f = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(1);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip the headers row
        rowIterator.next();

        while (rowIterator.hasNext()) {

            String str = " ";
            LoginData newLoginData = new LoginData();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell cell = null;
            //email
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newLoginData.setEmail(str);

            //password
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newLoginData.setPassword(str);

            //Error
            cell = cellIterator.next();
            newLoginData.setError(cell.getStringCellValue());
            lst.add(newLoginData);
        }

        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<String> readExcelSearchFile(String fileName) {
        int i = 0;
        List<String> lst = new ArrayList<String>();


        FileInputStream f = null;
        XSSFWorkbook workbook = null;
        try {
            f = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(2);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip the headers row
        rowIterator.next();

        while (rowIterator.hasNext()) {

            String str = " ";
            String newstrToSearch;
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell cell = null;
            //title
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newstrToSearch = (str);

            lst.add(newstrToSearch);
        }

        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<String> readExcelShppingCartFile(String fileName) {
        int i = 0;
        List<String> lst = new ArrayList<String>();


        FileInputStream f = null;
        XSSFWorkbook workbook = null;
        try {
            f = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(3);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip the headers row
        rowIterator.next();

        while (rowIterator.hasNext()) {

            String str = " ";
            String newstrToSearch;
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell cell = null;
            //ItemToBuy/Remove
            cell = cellIterator.next();
            str = getRightStr(cell.getStringCellValue());
            newstrToSearch = (str);

            lst.add(newstrToSearch);
        }

        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public String getRightStr(String str) {
        int size = str.length();
        if (str.equals("null"))
            return "";
        else {
            if (str.contains("/")) {

                str = str.substring(1, size - 1);

            }
            if (str.contains("-")) {
                int index = str.indexOf("-");
                str = charRemoveAt(str, index);

            }
        }
        return str;
    }

    public static String charRemoveAt(String str, int i) {
        return str.substring(0, i) + str.substring(i + 1);
    }

}
