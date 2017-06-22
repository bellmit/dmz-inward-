package dmztest.ThreadExamples;

public class Reader extends Thread {
    private IBuffer buff;
    public Reader(IBuffer buff) {
        this.buff = buff;
    }
    @Override
    public void run() {
        try {
            buff.read();
        } catch (InterruptedException e) {
            System.out.println("我不读了");
        }
        System.out.println("读结束");
    }
}
