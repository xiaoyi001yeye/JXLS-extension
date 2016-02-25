package maven.search;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.DefaultHandler2;

/**
 * Created by weiyi on 16/2/25.
 */
public class SaxHandler extends DefaultHandler2
        implements DeclHandler {

    public SaxHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("qName = " + qName);
    }
}
