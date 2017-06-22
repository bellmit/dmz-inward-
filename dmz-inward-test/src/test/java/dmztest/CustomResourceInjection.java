package dmztest;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class CustomResourceInjection {

    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void showResource() {
        try (InputStream input = resource.getInputStream()) {
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
