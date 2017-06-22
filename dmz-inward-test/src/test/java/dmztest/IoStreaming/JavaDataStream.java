package dmztest.IoStreaming;


import java.io.*;

/**
 * Created by dmz on 2016/3/10.
 */
public class JavaDataStream {
    public static void main(String[] args){

        String fileName = "C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/two.txt" ;
//        DataStreamOut(fileName);
        DataStreamIn(fileName);

    }

    private static void DataStreamIn(String fileName){
        try (InputStream inputStream = new FileInputStream(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             DataInputStream dataInputStream = new DataInputStream(bufferedInputStream)){
            String str;
            Double dou ;
            int in ;

            while( true){
                str = dataInputStream.readUTF() ;
                dou = dataInputStream.readDouble();
                in = dataInputStream.readInt();
                System.out.format( "String is %s , Double is %.2f , Int is %d %n" ,str, dou,in) ;
            }

        } catch (EOFException e) {
            /**
             * 通过EOFFException异常捕获,判断文件是否读完
             */
            System. out.println("down." );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DataStreamOut(String fileName){

        try (OutputStream outputStream = new FileOutputStream(fileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             DataOutputStream out = new DataOutputStream(bufferedOutputStream)){


            String[] str= {"dmz-one", "L O V E","明天的事情。" };
            double[] prices = { 192.9,23.9 ,35.00} ;
            int[] units= { 198,32 ,19} ;

            for ( int i=0 ;i<str.length ;i++){
                out.writeUTF(str[i]);
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
            }

        }catch (Exception e){

        }
    }
}

