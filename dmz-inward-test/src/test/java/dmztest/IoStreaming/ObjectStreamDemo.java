package dmztest.IoStreaming;

import java.io.*;

/**
 * @author dmz
 * @date 2017/2/28
 */
class DMZ implements Serializable {

    private static final long serialVersionUID = 8889034304327302718L ;

    private String content;

    private transient int age =100000;

    private transient boolean flag ;

    private transient String te="name" ;

    public String getTe() {
        return te;
    }

    public boolean isFlag() {
        return flag;
    }

    public DMZ(String content,int age ,boolean flag){
        this .content = content ;
        this. age = age;
        this. flag = flag;
    }

    public int getAge() {
        return age;
    }

    public void setContent(String content) {
        this .content = content ;
    }

    public String getContent() {
        return content;
    }
}

public class ObjectStreamDemo implements Serializable {


    public static void main(String[] args) {

        String fileName="C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/object.txt" ;
//        ObjectStreamOut(fileName);
        ObjectStreamIn(fileName);

    }

    private static void ObjectStreamIn(String fileName){
        try (InputStream inputStream = new FileInputStream(fileName) ;
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream) ;
             ObjectInputStream objectInput = new ObjectInputStream(bufferedInputStream) ){

            while (true){
                DMZ read = (DMZ)objectInput.readObject() ;
                System.out.println( "Name: "+read.getContent()+" age:" +read.getAge()+" Flag:"+read.isFlag()+ " Te:"+read.getTe());
            }

        }catch (EOFException e){
            System.out.println( "down.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ObjectStreamOut(String fileName){
        try (OutputStream outputStream = new FileOutputStream(fileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)){

            objectOutputStream.writeObject(new DMZ( "dmz",18 ,true));
            objectOutputStream.writeObject( new DMZ("ym" ,20,false ));

        } catch (Exception e){

        }
    }
}

