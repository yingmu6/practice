package relative.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author chensy
 * @date 2021/4/20
 */
public class Dom4jTest {
    public static void main(String[] args) {
        SAXReader saxReader = new SAXReader();
        Document document = null; //
        try {
            File file = new File("/Users/chenshengyong/selfPro/util_db/basic-util/src/main/resources/dom.xml");
            document = saxReader.read(file); //Document的实现类为DefaultDocument
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }

        Element rootElement = document.getRootElement();
        System.out.println("整个文档：\n" + rootElement.asXML());

        String rootName = rootElement.getName();
        System.out.println("根元素名称：" + rootName);

        Element book = rootElement.element("book");
        Attribute attribute = book.attribute("id");
        System.out.println("属性名：" + attribute.getName() + ", 属性值：" + attribute.getValue());

    }
}
