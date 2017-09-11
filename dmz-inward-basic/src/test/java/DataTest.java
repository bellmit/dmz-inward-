import com.dmz.basic.document.UserDocument;
import com.dmz.basic.idao.ILoginDao;
import com.dmz.basic.idao.IUserDocumentDao;
import com.dmz.basic.model.Login;
import com.dmz.service.constant.basic.LoginConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author dmz
 * @date 2017/8/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class})
public class DataTest {

    @Resource
    private IUserDocumentDao userDocumentDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Resource
    private ILoginDao loginDao;

    @Test
    public void testSaveUserDocument() {
        UserDocument user = new UserDocument();
        user.setName("DMZ");
        userDocumentDao.saveUser(user);
        System.out.println();

    }


    @Test
    public void testAdd() {
        Login login = new Login();
        login.setHasPasswd(LoginConstant.HAS_HPASSWORD.get(LoginConstant.YES));
        login.setIsDelete(false);
        login.setLoginName("dmz");
        login.setPasswd("0307");
        login.setUserNo("UserNo");
        login.setPlatform(LoginConstant.PLATFORM.get(LoginConstant.WEB));
        login.setPwdStrength(LoginConstant.PASS_STRENGTH.get(LoginConstant.WEAK));
        login.setStatus(LoginConstant.STATUS.get(LoginConstant.UNLOCKED));
        login.setCreateTime(new Date());
        login.setUpdateTime(new Date());
        loginDao.insertLogin(login);

    }


    @Test
    public void testRedis() {
        redisTemplate.opsForSet().add("dmz_inward:test:", "Hello Redis");
        //redisTemplate.expire("dmz_inward:test", 10, TimeUnit.SECONDS);
    }

}
