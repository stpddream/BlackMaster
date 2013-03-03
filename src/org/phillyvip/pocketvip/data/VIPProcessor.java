package org.phillyvip.pocketvip.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.phillyvip.pocketvip.test.*;

import android.util.Log;

public class VIPProcessor {
	
	public static LinkedList<Case> parseCase(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		
		LinkedList<Case> caseList = new LinkedList<Case>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();	
		Document dom = db.parse(in);
		Element docEle = dom.getDocumentElement();				
		
		NodeList nl = docEle.getElementsByTagName("node");
		
		Log.i(VIPTest.TESTTAG, "AHALength" + nl.getLength());

		if(nl != null && nl.getLength() > 0) {
			for(int i = 0; i < nl.getLength(); i++) {
				Element node = (Element)nl.item(i);
				
				Element eleTopic = (Element)node.getElementsByTagName("Topic").item(0);
				Element eleCategory = (Element)node.getElementsByTagName("Category").item(0);
				Element eleDescription = (Element)node.getElementsByTagName("Description").item(0);
				Element eleCaseNumber = (Element)node.getElementsByTagName("CaseNumber").item(0);
				Element eleUrgentFlag = (Element)node.getElementsByTagName("UrgentFlag").item(0);

				String topic = eleTopic.getFirstChild().getNodeValue();
				String category = eleCategory.getFirstChild().getNodeValue();
				String caseNumber= eleCaseNumber.getFirstChild().getNodeValue();
				String description  = eleDescription.getFirstChild().getNodeValue();
				String urgentStr = eleUrgentFlag.getFirstChild().getNodeValue();
				boolean urgentFlag = urgentStr.equals("True")?true:false;
				
				Log.i(VIPTest.TESTTAG, "Topic: " + topic);

				caseList.add(new Case(category,topic,caseNumber,description,urgentFlag));

			}

		}
		return caseList;

	}

}
