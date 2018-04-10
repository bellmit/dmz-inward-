import com.dmz.basic.config.DataConfig;
import com.dmz.basic.config.PropertiesConfig;
import com.dmz.basic.document.UserDocument;
import com.dmz.basic.idao.ILoginDao;
import com.dmz.basic.idao.IUserDocumentDao;
import com.dmz.basic.model.Login;
import com.dmz.service.constant.basic.LoginConstant;
import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/8/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, DataConfig.class})
public class DataTest {

    @Resource
    private IUserDocumentDao userDocumentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ILoginDao loginDao;

    @Resource
    private RedissonClient redisson;

    @Test
    public void testSaveUserDocument() {
        UserDocument user = new UserDocument();
        user.setName("DMZS");
        userDocumentDao.saveUser(user);
        System.out.println();

    }

    @Test
    public void testSaveFile() {
        userDocumentDao.saveFile("users", new File("E:\\公共模板-账户系统-代码评审.xlsx"), "fileId",
                "companyid", "公共模板-账户系统-代码评审.xlsx");
    }

    @Test
    public void testGetFile() {
        GridFSDBFile file = userDocumentDao.retrieveFileOne("users", "fileId");
        //file.getInputStream();
        //new FileReader(new Inpu);
        //new FileReader(new Bufffile.getInputStream());

        //try (FileOutputStream fileOutPutStream = new FileOutputStream("E:\\公共模板-账户系统-代码评审_From_GridFS.xlsx");
        //     BufferedInputStream bf = new new BufferedInputStream){
        //
        //} catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        byte[] bytes = getFile(file);
        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\\\公共模板-账户系统-代码评审_From_GridFS.xlsx");) {
            //int c;
            //while ((c = file.getInputStream().read()) != -1) {
                fileOutputStream.write(bytes);
            //}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] getFile(GridFSDBFile queryFile){
        byte[] file = null;
        if(queryFile != null){
            try (ByteArrayOutputStream bao = new ByteArrayOutputStream();) {

                queryFile.writeTo(bao);
                file = bao.toByteArray();
            } catch (IOException e) {

            }
        }
        return file;
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

    @Test
    public void testRedisson() throws IOException, InterruptedException {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        String previous = concurrentHashMap.putIfAbsent("A", "AA");
        System.out.println(previous);
        System.out.println(concurrentHashMap.putIfAbsent("A", "BB"));
        //RKeys keys = redissonClient.getKeys();
        //RAtomicLong dmz = redissonClient.getAtomicLong("dmz");
        //RFuture<Boolean> future = dmz.compareAndSetAsync(401, 40000);
        //dmz.set(10000000);

        //future.handle((conn, exception) -> System.out.println(conn.booleanValue()));
        //System.out.println(future.isSuccess());

        //future.addListener(future1 -> {
        //    if (future1.isSuccess()) {
        //        // 取得结果
        //        Boolean result = future1.getNow();
        //        System.out.println(result);
        //        // ...
        //    } else {
        //        // 对发生错误的处理
        //        Throwable cause = future1.cause();
        //        System.out.println(cause);
        //    }
        //});

        //RLock lock2 = redisson.getLock("anyLock");

        RLock lock = redisson.getLock("anyLock");

        CountDownLatch countDownLatch = new CountDownLatch(2);
        CountDownLatch countDownLatchNext = new CountDownLatch(1);
        new Thread(() -> {
            try {
                if (lock.tryLock(5, 10, TimeUnit.SECONDS)) {    //leaseTime 50秒，50秒后锁被释放。
                    System.out.println("Redisson Try Lock");
                } else {
                    System.out.println("Redisson Time OUT");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            countDownLatchNext.countDown();
        }).start();

        //RLock lock2 = redisson.getLock("anyLock");
        //
        //new Thread(() -> {
        //    try {
        //        lock.lock();
        //        lock2.lock();
        //
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println();
        //}).start();
        //Thread.sleep(1000);

        //new Thread(() -> {
        //    //lock.lock();
        //    try {
        //        lock.tryLock(100, 100, TimeUnit.SECONDS);
        //        System.out.println("Thread lock");
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println();
        //}).start();
        //
        //Thread.sleep(1000);


        //boolean res = lock.tryLock(1, 10000, TimeUnit.SECONDS);
        //lock.lock(10000, TimeUnit.SECONDS); // 默认30秒释放
        //lock.lock(10000, TimeUnit.SECONDS); // 默认30秒释放
        //lock.tryLock(10, 1000, TimeUnit.SECONDS);
        //RLock lock = redisson.getLock("anyLock");
        countDownLatchNext.await();
        new Thread(()->{
            lock.lock();

            System.out.println("Redisson Try Lock GET AGAIN");

            lock.unlock();

            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
        lock.lock();
        //lock.lock();
        //lock.unlock();
        //lock.unlock();
        //lock.lock();

        System.out.println("--------------_++++++++++++++++++++++_----------------------" + lock.tryLock(10, 1000, TimeUnit.SECONDS));

        //lock.unlock();

        //
        //
        //
        //
        //System.in.read();

        //new Thread(()->lock.unlock()).start();


        //System.out.println(BigDecimal.ZERO.negate());


    }

}
