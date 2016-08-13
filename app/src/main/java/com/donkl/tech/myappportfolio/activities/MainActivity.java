package com.donkl.tech.myappportfolio.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.donkl.tech.myappportfolio.R;
import com.donkl.tech.myappportfolio.fastAdaptersItems.simpleAppItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /**
     * variables section
     */
    //Global variables
    private static final String TAG = "MainActivity" ;
    private FastItemAdapter<simpleAppItem> mAppsAdapter;
    private String mPreToastText;

    //views section
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.myapps_text)
    TextView mAppsText;
    @Bind(R.id.myapps_recycler)
    RecyclerView mAppsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind views with butter knife
        ButterKnife.bind(this);

        //make toolbar supported
        setSupportActionBar(mToolbar);

        //setup recycler items
        setupAppsAdapter(savedInstanceState);

        //make listener on item click to show toast
        mPreToastText = getResources().getString(R.string.pre_apps_toast);
        mAppsAdapter.withOnClickListener(new FastAdapter.OnClickListener<simpleAppItem>() {
            @Override
            public boolean onClick(View v, IAdapter<simpleAppItem> adapter, simpleAppItem item, int position) {
                Toast.makeText(MainActivity.this, mPreToastText +" "+ item.getAppName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = mAppsAdapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    /**
     * setup apps adapter with data and link with the recycler
     * @param savedInstanceState
     */
    private void setupAppsAdapter(Bundle savedInstanceState) {
        //setup recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        mAppsRecycler.setLayoutManager(linearLayoutManager);
        mAppsRecycler.setItemAnimator(new DefaultItemAnimator());

        //add divider to recycler
        mAppsRecycler.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.TRANSPARENT)
                        .sizeResId(R.dimen.default_margin)
                        .build());

        mAppsAdapter = new FastItemAdapter<>();

        mAppsRecycler.setAdapter(mAppsAdapter); // Used for single adapter type

        List<simpleAppItem> appItems = new ArrayList<>();
        //fill data
        appItems.add(new simpleAppItem("Popular Movies"));
        appItems.add(new simpleAppItem("Stock Hawk"));
        appItems.add(new simpleAppItem("Build it Bigger"));
        appItems.add(new simpleAppItem("Make Your App Material"));
        appItems.add(new simpleAppItem("Go Ubiquitous"));
        appItems.add(new simpleAppItem("Capstone"));

        mAppsAdapter.add(appItems); // Always add items after setting adapter

        // Call only after adding items
        mAppsAdapter.withSavedInstanceState(savedInstanceState);
    }

}
