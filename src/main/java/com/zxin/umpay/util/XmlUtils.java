package com.zxin.umpay.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;


public class XmlUtils {
	public static String mapToXml(Map<String, String> map, String rootName) {
		Element root = new Element(rootName);
		if (map == null)return xmlToString(root);
		for (String str : map.keySet()){
			root.addContent(new Element(str).setText((map.get(str) == null ? "": map.get(str) + "")));
		}
		return xmlToString(root);
	}
	
	public static Map<String, String> xmlToMap(String xmlStr) throws Exception {
		Map<String, String> map = new TreeMap<String, String>();
		if (xmlStr == null || "".equals(xmlStr)) {
			return map;
		}
		ByteArrayInputStream stream =null;
		try {
			xmlStr = URLDecoder.decode(xmlStr, "UTF-8");
			DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
			doc.setExpandEntityReferences(false);
			doc.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = doc.newDocumentBuilder();
			stream= new ByteArrayInputStream(xmlStr.getBytes());
			org.w3c.dom.Document documet = db.parse(stream);
			org.w3c.dom.Element root = documet.getDocumentElement();
			NodeList users = root.getChildNodes();
			for (int i = 0; i < users.getLength(); i++) {
				Node user = users.item(i);
				if(StringUtils.isNotEmpty(user.getTextContent())&&StringUtils.isNotBlank(user.getTextContent())){
					map.put(user.getNodeName(), user.getTextContent());
				}
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if(stream!=null){
				try {
					stream.close();
				} catch (Exception e2) {
				}
			}
		}
		return map;
	}

	public static String listToXml(List<Map<String, String>> list,
			String rootName, String parentName) {
		Element root = new Element(rootName);
		boolean flag = false;
		Element parentElement = null;
		Element child = null;
		if (list == null)
			return xmlToString(root);
		for (Map<String, String> map : list)
			if (flag) {
				flag = false;
				for (String str : map.keySet()) {
					child = new Element(str).setText(map.get(str) == null ? ""
							: (map.get(str) + ""));
					root.addContent(child);
				}
			} else {
				parentElement = new Element(parentName);
				root.addContent(parentElement);
				for (String str : map.keySet()) {
					child = new Element(str).setText(map.get(str) == null ? ""
							: (map.get(str) + ""));
					parentElement.addContent(child);
				}
			}
		return xmlToString(root);
	}
	
	public static List<Map<String, String>> xmlToList(String xmlStr) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		
		if(xmlStr==null || "".equals(xmlStr)){
			return resultList;
		}
		
		Map<String, String> map = null;
		boolean flag = true;

			xmlStr = URLDecoder.decode(xmlStr, "UTF-8");
			Reader in = new StringReader(xmlStr);
			Document doc = builder.build(in);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren();
			for (Element e : list) {
				if (e.getChildren().size() == 0) {
					if (flag) {
						flag = false;
						map = new HashMap<String, String>();
						resultList.add(map);
					}
					map.put(e.getName(), e.getText());
				} else {
					map = new HashMap<String, String>();
					List<Element> childrenList = e.getChildren();
					resultList.add(map);
					for (Element element : childrenList) {
						map.put(element.getName(), element.getText());
					}
				}
			}
		return resultList;
	}


	public static String xmlToString(Element element) {
		XMLOutputter output = new XMLOutputter();
		output.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
		Document doc = new Document(element);
		String str = output.outputString(doc);
		return str;
	}

	
	public static Element createNodes(Element parentElement,
			Map<String, String> map) {
		String msg = "";
		Iterator<String> it = map.keySet().iterator();
		String tempStr = "";
		Element sonElement = null;
		while (it.hasNext()) {
			tempStr = it.next();
			msg = (map.get(tempStr)) == null ? "" : (map.get(tempStr) + "");
			sonElement = new Element(tempStr);
			parentElement.addContent(sonElement.setText(msg));
		}
		return parentElement;
	}


	public static void createNodes(Element root, String[] strArr) {
		Element e = null;
		for (String str : strArr) {
			e = new Element(str);
			root.addContent(e);
		}
	}

	public static String xmlFileToString(String xmlPath, String rootName) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Map<String, String> map = new HashMap<String, String>();
		Document doc = builder.build(new File(xmlPath));
		Element root = doc.getRootElement();
		List<Element> list = root.getChildren();
		for (Element e : list) 
			map.put(e.getName(), e.getText());
		return mapToXml(map, rootName);
	}

	@SuppressWarnings("unused")
	public static String listToXml(Map<String,String> first_map,List<Map<String, String>> second_list,String rootName, String parentName) {
		Element root = new Element(rootName);
		boolean flag = false;
		Element parentElement = null;
		Element child = null;
		for (String str : first_map.keySet()) {
			child = new Element(str).setText(first_map.get(str) == null ? ""
					: (first_map.get(str) + ""));
			root.addContent(child);
		}
		for (Map<String, String> map : second_list){
			parentElement = new Element(parentName);
			root.addContent(parentElement);
			for (String str : map.keySet()) {
				child = new Element(str).setText(map.get(str) == null ? ""
						: (map.get(str) + ""));
				parentElement.addContent(child);
			}
		}
		return xmlToString(root);
	}
	
	
	@SuppressWarnings("unused")
	public static String listToXml(Map<String,String> first_map,Map<String, String> second_map,String rootName, String parentName) {
		Element root = new Element(rootName);
		boolean flag = false;
		Element parentElement = null;
		Element child = null;
		for (String str : first_map.keySet()) {
			child = new Element(str).setText(first_map.get(str) == null ? ""
					: (first_map.get(str) + ""));
			root.addContent(child);
		}
	    if(second_map!=null){
				parentElement = new Element(parentName);
				root.addContent(parentElement);
				for (String str : second_map.keySet()) {
					child = new Element(str).setText(second_map.get(str) == null ? "": (second_map.get(str) + ""));
					parentElement.addContent(child);
				}
	    }
		return xmlToString(root);
	}
	
	/**
	 * xml转成Javabean对象
	 * @param clazz
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(Class<T> clazz, String xml) throws JAXBException {
		JAXBContext newInstance = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = newInstance.createUnmarshaller();
		T bean = (T) unmarshaller.unmarshal(new StringReader(xml));
		return bean;
	}
	
	
	/**
	 * 获取XML序列化对象
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	public static Marshaller getMarshaller(Class<?> clazz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		// 格式化换行
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 去掉生成xml的默认报文头
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		// 不进行转义字符的处理
		marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
			public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
				writer.write(ch, start, length);
			}
		});
		return marshaller;
	}

}