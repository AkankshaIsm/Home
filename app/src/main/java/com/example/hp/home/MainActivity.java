package com.example.hp.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import retrofit.Callback;

import butterknife.InjectView;
import butterknife.ButterKnife;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnItemSelectedListener{

    public static final String rootURL="http://api.themoviedb.org/3/movie/"; //base url for movies  always end with /
    private static final String imageURL="https://image.tmdb.org/t/p/w780"; //base url for for images

    private List<MovieModel> movieModelList = null;
    private int pos =0;
    @InjectView(R.id.recyclerView)RecyclerView recyclerView;
    @InjectView(R.id.spinner)Spinner spinner;
    @InjectView(R.id.nav_view) NavigationView navigationView;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.drawer_layout) DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SwipeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Log.e("Error", "in oncreate");


        spinner.setOnItemSelectedListener(MainActivity.this);
        List<String> categories = new ArrayList<String>();
        categories.add("Title");
        categories.add("Rating");
        categories.add("Year");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        //set drop down view for list of categories
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieModel movieModel = movieModelList.get(position);
                Intent intent = new Intent(MainActivity.this,AboutMovie.class);
                intent.putExtra("MyKey", movieModel);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getPopular();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getPopular()
    {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = new ProgressDialog(MainActivity.this,R.style.AppTheme_AppBarOverlay);
        loading.setIndeterminate(true);
        loading.setMessage("Fetching Data");
        loading.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(rootURL)
                .build();
        MovieAPI api=adapter.create(MovieAPI.class);
        api.getPopular(new Callback<Example>() {

            @Override
            public void success(Example movieModels, Response response) {  loading.dismiss();  //if successful in fetching the json, stop the progress dialog
                Log.e("In Main ","MainActivity.success");
                movieModelList = movieModels.getResults(); //get movie model object
                switch (pos) {
                    case 0:
                        Collections.sort(movieModelList, MovieModel.titleComparator);
                        break;
                    case 1:
                        Collections.sort(movieModelList, MovieModel.ratingComparator);
                        break;
                    case 2:
                        Collections.sort(movieModelList, MovieModel.dateComparator);
                        break;

                }


                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //unable to fetch json from server
                loading.dismiss();
                Toast.makeText(MainActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
                //Log.e("error retro",error.toString());
            }
        });
    }

    private void showList()
    {
        MyAdapter myAdapter=new MyAdapter(movieModelList,imageURL);

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.e("Error", "showlist");
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        getPopular();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        getPopular();
    }



}