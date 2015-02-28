package hueller.jhonny.tortoisesecurity.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hueller.jhonny.tortoisesecurity.R;
import hueller.jhonny.tortoisesecurity.controll.FragmentCommunicator;
import hueller.jhonny.tortoisesecurity.model.News;
import hueller.jhonny.tortoisesecurity.model.NewsXmlParser;


public class Home extends Activity implements FragmentCommunicator{
    private String tag="TortoiseSecurity";
    public static final String WIFI="Wi-Fi";
    public static final String ANY="any";
    private static final URL URL=null;
    public static boolean refreshDisplay=true;
    public static String sPref=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentManager myManager=getFragmentManager();
        myManager.popBackStack();
        FragmentTransaction myTransaction=myManager.beginTransaction();
        HomeFragment myHomeFragment=new HomeFragment();
        myTransaction.add(R.id.layoutHomeActivity,myHomeFragment,"HomeFragment");
        myTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickChooseNews(int idNews) {
        String[] titles,texts;
        titles=getResources().getStringArray(R.array.NewsTitle);
        texts=getResources().getStringArray(R.array.NewsText);
        DetailNewsFragment myDetailNewsFragment=DetailNewsFragment.newInstance(titles[idNews],texts[idNews]);
        FragmentManager myFragmentManager=getFragmentManager();
        FragmentTransaction myFragmentTransaction=myFragmentManager.beginTransaction();
        myFragmentTransaction.addToBackStack(null);
        myFragmentTransaction.replace(R.id.layoutHomeActivity,myDetailNewsFragment,"DetailNewsFragment");
        myFragmentTransaction.commit();
    }

    @Override
    public void watchNews() {
        NewsFragment myNewsFragment=new NewsFragment();
        FragmentManager myFragmentManager=getFragmentManager();
        FragmentTransaction myTransaction=myFragmentManager.beginTransaction();
        myTransaction.addToBackStack(null);
        myTransaction.replace(R.id.layoutHomeActivity,myNewsFragment,"News Fragment");
        myTransaction.commit();
    }

    @Override
    public void watchCourses() {

    }

    @Override
    public void lockOutTheNemesis() {

    }

    @Override
    public void loadNews() {
        List<News> news=getNewsFromNetwork();

    }

    @Override
    public void loadCourses() {

    }

    private List<News> getNewsFromNetwork(){
        List<News> newsToLoad=null;
        if (checkNetwork(this)){
            newsToLoad= (List<News>) new DownloadNewsXmlTask().execute(URL);
        }else {
            Toast myToast=Toast.makeText(this,"Manca connessione internet",Toast.LENGTH_LONG);
            myToast.show();
        }
        return newsToLoad;
    }
    private class DownloadNewsXmlTask extends AsyncTask<URL,Integer,List<News>>{

        @Override
        protected List<News> doInBackground(URL... url) {
            List<News> myNews=new ArrayList<>();
            try{
                myNews=loadNewsFromNetwork(url[0]);
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }catch (XmlPullParserException e){
                e.printStackTrace();
                return null;
            }
            return null;
        }

        private List<News> loadNewsFromNetwork(URL url) throws IOException,XmlPullParserException{
            InputStream input=null;
            NewsXmlParser myNewsXmlParser=new NewsXmlParser();
            List<News> news=null;
            try{
                input=downloadUrl(url);
                news=myNewsXmlParser.parse(input);
            }finally {
                if (input!=null){
                    input.close();
                }
            }

            return news;
        }
        private InputStream downloadUrl(URL url) throws IOException {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            return conn.getInputStream();
        }
    }
    private boolean checkNetwork(Context context){
        ConnectivityManager myConnectivityManager=(ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo i=myConnectivityManager.getActiveNetworkInfo();
        boolean con;
        if (i.isAvailable()){
            con=true;
        }else if (i.isConnected()){
            con=true;
        }else {
            con=false;
        }
        return con;
    }
}
