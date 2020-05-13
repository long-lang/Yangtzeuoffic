package com.hh.parsexmldemo01;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WeatherServiceSax {
    static List<WeatherInfo> weatherInfos;
    //存放标签名
    static  String tagName=null;
    //定义一个对象
    static  WeatherInfo weatherInfo = null;
    public static  List<WeatherInfo> getInfosFromXML () throws ParserConfigurationException, SAXException, IOException {
        //创建一个LIst容器存放WeatherInfo对象


        //1.先创建解析器工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.利用工厂创建一个解析器
        SAXParser saxParser = factory.newSAXParser();
        //3.调用解析方法，解析文件,该方法有两个参数，第一个参数是要解析的文件，第二个参数是处理类
        try{
        saxParser.parse("raw/weather1.xml",new MyDefaultHandler());
        }catch(SAXException e){
            e.printStackTrace();
        }
        return weatherInfos;
    }
     static class MyDefaultHandler extends DefaultHandler {

        @Override
        public void startDocument()throws SAXException{
            Log.v("sax","startDocument开始解析...");
            weatherInfos = new ArrayList<WeatherInfo>();
        }
        @Override
        public void endDocument() throws SAXException {
            Log.v("sax","endDocument解析完毕...");
        }
        //开始标签
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equals("city")) {                 //拿到一个Employee对象
                weatherInfo = new WeatherInfo();                 //就创建一个对象
               // String id = attributes.getValue("id");     //获取属性值
            }else {
                tagName = qName;
            }
        }
        //当前文本内容
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //构建当前读取到文本内容
            String msg = new String(ch,start,length);
            //tagName存放这当前的qName，判断当前拿取到的是哪个值
            if(tagName.equals("temp")) {
                weatherInfo.setTemp(msg);
            }else if(tagName.equals("weather")) {
                weatherInfo.setWeather(msg);
            }else if(tagName.equals("name")) {
                weatherInfo.setName(msg);
            }else if(tagName.equals("pm")) {
                weatherInfo.setPm(msg);
            }else if(tagName.equals("wind")) {
                weatherInfo.setWind(msg);
            }
        }

        //解析一个weatherInfo结束标签
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if(qName.equals("city")) {    //此时说明一个对象已经解析完，我们就将当前对象存放到容器中
                weatherInfos.add(weatherInfo);
            }else {
                //因为SAX解析也会识别空文本，所以每次遇到尾标签，需要将tagName值空，避免，将当前的值被空文本给覆盖掉
                tagName = null;
            }
        }

    }
}
