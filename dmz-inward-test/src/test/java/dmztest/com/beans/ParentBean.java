package dmztest.com.beans;

/**
 * Created by dmz on 2016/2/19.
 */
public class ParentBean implements IParentBean {
    private ISon dmz;

    public void setDmz(ISon dmz) {
        this.dmz = dmz;
    }

    public void sayToDmz() {
        System.out.println("Hello,World...!");
        dmz.cao();
    }
}
