package maven.search;

import com.google.common.collect.Lists;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by weiyi on 2016/2/24.
 */
public class XmlReader {

    public static void read(InputStream xmlInput) throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MyDefaultHandler handler = new MyDefaultHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(xmlInput));
    }

    static class MyDefaultHandler extends DefaultHandler {

        List<Project> projectList = Lists.newArrayList();

        public List<Project> getProjectList() {
            return projectList;
        }


        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
            return super.resolveEntity(publicId, systemId);
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            System.out
                    .println("--------------startElement开始执行--------------------------");
            System.out.println("uri:::" + uri);
            System.out.println("localName:::" + localName);
            System.out.println("qName:::" + qName);
            for (int i = 0; i < attributes.getLength(); i++) {
                String value = attributes.getValue(i);// 获取属性的value值
                System.out.println(attributes.getQName(i) + "-----" + value);
            }
            System.out.println("------------------startElement执行完毕---------------------------");
        }





    }
}
