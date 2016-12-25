package lecture.mobile.final_project.ma01_20141105;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by cooling on 2016-10-05.
 */

public class MyXmlParser {


    public enum TagType { NONE, TITLE, CONTENTID,IMAGE, READCOUNT,MAPX,MAPY};

    public MyXmlParser() {
    }

    public ArrayList<CourseOfTravel> parse(String xml) {

        ArrayList<CourseOfTravel> resultList = new ArrayList<CourseOfTravel>();
        CourseOfTravel dbo = null;

        TagType tagType = TagType.NONE;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("item")) {
                            Log.d("cc",parser.getName());
                            dbo = new CourseOfTravel();
                        } else if (parser.getName().equals("contentid")) {
                            tagType = TagType.CONTENTID;
                        } else if (parser.getName().equals("firstimage2")) {
                            tagType = TagType.IMAGE;
                        } else if (parser.getName().equals("title")) {
                            tagType = TagType.TITLE;
                        } else if (parser.getName().equals("readcount")){
                            tagType = TagType.READCOUNT;
                        } else if (parser.getName().equals("mapx")){
                            tagType = TagType.MAPX;
                        } else if (parser.getName().equals("mapy")){
                            tagType = TagType.MAPY;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            resultList.add(dbo);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {
                            case IMAGE:
                                dbo.setFirstimage2(parser.getText());
                                break;
                            case TITLE:
                                dbo.setTitle(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case CONTENTID:
                                dbo.setContentid(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case READCOUNT:
                                dbo.setReadcount(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case MAPX:
                                dbo.setMapX(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case MAPY:
                                dbo.setMapY(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                        }
                        tagType = TagType.NONE;
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
