package qid.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 读取XML文件	
 * 1.domReadFileXML		DOM   实现方法
 * 2.dom4jReadFileXML	DOM4J 实现方法
 * 3.jdomReadFileXML	JDOM  实现方法
 * 4.saxReadFileXML		sax	     实现方法
 * @author djun
 *
 */
public class FileRedXML extends DefaultHandler{
	@SuppressWarnings("rawtypes")
	Stack tags = new Stack();
	public FileRedXML(){//
		super();
	}


	/**
	 * DOM   实现方法
	 */
	public static void domReadFileXML(){
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("D:\\waterSend\\parameter.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nl = doc.getElementsByTagName("Account");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println("学号："+ doc.getElementsByTagName("code").item(i).getFirstChild().getNodeValue());
				System.out.println("密码："+ doc.getElementsByTagName("pass").item(i).getFirstChild().getNodeValue());
				System.out.println("姓名："+ doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue());
				System.out.println("价格："+ doc.getElementsByTagName("money").item(i).getFirstChild().getNodeValue());
			}
			System.out.println("运行时间：" + (System.currentTimeMillis() - lasting)+ "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DOM4J 实现方法
	 * 需要	dom4j.jar
	 */
	@SuppressWarnings("rawtypes")
	public static void dom4jReadFileXML(){
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("D:\\waterSend\\parameter.xml");
			SAXReader Read = new SAXReader();
			org.dom4j.Document doc = Read.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("VALUE"); i.hasNext();) {
				foo = (Element) i.next();
				System.out.println("学号："+ foo.elementText("code"));
				System.out.println("密码："+ foo.elementText("pass"));
				System.out.println("姓名："+ foo.elementText("name"));
				System.out.println("价格："+ foo.elementText("money"));
			}
			System.out.println("运行时间：" + (System.currentTimeMillis() - lasting)+ "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * jdom	实现方法
	 * 需要   jdom-2.0.6.jar
	 */
	@SuppressWarnings("rawtypes")
	public static void jdomReadFileXML(){
		long lasting = System.currentTimeMillis();
		try {
			SAXBuilder builder = new SAXBuilder();
			org.jdom2.Document doc = builder.build(new File("D:\\waterSend\\parameter.xml"));
			org.jdom2.Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for (int i = 0; i < allChildren.size(); i++) {
				System.out.println("学号："+ ((org.jdom2.Element) allChildren.get(i)).getChild("code").getText());
				System.out.println("密码："+ ((org.jdom2.Element) allChildren.get(i)).getChild("pass").getText());
				System.out.println("姓名："+ ((org.jdom2.Element) allChildren.get(i)).getChild("name").getText());
				System.out.println("价格："+ ((org.jdom2.Element) allChildren.get(i)).getChild("money").getText());
			}
			System.out.println("运行时间：" + (System.currentTimeMillis() - lasting)+ "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * jdom	实现方法
	 * 需要   sax-2.0.1.jar
	 * 需要继承DefaultHandler
	 * 
	 */
	public static void saxReadFileXML(){
		long lasting = System.currentTimeMillis();
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			FileRedXML reader = new FileRedXML();
			sp.parse(new InputSource("D:\\waterSend\\parameter.xml"),reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("运行时间：" + (System.currentTimeMillis() - lasting)+ "毫秒");
	}
	public void characters(char ch[], int start, int length)throws SAXException {
		String tag = (String) tags.peek();
		if (tag.equals("code") && tag.indexOf("/") <= 0) {
			System.out.println("学号：" + new String(ch, start, length));
		}
		if (tag.equals("pass")) {
			System.out.println("密码：" + new String(ch, start, length));
		}
		if (tag.equals("name")) {
			System.out.println("姓名：" + new String(ch, start, length));
		}
		if (tag.equals("money")) {
			System.out.println("价格：" + new String(ch, start, length));
		}
	}
	@SuppressWarnings("unchecked")
	public void startElement(String uri, String localName, String qName,Attributes attrs) {
			tags.push(qName);
	}
	
	
	public static void main(String[] args) {
		saxReadFileXML();
	}
	
}
