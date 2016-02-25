package maven.search;

import com.fasterxml.aalto.sax.SAXParserFactoryImpl;
import com.fasterxml.aalto.sax.SAXProperty;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.ext.DeclHandler;

import javax.xml.parsers.SAXParser;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * Created by weiyi on 16/2/23.
 */
public class MavenSearch {
    public static void main(String[] args) throws Exception {
//        parseXml(openHttp());
//            File projectRoot = new File("/Users/wy/code/mvnSpringBoot");
//            if(projectRoot.exists()){
//                projectRoot.delete();
//            }
//            projectRoot.mkdirs();
//            projectRoot.setWritable(true);
//
//            for (Project project : projectList){
//                execMvnCmd(project);
//            }


            System.out.println("Done.");


    }

    @Test
    public void getFile() throws IOException {
        URL input = MavenSearch.class.getResource("/x.xml");

        if(input == null || input.openStream()!=null){
            FileOutputStream fos = new FileOutputStream("/x.xml")
            fos.write(input2byte());
        }
    }

//    @Test
    public InputStream openHttp() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://repo1.maven.org/maven2/archetype-catalog.xml")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().byteStream();
    }

    public static void parseXml(InputStream inputSteam) throws Exception {
        SAXParserFactoryImpl spf = new SAXParserFactoryImpl();
        SAXParser sp = spf.newSAXParser();
        SaxHandler h = new SaxHandler();
        sp.setProperty(SAXProperty.LEXICAL_HANDLER.toExternal(), (DeclHandler) h);
        sp.parse(new InputSource(inputSteam), h);
    }

    public static ByteBuffer input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return ByteBuffer.wrap(in2b);
    }


    private static void execMvnCmd(Project project)  {
        String mvnCmd = new StringBuilder().append("mvn archetype:generate -B -DarchetypeGroupId=").append(project.getGroupId()).append(" -DarchetypeArtifactId=").append(project.getArtifactId()).append(" -DarchetypeVersion=").append(project.getVersion()).append(" -DgroupId=com.riil -DartifactId=").append(project.getGroupId()).append("_").append(project.getArtifactId()).append(" -Dversion=1.0-SNAPSHOT -Dpackage=com.riil").toString();
        System.out.println(mvnCmd);
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(mvnCmd);
            //Process p = rt.exec(mvnCmd);
            InputStreamReader ir = new InputStreamReader(p
                    .getInputStream());
            BufferedReader br=new BufferedReader(ir);
            String line;
            p.waitFor();
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}