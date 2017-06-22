package dmz.basic.mapperTest;

import com.dmz.basic.mapper.LoginMapper;
import com.dmz.basic.model.Login;
import com.dmz.service.constant.basic.LoginConstant;
import com.dmz.service.utils.GenerateUUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dmz on 2016/3/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-basic.xml","classpath:Spring-utils.xml"})
public class LoginMapperTest extends TestSuites {

    @Autowired
    private LoginMapper loginMapper;

    @Test
    public void testLoginMapper() {
        Login login = new Login();
        login.setHasPasswd(LoginConstant.HAS_HPASSWORD.get(LoginConstant.YES));
        login.setIsDelete(false);
        login.setLoginName("dmz");
        login.setPasswd("0307");
        login.setUserNo(GenerateUUID.getUUID());
        login.setPlatform(LoginConstant.PLATFORM.get(LoginConstant.WEB));
        login.setPwdStrength(LoginConstant.PASS_STRENGTH.get(LoginConstant.WEAK));
        login.setStatus(LoginConstant.STATUS.get(LoginConstant.UNLOCKED));
        login.setCreateTime(new Date());
        login.setUpdateTime(new Date());
        loginMapper.insert(login);

    }
}
