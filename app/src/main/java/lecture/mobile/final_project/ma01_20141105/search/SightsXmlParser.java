package lecture.mobile.final_project.ma01_20141105.search;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import lecture.mobile.final_project.ma01_20141105.model.Sights;

/**
 * Created by USER on 2016-12-25.
 */

public class SightsXmlParser {

    public enum TagType {NONE,ADDR1,ADDR2,AREACODE,CONTENTID,CONTENTTYPEID,
        MAPX,MAPY,MLEVEL,READCOUNT,SIGUNGUCODE,TEL,TITLE};

    public SightsXmlParser() {
    }

    public ArrayList<Sights> parse(String xml) {

        ArrayList<Sights> resultList = new ArrayList<Sights>();
        Sights dto = null;

        SightsXmlParser.TagType tagType = SightsXmlParser.TagType.NONE;

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
                            dto = new Sights();
                        } else if (parser.getName().equals("addr1")) {
                            tagType = SightsXmlParser.TagType.ADDR1;
                        } else if (parser.getName().equals("addr2")) {
                            tagType = TagType.ADDR2;
                        } else if (parser.getName().equals("areacode")) {
                            tagType = TagType.AREACODE;
                        } else if (parser.getName().equals("contentid")){
                            tagType = TagType.CONTENTID;
                        } else if (parser.getName().equals("contenttypeid")){
                            tagType = TagType.CONTENTTYPEID;
                        } else if (parser.getName().equals("mapx")){
                            tagType = SightsXmlParser.TagType.MAPX;
                        }else if (parser.getName().equals("mapy")){
                            tagType = SightsXmlParser.TagType.MAPY;
                        } else if (parser.getName().equals("mlevel")){
                            tagType = SightsXmlParser.TagType.MLEVEL;
                        } else if (parser.getName().equals("readcount")){
                            tagType = SightsXmlParser.TagType.READCOUNT;
                        } else if (parser.getName().equals("sigungucode")){
                            tagType = SightsXmlParser.TagType.SIGUNGUCODE;
                        } else if (parser.getName().equals("tel")){
                            tagType = SightsXmlParser.TagType.TEL;
                        } else if (parser.getName().equals("title")){
                            tagType = SightsXmlParser.TagType.TITLE;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            resultList.add(dto);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {
                            case ADDR1:
                                dto.setAddr1(parser.getText());
                                break;
                            case ADDR2:
                                dto.setAddr2(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case AREACODE:
                                dto.setAreacode(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case CONTENTID:
                                dto.setContentid(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case CONTENTTYPEID:
                                dto.setContenttypeid(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case READCOUNT:
                                dto.setReadcount(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case MAPY:
                                dto.setMapy(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case MLEVEL:
                                dto.setMlevel(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case SIGUNGUCODE:
                                dto.setSigungucode(Integer.valueOf(parser.getText()));
                                Log.d("aa",""+parser.getText());
                                break;
                            case TEL:
                                dto.setTel(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                            case TITLE:
                                dto.setMapx(parser.getText());
                                Log.d("aa",""+parser.getText());
                                break;
                        }
                        tagType = SightsXmlParser.TagType.NONE;
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
