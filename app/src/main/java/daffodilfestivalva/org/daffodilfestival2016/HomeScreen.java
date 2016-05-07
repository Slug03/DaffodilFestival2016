package daffodilfestivalva.org.daffodilfestival2016;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragManager;
    private ActionBar actionBar;
    private ArrayList <String> navArray;
    ArrayAdapter<String> adapter;
    private int lastClickedPosition;
    private boolean vendorMenuPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBar = getSupportActionBar();
        navList = (ListView) findViewById(R.id.navList);

        navArray = new ArrayList<String>(); //menu items
        navArray.add("Home");
        navArray.add("About Us");
        navArray.add("Vendors");
        navArray.add("Schedule");
        navArray.add("Map");
        navArray.add("Sponsors");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, navArray);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBar.setDisplayHomeAsUpEnabled(true); // Back button on the action bar when drawer is open
        actionBar.setDisplayShowHomeEnabled(true); // hamburger button when drawer is closed
        fragManager = getSupportFragmentManager();
        fragTransaction = fragManager.beginTransaction();

        vendorMenuPresent = false;
        loadFrag(0); //The default fragment is the home
    }

    private void loadFrag(int i)
    {
        if(vendorMenuPresent)
        {
            if(i <= 4 || i == 6)
                navList.setItemChecked(i == 6 ? lastClickedPosition : i, true);
            else
                navList.setItemChecked(i - 2, true);
        }
        else
        {
            int tempHighlight = -1;
            if(i > 2 && i != 6)
                tempHighlight = i - 2;
            else
                tempHighlight = i;
            navList.setItemChecked(i == 6 ? lastClickedPosition : tempHighlight, true);
        }
        if(i == 0)
        {
            actionBar.setTitle("Welcome");
            fragTransaction = fragManager.beginTransaction();
            HomeFragment home = new HomeFragment();
            fragTransaction.replace(R.id.fragmentholder,home);
            fragTransaction.commit();
        }
        else if(i == 1)
        {
            actionBar.setTitle("About Us");
            fragTransaction = fragManager.beginTransaction();
            AboutUs aboutUs = new AboutUs();
            fragTransaction.replace(R.id.fragmentholder, aboutUs);
            fragTransaction.commit();
        }
        else if(i == 3)
        {
            actionBar.setTitle("Vendor List");
            fragTransaction = fragManager.beginTransaction();
            Vendors vendors = new Vendors();
            fragTransaction.replace(R.id.fragmentholder,vendors);
            fragTransaction.commit();
        }
        else if(i == 4)
        {
            actionBar.setTitle("Vendor Search");
            fragTransaction = fragManager.beginTransaction();
            Vendors vendors = new Vendors();
            fragTransaction.replace(R.id.fragmentholder,vendors);
            fragTransaction.commit();
        }
        else if(i == 5)
        {
            actionBar.setTitle("Schedule");
            fragTransaction = fragManager.beginTransaction();
            Schedule schedule = new Schedule();
            fragTransaction.replace(R.id.fragmentholder, schedule);
            fragTransaction.commit();
        }
        else if(i == 6)
        {
            new Map().execute(this);
        }
        else if(i == 7)
        {
            actionBar.setTitle("Sponsors");
            fragTransaction = fragManager.beginTransaction();
            Sponsors sponsors = new Sponsors();
            fragTransaction.replace(R.id.fragmentholder,sponsors);
            fragTransaction.commit();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeFragment/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
            return true;
        else if(id == android.R.id.home)
        {
            if(drawerLayout.isDrawerOpen(navList))
                drawerLayout.closeDrawer(navList); //if drawer is open, close it
            else
                drawerLayout.openDrawer(navList); //if drawer is closed, open it
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(position != 2 && vendorMenuPresent)
        {
            loadFrag(position); // load the correct fragment
            lastClickedPosition = position == 6 ? lastClickedPosition : position;
            drawerLayout.closeDrawer(navList);
            if(position != 3 && position != 4 && position != 6)
            {
                navArray.remove(3);
                navArray.remove(3);
                vendorMenuPresent = false;
                adapter.notifyDataSetChanged();
            }

        }
        else if(position != 2 && !vendorMenuPresent)
        {
            if(position > 2)
                loadFrag(position + 2); // load the correct fragment
            else
                loadFrag(position);

            lastClickedPosition = position == 4 ? lastClickedPosition : position;
            drawerLayout.closeDrawer(navList);
        }
        else if(position == 2 && !vendorMenuPresent)
        {
            vendorMenuPresent = true;
            drawerLayout.closeDrawer(navList);
            navArray.add(3,"\tVendor List");
            navArray.add(4,"\tVendor Search");
            adapter.notifyDataSetChanged();
            drawerLayout.openDrawer(navList);
        }
        else if(position == 2 && vendorMenuPresent)
        {
            vendorMenuPresent = false;
            drawerLayout.closeDrawer(navList);
            navArray.remove(3);
            navArray.remove(3);
            adapter.notifyDataSetChanged();
            drawerLayout.openDrawer(navList);
        }
    }
}