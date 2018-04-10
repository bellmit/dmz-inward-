package dmztest.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dmz
 * @date 2017/2/6
 * Craig, Landin, and Hagersten
 */
public class CLHLock {

    private static class Node {
        private volatile boolean locked;
    }

    private ThreadLocal<Node> pre = new ThreadLocal<>();
    private ThreadLocal<Node> node;
    private AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public CLHLock() {
        this.node = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return new Node();
            }
        };
    }

    public void lock() throws InterruptedException {
        final Node node = this.node.get();
        node.locked = true;
        Node pre = tail.getAndSet(node);
//        this.pre.set(pre);
        while (pre.locked) {
            System.out.println("waiting...");
        }
    }

    public void unLock() {
        final Node node = this.node.get();
        node.locked = false;
//        this.node.set(this.pre.get());
    }
}
