package com.example.androidtoolbox.misc;

import android.os.Build;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.LogUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XmlActivity extends AppCompatActivity {

    @BindView(R.id.sax)
    Button sax;
    @BindView(R.id.dom)
    Button dom;
    @BindView(R.id.pull)
    Button pull;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.sax, R.id.dom, R.id.pull, R.id.pullWrite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sax:
                saxParser();
                break;
            case R.id.dom:
                domParser();
                break;
            case R.id.pull:
                pullParser();
                break;

            case R.id.pullWrite:
                ArrayList<Person> personArrayList=new ArrayList<>();
                personArrayList.add(new Person("tom",18,2));
                personArrayList.add(new Person("汤姆",19,3));
                try {
                    FileOutputStream fos=new FileOutputStream(getFilesDir()+"/persons.xml");
                    pullWrite(personArrayList,fos);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private static String arrayList2String(ArrayList<Person> personArrayList) {
        StringBuilder builder = new StringBuilder();
        for (Person p : personArrayList) {
            builder.append(p.toString());
        }
        return builder.toString();
    }

    private void saxParser() {
        try {
            InputStream inputStream = getAssets().open("persons.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse(inputStream, saxHandler);
            ArrayList<Person> personArrayList = saxHandler.getPersonArrayList();
            LogUtil.d(String.valueOf(personArrayList.size()));

//                personArrayList.forEach((it)->builder.append(it.toString()));
            textView.setText(arrayList2String(personArrayList));
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void domParser() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        ArrayList<Person> personArrayList = new ArrayList<>();
        LogUtil.d("开始解析!");
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream inputStream = getAssets().open("persons.xml");

            Document document = documentBuilder.parse(inputStream);

            LogUtil.d("处理改document的对象:" + documentBuilder.getDOMImplementation());
            LogUtil.d("处理改document的对象:" + document.getImplementation());

            NodeList personNodeList = document.getElementsByTagName("person");
            for (int i = 0; i < personNodeList.getLength(); i++) {
                Element item = (Element) personNodeList.item(i);
                Person person = new Person();
                person.setId(Integer.parseInt(item.getAttribute("id")));
                LogUtil.d("开始解析person");
                NodeList childNodes = item.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node node = childNodes.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        if ("name".equals(element.getNodeName())) {
                            LogUtil.d("处理name");
                            person.setName(element.getFirstChild().getNodeValue());
                        } else if ("age".equals(element.getNodeName())) {
                            LogUtil.d("处理age");
                            person.setAge(Integer.parseInt(element.getFirstChild().getNodeValue()));
                        }
                    }

                }
                LogUtil.d("结束解析person");
                personArrayList.add(person);
            }
            LogUtil.d("解析完毕!");
            textView.setText(arrayList2String(personArrayList));


        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void pullParser() {
        XmlPullParserFactory xmlPullParserFactory = null;
        try {
            InputStream inputStream = getAssets().open("persons.xml");
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(inputStream, StandardCharsets.UTF_8.displayName());
            int eventType = xmlPullParser.getEventType();
            Person person = null;
            ArrayList<Person> personArrayList = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        personArrayList = new ArrayList<>();
                        break;

                    case XmlPullParser.START_TAG:
                        if ("person".equals(xmlPullParser.getName())) {
                            person = new Person();
                            person.setId(Integer.parseInt(xmlPullParser.getAttributeValue(0)));
                        } else if ("name".equals(xmlPullParser.getName())) {
                            person.setName(xmlPullParser.nextText());
                        } else if ("age".equals(xmlPullParser.getName())) {
                            person.setAge(Integer.parseInt(xmlPullParser.nextText()));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("person".equals(xmlPullParser.getName())) {
                            personArrayList.add(person);
                        }
                        break;

                }
                eventType = xmlPullParser.next();
            }

            textView.setText(arrayList2String(personArrayList));
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pullWrite(List<Person> personList, OutputStream outputStream) {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            xmlSerializer.setOutput(outputStream,StandardCharsets.UTF_8.displayName());
            xmlSerializer.startDocument(StandardCharsets.UTF_8.displayName(),true);
            xmlSerializer.startTag(null,"persons");
            for(Person person:personList){
                xmlSerializer.startTag(null,"person");
                xmlSerializer.attribute(null,"id", String.valueOf(person.getId()));
                xmlSerializer.startTag(null,"name");
                xmlSerializer.text(person.getName());
                xmlSerializer.endTag(null,"name");
                xmlSerializer.startTag(null,"age");
                xmlSerializer.text(String.valueOf(person.getAge()));
                xmlSerializer.endTag(null,"age");
                xmlSerializer.endTag(null,"person");
            }

            xmlSerializer.endTag(null,"persons");
            xmlSerializer.endDocument();
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Person {
        private String name;
        private int age;
        private int id;

        public Person() {
        }

        public Person(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    static class SaxHandler extends DefaultHandler {
        public ArrayList<Person> getPersonArrayList() {
            return personArrayList;
        }

        private ArrayList<Person> personArrayList;
        private String tagName;
        private Person person;

        @Override
        public void startDocument() throws SAXException {
            LogUtil.d("开始解析!");
            personArrayList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            LogUtil.d("解析完毕!");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (localName.equals("person")) {
                LogUtil.d("开始解析person");
                person = new Person();
                person.setId(Integer.parseInt(attributes.getValue("id")));
            }

            tagName = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (localName.equals("person")) {
                LogUtil.d("结束解析person");
                personArrayList.add(person);
            }
            tagName = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (this.tagName != null) {
                String data = new String(ch, start, length);
                if (this.tagName.equals("name")) {
                    LogUtil.d("处理name");
                    person.setName(data);
                } else if (this.tagName.equals("age")) {
                    person.setAge(Integer.parseInt(data));
                    LogUtil.d("处理age");
                }
            }
        }
    }
}
