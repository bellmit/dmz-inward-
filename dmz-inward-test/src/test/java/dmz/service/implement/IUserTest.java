package dmz.service.implement;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dmz
 * @date 2017/1/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-basic.xml","classpath:Spring-service.xml","classpath:Spring-utils.xml"})
public class IUserTest {

    //@Resource
    //private IUserService userService;
    //
    //@Test
    //public void addUserInfoTest() {
    //    User user = new User();
    //    user.setUserNo("444");
    //    user.setUpdateTime(new Date());
    //    user.setCreateTime(new Date());
    //    userService.addUserInfo(user);
    //}

}
