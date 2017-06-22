package dmztest;

import java.io.Closeable;

/**
 * Created by dmz on 2016/8/18.
 */
class DirtyResource implements Closeable {

    public void accessResource() {
        throw new RuntimeException("Access Resource Failure...");
    }

    @Override
    public void close() throws NullPointerException {
        throw new NullPointerException("Null exceptions...");
    }
}

public class ExceptionExample {

    static void accessMethodWithResource() {

        try (DirtyResource dirtyResource = new DirtyResource()) {
            dirtyResource.accessResource();
        }
//        catch (Exception ex) {
//            err.println("Try Exception:"+ex.toString());
//            final Throwable[] suppressedExceptions = ex.getSuppressed();
//            final int numSuppressed = suppressedExceptions.length;
//            if (numSuppressed > 0) {
//                err.println("tThere are " + numSuppressed + " suppressed exceptions:");
//                for (final Throwable exception : suppressedExceptions) {
//                    err.println("tt" + exception.toString());
//                }
//            }
//        }

    }

    static void accessMethod() throws Throwable {
        Throwable th = null;
        DirtyResource dirtyResource = new DirtyResource();

        try {
            dirtyResource.accessResource();
        } catch (Exception e) {
//            err.println(e.getMessage());
            th = e;
        } finally {

            try {
                dirtyResource.close();
            } catch (Exception e) {
                if (th != null) {
//                    e.addSuppressed(th);
//                    throw e;
                    th.addSuppressed(e);
                    throw th;
                }
            }
        }
    }

    static public void main(String[] args) {

        String s = null;
        System.out.println(String.valueOf(s));
        System.out.println("----------------------------------");
        System.out.println(s.toString());
//        try {
//            accessMethod();
            accessMethodWithResource();
//        }
//        catch (Exception ex) {
//            err.println("Encountered Exception:" + ex.toString());
//            final Throwable[] suppressedExceptions = ex.getSuppressed();
//            final int numSuppressed = suppressedExceptions.length;
//            if (numSuppressed > 0) {
//                err.println("tThere are " + numSuppressed + " suppressed exceptions:");
//                for (final Throwable exception : suppressedExceptions) {
//                    err.println("tt" + exception.toString());
//                }
//            }
//        }
    }
}
