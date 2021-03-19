package m2dl.mobe.vacances.challenge.utils;


import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.level.PlateformeDure;
import m2dl.mobe.vacances.challenge.level.aPlateforme;

public class XMLParser {

    public XMLParser(){

    }


    public String read(Activity activity){
        Resources res = activity.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.level);
        StringBuffer stringBuffer = new StringBuffer();

        try {
            xrp.next();
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType == XmlPullParser.START_DOCUMENT)
                {
                    stringBuffer.append("--- Start XML ---");
                }
                else if(eventType == XmlPullParser.START_TAG)
                {
                    stringBuffer.append("\nSTART_TAG: "+xrp.getName());
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    stringBuffer.append("\nEND_TAG: "+xrp.getName());
                }
                else if(eventType == XmlPullParser.TEXT)
                {
                    stringBuffer.append("\nTEXT: "+xrp.getText());
                }
                eventType = xrp.next();

            }
            stringBuffer.append("\n--- End XML ---");
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return "pas de bol";
    }

    public List<Object> readLevel(Activity activity){
        Resources res = activity.getResources();
        XmlResourceParser xrp = res.getXml(R.xml.level);
        List<Object> objects = new ArrayList<>();

        try {
            xrp.next();
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG && xrp.getName().equals("PlateformeDure"))
                {
                    objects.add(buildRectangleDure(xrp));

                }

                eventType = xrp.next();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return objects;
    }

    private PlateformeDure buildRectangleDure(XmlResourceParser xrp) {
        List<Integer> rect = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = xrp.getEventType();
            String name = xrp.getName();
            System.out.println(name + " " + eventType + " " + XmlPullParser.END_TAG);
            while (eventType != XmlPullParser.END_TAG || !name.equals("PlateformeDure")){
                if(eventType == XmlPullParser.TEXT){
                    rect.add(Integer.parseInt(xrp.getText()));
                }
                eventType = xrp.next();
                name = xrp.getName();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PlateformeDure(rect.get(0),rect.get(1),rect.get(2),rect.get(3));
    }


}
