package dmztest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by dmz on 2016/10/25.
 */
public class ReplaceStr {
    public static void main(String[] args) {

        Field f = null; //Internal reference
        try {
            f =Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            String passwd = new String("ewkewk");
            String fake = passwd.replaceAll(".", "?");
//            char[] fakeChars = fake.toCharArray();
//            char[] passwdChars = passwd.toCharArray();
//            System.arraycopy(fakeChars, 0, passwdChars, 0, fakeChars.length);
//            unsafe.copyMemory(fake,0l,null,passwd.hashCode(),passwd.length());
            System.out.println(fake);
            System.out.println(passwd);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



    }
}
