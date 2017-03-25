package com.kronistas.marvelcharacters.activities;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.fragments.CharsFragment;
import com.kronistas.marvelcharacters.fragments.ComicsFragment;
import com.kronistas.marvelcharacters.fragments.EventsFragment;
import com.kronistas.marvelcharacters.fragments.FabClickDialogFragment;
import com.kronistas.marvelcharacters.fragments.WelcomeFragment;
import com.kronistas.marvelcharacters.utils.Utils;
import com.kronistas.marvelcharacters.views.CustomTypefaceSpan;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FabClickDialogFragment.FragmentIterationListener {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawer;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;
    @InjectView(R.id.tittleToolbar)
    TextView tittleToolbar;
    FragmentManager fragmentManager;
    public static final String TAG = HomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.inject(this);

        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, new WelcomeFragment()).commit();

        navigationView.setNavigationItemSelectedListener(this);
        changeMenuFont();


    }

    private void changeMenuFont(){
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            Intent intent = new Intent(this, AboutMeActivity.class);
            startActivity(intent);
            return true;
            //about me
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new WelcomeFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("lookingFor", false);



        if (id == R.id.nav_characters) {
            tittleToolbar.setText(R.string.title_fragment_chars);
            fragment = CharsFragment.newInstance(bundle);

        } else if (id == R.id.nav_comics) {
            tittleToolbar.setText(R.string.title_fragment_comics);
            fragment = ComicsFragment.newInstance(bundle);

        } else if (id == R.id.nav_events) {
            tittleToolbar.setText(R.string.title_fragment_events);
            fragment = EventsFragment.newInstance(bundle);

        }


        fragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentIteration(Bundle parameters) {

        Fragment fragment = new WelcomeFragment();
        Bundle bundle = new Bundle();

        if (parameters.getBoolean("lookingFor")){
            Utils.log(TAG, "recibido lookingFor true");
            Utils.log(TAG,"recibido lookFor"+parameters.getString("lookFor"));

            bundle.putString("lookFor", parameters.getString("lookFor") );
            bundle.putBoolean("lookingFor", true);
        }else {
            bundle.putBoolean("lookingFor", false);
            Utils.log(TAG,"recibido lookFor"+parameters.getString("lookFor"));
        }
        Utils.log(TAG,"recibido TAGcaller"+parameters.getString("TAGcaller"));

        switch (parameters.getString("TAGcaller")){

            case "CharsFragment":
                fragment = CharsFragment.newInstance(bundle);
                break;

            case "ComicsFragment":
                fragment = ComicsFragment.newInstance(bundle);
                break;

            case "EventsFragment":
                fragment = EventsFragment.newInstance(bundle);
                break;

            default: break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentsContainer, fragment).commit();

    }


}
