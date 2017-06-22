package dmztest.zookeeper.zkclient;

import java.io.Serializable;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4895097020823401901L;

    public User() {

    }
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
