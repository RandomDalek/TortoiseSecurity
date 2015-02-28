package hueller.jhonny.tortoisesecurity.model;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jhonny on 28/02/15.
 */
public class NewsXmlParser {
    private static final String nameSpace=null;
    public List parse(InputStream stream) throws XmlPullParserException,IOException{
        try{
            XmlPullParser myParser= Xml.newPullParser();
            myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            myParser.setInput(stream,null);
            myParser.nextTag();
            return readNews(myParser);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } finally{
            stream.close();
        }
    }
    private List readNews(XmlPullParser myParser) throws IOException, XmlPullParserException, ParseException {
        List news=new ArrayList();
        myParser.require(XmlPullParser.START_TAG,nameSpace,"news");
        while (myParser.next()!=XmlPullParser.END_TAG){
            if(myParser.getEventType()!=XmlPullParser.START_TAG){
                continue;
            }
            String tagName=myParser.getName();
            //start a new news (lol, pun)
            if (tagName.equals("entry")){
                news.add(readEntry(myParser));
            }else{
                skip(myParser);
            }
        }
        return news;
    }
    private News readEntry(XmlPullParser myParser) throws IOException, XmlPullParserException, ParseException {
        myParser.require(XmlPullParser.START_TAG,nameSpace,"entry");
        String title=null;
        Date date=null;
        String text=null;
        while (myParser.next()!=XmlPullParser.END_TAG){
            if (myParser.getEventType()!=XmlPullParser.END_TAG){
                continue;
            }
            String tag=myParser.getName();
            if (tag.equals("title")){
                title=readTitle(myParser);
            }else if (tag.equals("date")){
                date=readDate(myParser);
            }else if (tag.equals("text")){
                text=readText(myParser);
            }else {
                skip(myParser);
            }
        }
        return  new News(date,title,text);
    }
    private String readTitle(XmlPullParser myParser) throws IOException, XmlPullParserException {
        myParser.require(XmlPullParser.START_TAG,nameSpace,"title");
        String title=readString(myParser);
        myParser.require(XmlPullParser.END_TAG,nameSpace,"title");
        return title;
    }
    private Date readDate(XmlPullParser myParser) throws IOException, XmlPullParserException,ParseException {
        Date date=null;
        String strDate;
        SimpleDateFormat myFormat=new SimpleDateFormat("yyyy-MM-dd");
        myParser.require(XmlPullParser.START_TAG,nameSpace,"date");
        strDate=readString(myParser);
        myParser.require(XmlPullParser.END_TAG,nameSpace,"date");
        date=myFormat.parse(strDate);
        return date;
    }
    private String readText(XmlPullParser myParser) throws IOException, XmlPullParserException {
        myParser.require(XmlPullParser.START_TAG,nameSpace,"text");
        String text=readString(myParser);
        myParser.require(XmlPullParser.END_TAG,nameSpace,"text");
        return text;
    }
    private String readString(XmlPullParser myParser) throws IOException, XmlPullParserException {
        String result="";
        if (myParser.next()==XmlPullParser.TEXT){
            result=myParser.getText();
            myParser.nextTag();
        }
        return result;
    }
    private void skip(XmlPullParser myParser) throws XmlPullParserException, IOException {
        if (myParser.getEventType()!=XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }
        int depth=1;
        while (depth!=0){
            switch (myParser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
