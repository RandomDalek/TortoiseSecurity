package hueller.jhonny.tortoisesecurity.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import hueller.jhonny.tortoisesecurity.R;
import hueller.jhonny.tortoisesecurity.controll.FragmentCommunicator;


public class Home extends Activity implements FragmentCommunicator{
    private String tag="TortoiseSecurity";
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
}
