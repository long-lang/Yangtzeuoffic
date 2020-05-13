package com.hh.parsexmldemo01;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WeatherServiceDom {
    public static List<WeatherInfo> getInfosFromXML (InputStream is) throws ParserConfigurationException, IOException, SAXException {
        List<WeatherInfo> weatherInfos = null;
        WeatherInfo weatherInfo = null;
        //建立DocumentBuilderFactor，用于获得DocumentBuilder对象：
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.建立DocumentBuidler：
        DocumentBuilder builder = factory.newDocumentBuilder();
        //3.建立Document对象，获取树的入口：
        Document doc = builder.parse(is);
        //4.建立NodeList：
        NodeList node = doc.getElementsByTagName("city");
        weatherInfos = new ArrayList<WeatherInfo>();
        //5.进行xml信息获取
        for(int i=0;i<node.getLength();i++){
            Element e = (Element)node.item(i);
            weatherInfo=new WeatherInfo();
            //String id=e.getAttribute("id");
            //weatherInfo.setId(id);
            String temp=e.getElementsByTagName("temp").item(0).getFirstChild().getNodeValue();
            weatherInfo.setTemp(temp);
            String weather=e.getElementsByTagName("weather").item(0).getFirstChild().getNodeValue();
            weatherInfo.setWeather(weather);
            String name=e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            weatherInfo.setName(name);
            String pm=e.getElementsByTagName("pm").item(0).getFirstChild().getNodeValue();
            weatherInfo.setPm(pm);
            String wind=e.getElementsByTagName("wind").item(0).getFirstChild().getNodeValue();
            weatherInfo.setWind(wind);
            weatherInfos.add(weatherInfo);
        }
        weatherInfo.toString();
       return weatherInfos;
    }
}
