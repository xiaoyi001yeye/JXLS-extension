package maven.search;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by weiyi on 2016/3/3.
 */
public class XmlFileUtil {

    public static BufferedReader getFile() throws IOException {
        File tempDir = Files.createTempDir().getParentFile();
        File localXmlFile = new File(tempDir, "archetype-catalog.xml");
        if(!localXmlFile.exists()){
            byte[] content = openHttp();
            Files.write(content, localXmlFile);
        }
        return Files.newReader(localXmlFile,Charsets.UTF_8);
    }


    private static byte[] openHttp() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://repo1.maven.org/maven2/archetype-catalog.xml")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().bytes();
    }
}
