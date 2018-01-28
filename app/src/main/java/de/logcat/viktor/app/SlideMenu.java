package de.logcat.viktor.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class SlideMenu extends AppCompatActivity{
    //burger menu
    private static String TAG = MainActivity.class.getSimpleName();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void afterCreate() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        //burger menu start

        mNavItems.add(new NavItem("Meine Übungen", "Alle deine Übungen im Überblick", "android:actionModePasteDrawable"));
        mNavItems.add(new NavItem("Kalender", "Die Übungen im Zeitplan", "ic_menu_my_calendar"));
        mNavItems.add(new NavItem("Analyse", "Analysiere deine sportlichen Aktivitäten", "ic_menu_gallery"));



        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
                Intent intent = null;
                finish();
                if(position == 0){
                    intent = new Intent(SlideMenu.this, B01_RoutineView.class);
                }else if(position == 1){
                    intent = new Intent(SlideMenu.this, B03_CalendarView.class);
                }else if(position == 2){
                    intent = new Intent(SlideMenu.this, B05_StatisticsView.class);
                }else  {
                    Toast.makeText(getApplicationContext(), position, Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
            }
        });

        //open close methods for drawer
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    //nav item
    public class NavItem {
        String mTitle;
        String mSubtitle;
        String iconResource;
        Resources res = getResources();

        public NavItem(String title, String subtitle, String resource) {
            mTitle = title;
            mSubtitle = subtitle;
            iconResource = resource;
        }
    }

    /*
    * Called when a particular item from the navigation drawer
    * is selected.
    **/
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_main, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle other action bar items
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void updateRoutineList() {
    }
}
