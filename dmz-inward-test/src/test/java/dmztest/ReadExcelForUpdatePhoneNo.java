package dmztest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by dmz on 2016/5/16.
 */
public class ReadExcelForUpdatePhoneNo {
    private static String notRegister = "set @mobile_no_old = 'notRegisterOldPhone';\n" +
            "set @mobile_no_new = 'notRegisterNewPhone';\n" +
            "\n" +
            "update t_member set verified_mobile = @mobile_no_new where verified_mobile = @mobile_no_old;\n" +
            "update t_login_alias set login_alias = @mobile_no_new where login_alias = @mobile_no_old;\n\n";


    private static String registed = "set @mobile_no_old = 'registedOldPhone';\n" +
            "set @mobile_no_new = 'registedNewPhone';\n" +
            "\n" +
            "select member_no into @member_no_old from t_member where verified_mobile = @mobile_no_old;\n" +
            "update t_member set verified_mobile = '11111111111' where member_no = @member_no_old;\n" +
            "update t_login_alias set login_alias = '11111111111' where member_no = @member_no_old;\n" +
            "\n" +
            "select member_no into @member_no_new from t_member where verified_mobile = @mobile_no_new;\n" +
            "update t_member set verified_mobile = '00000000000' where member_no = @member_no_new;\n" +
            "update t_login_alias set login_alias = '00000000000' where member_no = @member_no_new;\n" +
            "\n" +
            "update t_member set verified_mobile = @mobile_no_new where member_no = @member_no_old;\n" +
            "update t_login_alias set login_alias = @mobile_no_new where member_no = @member_no_old;\n" +
            "\n" +
            "update t_member set verified_mobile = @mobile_no_old where member_no = @member_no_new;\n" +
            "update t_login_alias set login_alias = @mobile_no_old where member_no = @member_no_new;\n\n";


    public static void main(String[] args) {
        String path="C:\\Users\\dmz\\Desktop\\HD会员脚本\\修复数据\\更改手机号码\\20160525.xlsx";
        String target="C:\\Users\\dmz\\Desktop\\HD会员脚本\\修复数据\\更改手机号码\\20160525.sql";
//        if (args.length != 7) {
//            System.out.println("CMD sourcePath  targetPath  nameIndex oldPhone newPhone isRegister sheetIndex");
//        }
//        String sourcePath = args[0];
//        String targetPath = args[1];
//        int nameIndex = Integer.valueOf(args[2]).intValue();
//        int oldPhone = Integer.valueOf(args[3]).intValue();
//        int newPhone = Integer.valueOf(args[4]).intValue();
//        int isRegister = Integer.valueOf(args[5]).intValue();
//        int sheetIndex = Integer.valueOf(args[6]).intValue();
//        System.out.println("Source:" + sourcePath + "\n" + "Target:" + targetPath);
//        readxls(sourcePath, targetPath, nameIndex, oldPhone, newPhone, isRegister, sheetIndex);

        readxls(path, target, 1, 2, 3, 4, 0);

    }
    private static void SQLCreator(BufferedWriter bufferedWriter, String sql) throws IOException {
        bufferedWriter.write(sql);
    }
    private static void readxls(String source, String target, int nameIndex, int oldPhone, int newPhone, int isRegister, int sheets) {
        try (FileInputStream fi = new FileInputStream(source);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, true), "UTF-8"));) {
            writer.write("use eif_member;\n\n");

            Workbook wb = new XSSFWorkbook(fi);
            Sheet sheet = wb.getSheetAt(sheets);
            System.out.println("sheet:" + wb.getSheetName(sheets));
            int total = 0;
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                total++;
                Cell name = sheet.getRow(j).getCell(nameIndex);
                String user_name = getCellComment(name);

                Cell phoneOld = sheet.getRow(j).getCell(oldPhone);
                String UoldPhone = getCellComment(phoneOld);

                Cell phoneNew = sheet.getRow(j).getCell(newPhone);
                String UnewPhone = getCellComment(phoneNew);

                Cell register = sheet.getRow(j).getCell(isRegister);
                String registerFlag = getCellComment(register);


                System.out.println("NAME:" + user_name + " OLDPHONE:" + UoldPhone + " NEWPHONE:" + UnewPhone + " isRegister:" + registerFlag);

                String sql =null;
                if ("未注册".equals(registerFlag)){
                    sql = notRegister.replaceAll("notRegisterOldPhone", UoldPhone).replaceAll("notRegisterNewPhone", UnewPhone);
                } else {
//                    continue;
                    sql = registed.replaceAll("registedOldPhone", UoldPhone).replaceAll("registedNewPhone", UnewPhone);
                }
                SQLCreator(writer, sql);
            }
            System.out.println("Total Row:" + total);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellComment(Cell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case 2:
                value = cell.getCellFormula().trim();
                break;
            case 0:
                value = String.format("%.0f", new Object[]{Double.valueOf(cell.getNumericCellValue())}).trim();
                break;
            case 1:
                value = cell.getStringCellValue().trim();
                break;
        }
        return value;
    }
}
