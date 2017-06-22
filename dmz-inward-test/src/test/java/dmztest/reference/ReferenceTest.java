package dmztest.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author dmz
 * @date 2017/2/21
 */
class Reference {
    private String name;

    public String getName() {
        return name;
    }

    public Reference(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("I am finalized.");
    }
}

public class ReferenceTest {

    private static ReferenceQueue<dmztest.reference.Reference> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {
//        StrongReference();
//        SoftReference();
//        WeakReference();
//        PhantomReference();
        WeakCollection();
    }

    private static void StrongReference() {
        dmztest.reference.Reference reference = new dmztest.reference.Reference("D.M.Z");
        System.out.println(reference.getName());
        reference = null;
        System.gc();
    }

    //  内存不足時候 會給回收
    private static void SoftReference() {
        dmztest.reference.Reference reference = new dmztest.reference.Reference("D.M.Z");
        SoftReference<dmztest.reference.Reference> softReference = new SoftReference<>(reference, referenceQueue);
        System.out.println("Reference:" + reference.getName());
        System.out.println("SoftReference Before:" + softReference.get().getName());
        reference = null;
        System.out.println("SoftReference After：" + softReference.get().getName());
        System.gc();
        SoftReference softTmp = null;
        while ((softTmp = (SoftReference) referenceQueue.poll()) != null) {
            System.out.println(softTmp);
        }
        System.out.println("isEnqueue:" + softReference.isEnqueued());

    }

    private static void WeakReference() throws InterruptedException {
        dmztest.reference.Reference reference = new dmztest.reference.Reference("D.M.Z");
        WeakReference<dmztest.reference.Reference> weakReference = new WeakReference<>(reference, referenceQueue);
        reference = null;
//        Reference referenceGet = weakReference.get(); //再次獲得強引用
        System.gc();
        Thread.sleep(1000);
        System.out.println("isEnqueue:" + weakReference.isEnqueued());
        WeakReference referenceT = null;
        while ((referenceT = (WeakReference) referenceQueue.poll()) != null) {
            System.out.println("Waiting finalize.");
        }
        System.out.println("isEnqueue:" + weakReference.isEnqueued());
        System.out.println("Gc After:" + referenceT);
    }

    private static void PhantomReference() throws InterruptedException {
        dmztest.reference.Reference reference = new dmztest.reference.Reference("D.M.Z");
        PhantomReference<dmztest.reference.Reference> phantomReference = new PhantomReference<>(reference, referenceQueue);
        System.out.println("Gc Before:" + referenceQueue.poll());
        System.out.println("isEnqueue:" + phantomReference.isEnqueued());
        reference = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("Gc After:" + referenceQueue.poll());
        System.out.println("isEnqueue:" + phantomReference.isEnqueued());

    }

    private static void WeakCollection() throws InterruptedException {

        dmztest.reference.Reference reference = new dmztest.reference.Reference("D.M.Z");
        WeakReference<dmztest.reference.Reference> wrc = new WeakReference(reference);
        int i = 0;
        while (true) {
            if (wrc.get() != null) {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            } else {
                System.out.println("WeakReferenceCar's Car has bean collected");
//                Thread.sleep(10000);
                break;
            }
        }
    }
}
