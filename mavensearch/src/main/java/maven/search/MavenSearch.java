package maven.search;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by weiyi on 16/2/23.
 */
public class MavenSearch {

    static File projectRoot = new File("D:\\code\\mvnSpringBoot");

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader httpStream = XmlFileUtil.getFile();
        System.out.println("get http time = " + (System.currentTimeMillis()-startTime));
        List<Project> projects = parseXml(httpStream);
        System.out.println("projects.size() = " + projects.size());
        for (Project p : projects) {
            System.out.println("ArtifactId = " + p.getArtifactId());
            System.out.println("Description = " + p.getDescription());
            System.out.println("---");
        }

        for (Project project : projects){
            execMvnCmd(project);
        }


        System.out.println("Done.");


    }



//    public static void xquery(InputStream inputSteam) throws Exception{
//        DDXQDataSource ds = new DDXQDataSource();
//        XQConnection conn = ds.getConnection();
//        XQExpression expression =conn.createExpression();
//        expression.bindString(new QName("configuration"), configuration,
//                conn.createAtomicType(XQItemType.XQBASETYPE_STRING));
//        expression.bindString(new QName("usage"), usage,
//                conn.createAtomicType(XQItemType.XQBASETYPE_STRING));
//        XQResultSequence result = expression.executeQuery(inputSteam);
//        while (result.next()) {
//            System.out.println(result.getItemAsString(null));
//        }
//        result.close();
//        exps.close();
//        conn.close();
//    }

    public static List<Project> parseXml(BufferedReader inputSteam) throws Exception {
        return new MyxmlParser(inputSteam).getProjectList();
    }

    private static void execMvnCmd(Project project)  {
        String mvnCmd = new StringBuilder()
                .append("D:\\apache-maven-3.1.1\\bin\\mvn.bat archetype:generate -B -DarchetypeGroupId=")
                .append(project.getGroupId())
                .append(" -DarchetypeArtifactId=").append(project.getArtifactId())
                .append(" -DarchetypeVersion=").append(project.getVersion())
                .append(" -DgroupId=com.riil -DartifactId=").append(project.getGroupId()).append("_").append(project.getArtifactId())
                .append(" -Dversion=1.0-SNAPSHOT -Dpackage=com.riil")
                .append(" -s D:\\code\\RIIL_BMC_EAC\\trunk\\doc\\settings.xml")
                .toString();
        System.out.println(mvnCmd);
//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process p = rt.exec(mvnCmd);
//            //Process p = rt.exec(mvnCmd);
//            InputStreamReader ir = new InputStreamReader(p
//                    .getInputStream());
//            BufferedReader br=new BufferedReader(ir);
//            String line;
//            p.waitFor();
//            while ((line = br.readLine()) != null){
//                System.out.println(line);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
