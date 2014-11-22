package com.candroid.app.util;

import com.candroid.app.dto.IncludeMacro;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLPullParserHandler {

    List<IncludeMacro> includeMacros;
    private IncludeMacro includeMacro;
    private String text;

    public XMLPullParserHandler() {
        includeMacros = new ArrayList<IncludeMacro>();
    }

    public List<IncludeMacro> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("macro")) {
                            includeMacro = new IncludeMacro();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("macro")) {
                            // Add Macro to list
                            includeMacros.add(includeMacro);
                        } else if (tagName.equalsIgnoreCase("name")) {
                            //macro.setName(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return includeMacros;
    }

    public List<IncludeMacro> getIncludeMacros() {
        return includeMacros;
    }
}
