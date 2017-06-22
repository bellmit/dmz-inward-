package dmztest;

/**
 * Created by dmz on 2016/8/17.
 */
class AccessControl {
    private String name;
    public AccessControl() {
    }
    public AccessControl(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAccessControl(AccessControl accessControl) {
        System.out.println("name:" + accessControl.name);
    }
}
public class AccessControlTest {
    public static void main(String args[]) {
        AccessControl access1 = new AccessControl();
        AccessControl access2 = new AccessControl("DMZ");
        access1.setAccessControl(access2);
    }
}
