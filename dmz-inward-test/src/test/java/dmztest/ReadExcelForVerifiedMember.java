package dmztest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by dmz on 2016/5/16.
 */
public class ReadExcelForVerifiedMember {
    private static String testTemp="SELECT * FROM t_member WHERE verified_mobile='dmz';\n" +
            "SELECT * FROM t_client_certification WHERE member_no=(SELECT member_no FROM t_member WHERE verified_mobile='dmz');\n\n";


    private static String temp="set @verified_mobile = 'dmzPhone';\n" +
            "set @idno = 'dmzCard';\n" +
            "set @name = 'dmzName';\n" +
            "select * from t_member where verified_mobile = @verified_mobile;\n" +
            "select member_no into @member_no from t_member where verified_mobile = @verified_mobile;\n" +
            "update t_client_certification set idno = @idno, idno_masked = @idno, name = @name, status = 1 where member_no = @member_no;\n" +
            "update t_member set certificated_level = 2, certificated_type = 6 where member_no = @member_no;\n" +
            "select * from t_client_certification where member_no = @member_no;\n" +
            "select * from t_member where member_no = @member_no;\n\n";

    public static void main(String[] args) {
        String path="C:\\Users\\dmz\\Desktop\\HD会员脚本\\实名认证\\20160517.xlsx";
        String target="C:\\Users\\dmz\\Desktop\\HD会员脚本\\实名认证\\20160517.sql";
        String testSql="C:\\Users\\dmz\\Desktop\\HD会员脚本\\实名认证\\test20160517.sql";
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
//        readxls(sourcePath, targetPath, nameIndex, phoneIndex, cardIndex, typeIndex, sheetIndex);

        readxls(path, target, 1, 2, 3, 4, 0);

    }
    private static void SQLCreator(BufferedWriter bufferedWriter, String sql) throws IOException {
        bufferedWriter.write(sql);
    }
    private static void readxls(String source, String target, int nameIndex, int phoneIndex, int cardNoIndex, int TypeIndex, int sheets) {
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

                Cell phone = sheet.getRow(j).getCell(phoneIndex);
                String phoneNo = getCellComment(phone);

                Cell card = sheet.getRow(j).getCell(cardNoIndex);
                String cardNo = getCellComment(card);

                Cell type = sheet.getRow(j).getCell(TypeIndex);
                String typeFlag = getCellComment(type);


                System.out.println("NAME:" + user_name + " PHONE:" + phoneNo + " CARD:" + cardNo + " Type:" + typeFlag);

                String sql=temp.replaceAll("dmzPhone",phoneNo).replaceAll("dmzCard",cardNo).replaceAll("dmzName",user_name);
                SQLCreator(writer, sql);

//                String testSql=testTemp.replaceAll("dmz",phoneNo);
//                SQLCreator(writer,testSql);
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
