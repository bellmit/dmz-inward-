package dmztest;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class CustomResourceLoader implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void showResources() {

//        Resource banner = resourceLoader.getResource("http://howtodoinjava.com/readme.txt");
//        Resource banner = resourceLoader.getResource("file:c:/temp/filesystemdata.txt");

//        To load file from application folder, use below template:
//        Resource banner = resourceLoader.getResource("file:data.txt");

        Resource resource = resourceLoader.getResource("classpath:xml-test.xml");
        try (InputStream input = resource.getInputStream()){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input, Charset.forName("utf-8")));
            while (true) {
                String strLine = bufferedReader.readLine();
                if (strLine == null) {
                    break;
                }
                System.out.println(strLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
