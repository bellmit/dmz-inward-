package dmztest;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by dmz on 2016/7/13.
 */

public class CopyBytes {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(System.getProperty("os.name"));
//        CopyBytes("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\infile.txt","C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
//        CopyCharacter("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\infile.txt","C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
//        CopyLineCharacter("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\infile.txt","C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
//        ScannerString("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\infile.txt");
//        ScannerNum("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\infile.txt");
//        InputStreamReader in = new InputStreamReader(System.in);
//        System.out.println((char)in.read());
//        Password();
//        DataOutStream("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
//        DataInputStream("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
        WriteObjectStream("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
        ReadObjectStream("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");

    }
    private static void ReadObjectStream(String source) {
        try( InputStream in = new FileInputStream(source);
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)){
            MapObject mo1 = (MapObject) objectInputStream.readObject();
            MapObject mo2 = (MapObject) objectInputStream.readObject();
            System.out.println(mo1.hashCode());
            System.out.println(mo2.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void WriteObjectStream(String target) {
        MapObject o = new MapObject();
//        Object o2 = new Object();
//        System.out.println(o2.hashCode());
        System.out.println(o.hashCode());
        try (OutputStream out = new FileOutputStream(target);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
            objectOutputStream.writeObject(o);
            objectOutputStream.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void DataInputStream(String target) {
        double price;
        int unit;
        String desc;
        try (InputStream in = new FileInputStream(target);
             BufferedInputStream bufferInputStream = new BufferedInputStream(in);
             DataInputStream dataInputStream = new DataInputStream(bufferInputStream)) {
            while (true) {
                price = dataInputStream.readDouble();
                unit = dataInputStream.readInt();
                desc = dataInputStream.readUTF();
                System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                        unit, desc, price);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("DONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void DataOutStream(String target) {
         final double[] prices = { 19.99, 9.99999999999999999, 15.99, 3.99, 4.99 };
         final int[] units = { 12, 8, 13, 29, 50 };
         final String[] descs = {
                "Java T-shirt",
                "Java Mug",
                "Duke Juggling Dolls",
                "Java Pin",
                "Java Key Chain"
        };
        try(OutputStream out = new FileOutputStream(target);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream)) {
            for (int i = 0; i < descs.length; i++) {
                dataOutputStream.writeDouble(prices[i]);
                dataOutputStream.writeInt(units[i]);
                dataOutputStream.writeUTF(descs[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void Password() {
        Console c = System.console();
        if (c == null) {
            System.err.println("no console.");
            System.exit(1);
        }
        String login = c.readLine("Enter your login:");
        char[] password = c.readPassword("Enter your password");
        System.out.println(login);
        System.out.println(password);
    }
    private static void ScannerNum(String source) {
        try (Reader reader = new FileReader(source);
             BufferedReader bufferedReader = new BufferedReader(reader);
             Scanner sc = new Scanner(bufferedReader)) {
                   sc.useLocale(Locale.US);
            double sum = 0;
            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
//                    System.out.println("Double-"+sc.nextDouble());
                    sum +=sc.nextDouble();
                } else {
                    System.out.println("String-"+sc.next());
                }
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ScannerString(String source) {
        try (Reader reader = new FileReader(source);
             BufferedReader bufferedReader = new BufferedReader(reader);
             Scanner sc = new Scanner(bufferedReader)) {
//            sc.useDelimiter(",\\s*");
//            默认使用空白分隔(blank, tab, line terminator)
            while (sc.hasNext()) {
                System.out.println(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void CopyLineCharacter(String source, String target) {
        try (Reader reader = new FileReader(source); BufferedReader bufferedReader=new BufferedReader(reader);
             Writer writer = new FileWriter(target); BufferedWriter bufferedWriter=new BufferedWriter(writer); PrintWriter print = new PrintWriter(writer)) {
             String line;
            while ((line=bufferedReader.readLine()) != null) {
                print.println(line);
                bufferedWriter.write(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void CopyCharacter(String source, String target) {
        try (Reader reader = new FileReader(source);
             Writer writer = new FileWriter(target)) {
             int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void CopyBytes(String source,String target) {
        try(InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target)){
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
