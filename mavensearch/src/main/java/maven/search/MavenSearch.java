package maven.search;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weiyi on 16/2/23.
 */
public class MavenSearch {
    public static void main(String[] args) {
        File file = new File("http://repo1.maven.org/maven2/archetype-catalog.xml");

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream in = new FileInputStream(file);
            Document doc = builder.parse(in);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            // 选取所有class元素的name属性
            // XPath语法介绍： http://w3school.com.cn/xpath/
            XPathExpression expr = xpath.compile("//archetype[contains(description,'Spring Boot')]");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            System.out.println("node size = " + nodes.getLength());

            List<Project> projectList = new ArrayList<Project>();
            Set<String> projectFlags = new HashSet<String>();
            for (int i = 0; i < nodes.getLength(); i++) {
                Project project = new Project();
                NodeList nl = nodes.item(i).getChildNodes();
                project.setGroupId(nl.item(1).getTextContent());
                project.setArtifactId(nl.item(3).getTextContent());
                project.setVersion(nl.item(5).getTextContent());
                String projectFlag = String.format("%s::%s",project.getGroupId(),project.getArtifactId());
                if(!projectFlags.contains(projectFlag)){
                    projectFlags.add(projectFlag);
                    projectList.add(project);
                }

            }
            System.out.println("project size = "+projectList.size());
            for (int i = 0; i <projectList.size() ; i++) {
                System.out.println(projectList.get(i));
            }
            File projectRoot = new File("/Users/wy/code/mvnSpringBoot");
            if(projectRoot.exists()){
                projectRoot.delete();
            }
            projectRoot.mkdirs();
            projectRoot.setWritable(true);

            for (Project project : projectList){
                execMvnCmd(project);
            }


            System.out.println("Done.");

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
