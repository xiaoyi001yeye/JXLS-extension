package maven.search;

import com.fasterxml.aalto.sax.SAXParserFactoryImpl;
import com.fasterxml.aalto.sax.SAXProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.DefaultHandler2;

import javax.xml.parsers.SAXParser;
import java.io.BufferedReader;
import java.util.List;
import java.util.Set;

/**
 * Created by weiyi on 16/2/25.
 */
public class MyxmlParser extends DefaultHandler2
        implements DeclHandler {

    private List<Project> projectList = Lists.newArrayList();

    private Project currentProject = null;

    private String preTag = null;

    private BufferedReader bufferdReader = null;

    public MyxmlParser(BufferedReader input) {
        bufferdReader = input;
    }

    public List<Project> getProjectList() throws Exception {
        SAXParserFactoryImpl spf = new SAXParserFactoryImpl();
        SAXParser sp = spf.newSAXParser();
        sp.setProperty(SAXProperty.LEXICAL_HANDLER.toExternal(), (DeclHandler) this);
        sp.parse(new InputSource(bufferdReader), this);
        return projectList;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        preTag = qName;
        if("archetype".equals(preTag)){
            currentProject = new Project();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(preTag == null){
            return;
        }
        if("groupId".equals(preTag)){
            currentProject.setGroupId(new String(ch,start,length));
        }else if("artifactId".equals(preTag)){
            currentProject.setArtifactId(new String(ch,start,length));
        }else if("version".equals(preTag)){
            currentProject.setVersion(new String(ch,start,length));
        }else if("description".equals(preTag)){
            currentProject.setDescription(new String(ch,start,length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        preTag = null;
        if("archetype".equals(qName)){
            if(currentProject==null)
                return;

            if(!StringUtils.contains(currentProject.getArtifactId(),"xml") )
                return;
            if(hashKey.contains(getHashKey(currentProject)))
                return;
            hashKey.add(getHashKey(currentProject));
            projectList.add(currentProject);
            currentProject = null;
        }
    }

    private Set<String> hashKey = Sets.newHashSet();

    private String getHashKey(Project project) {
        return String.format("%s-%s", project.getArtifactId(), project.getGroupId());
    }

}
