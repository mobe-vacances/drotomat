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
import m2dl.mobe.vacances.challenge.game.goal.Goal;
import m2dl.mobe.vacances.challenge.game.platform.SolidPlatform;
import m2dl.mobe.vacances.challenge.game.player.Player;
import m2dl.mobe.vacances.challenge.game.platform.PlateformeFlick;


public class XMLParser {

    private Player player;

    public XMLParser(Player player){
        this.player = player;
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
        StringBuffer stringBuffer = new StringBuffer();
        List<Object> objects = new ArrayList<>();

        try {
            xrp.next();
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG && xrp.getName().equals("PlateformeDure"))
                {
                    objects.add(buildPlateformeDure(xrp));

                }

                else if(eventType == XmlPullParser.START_TAG && xrp.getName().equals("PlateformeFlick"))
                {
                    objects.add(buildPlateformeFlick(xrp));

                }

                else if(eventType == XmlPullParser.START_TAG && xrp.getName().equals("Start"))
                {
                    buildStart(xrp);

                }

                else if(eventType == XmlPullParser.START_TAG && xrp.getName().equals("Goal"))
                {
                    objects.add(buildGoal(xrp));

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

    private SolidPlatform buildPlateformeDure(XmlResourceParser xrp) {
        List<Integer> rect = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = xrp.getEventType();
            String name = xrp.getName();
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
        return new SolidPlatform(rect.get(0),rect.get(1),rect.get(2),rect.get(3), player);
    }

    private PlateformeFlick buildPlateformeFlick(XmlResourceParser xrp) {
        List<Integer> rect = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = xrp.getEventType();
            String name = xrp.getName();
            while (eventType != XmlPullParser.END_TAG || !name.equals("PlateformeFlick")){
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
        return new PlateformeFlick(rect.get(0),rect.get(1),rect.get(2),rect.get(3),rect.get(4), player);
    }

    private Goal buildGoal(XmlResourceParser xrp) {
        List<Integer> rect = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = xrp.getEventType();
            String name = xrp.getName();
            while (eventType != XmlPullParser.END_TAG || !name.equals("Goal")){
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

        return new Goal(player,rect.get(0),rect.get(1),rect.get(2),rect.get(3));
    }

    private void buildStart(XmlResourceParser xrp) {
        List<Integer> coor = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = xrp.getEventType();
            String name = xrp.getName();
            while (eventType != XmlPullParser.END_TAG || !name.equals("Start")){
                if(eventType == XmlPullParser.TEXT){
                    coor.add(Integer.parseInt(xrp.getText()));
                }
                eventType = xrp.next();
                name = xrp.getName();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setXY(coor.get(0),coor.get(1));
    }


}
