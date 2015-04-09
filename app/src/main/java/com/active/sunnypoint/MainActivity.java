package com.active.sunnypoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import timber.log.Timber;


public class MainActivity extends Activity {

    private AlphabetListAdapter adapter;

    ListView getListView() {

        return (ListView) findViewById(R.id.list);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(MainActivity.class.getSimpleName());
        setContentView(R.layout.list_alphabet);

//        ButterKnife.inject(this);

        ArrayList<Info> rows = new ArrayList<Info>();
        rows.add(new Info("Use 827 Points 03/27"));
        rows.add(new Info("03/26 Get 587 Points"));
        rows.add(new Info("Get 500 Points 03/16"));
        rows.add(new Info("03/05 Get 136 Points"));
        rows.add(new Info("2015 March",true));
        rows.add(new Info("Use 827 Points 03/27"));
        rows.add(new Info("03/26 Get 587 Points"));
        rows.add(new Info("Get 500 Points 03/16"));
        rows.add(new Info("03/05 Get 136 Points"));
        rows.add(new Info("2015 March",true));
        rows.add(new Info("Use 827 Points 03/27"));
        rows.add(new Info("03/26 Get 587 Points"));
        rows.add(new Info("Get 500 Points 03/16"));
        rows.add(new Info("03/05 Get 136 Points"));
        rows.add(new Info("2015 March",true));
        rows.add(new Info("Use 827 Points 03/27"));
        rows.add(new Info("03/26 Get 587 Points"));
        rows.add(new Info("Get 500 Points 03/16"));
        rows.add(new Info("03/05 Get 136 Points"));
        rows.add(new Info("2015 March",true));
        rows.add(new Info("Use 827 Points 03/27"));
        rows.add(new Info("03/26 Get 587 Points"));
        rows.add(new Info("Get 500 Points 03/16"));
        rows.add(new Info("03/05 Get 136 Points"));
        rows.add(new Info("2015 March",true));
        adapter = new AlphabetListAdapter(rows);
        getListView().setAdapter(adapter);

    }

    public void updateList() {
        TextView tmpTV;
            tmpTV = new TextView(this);
            tmpTV.setGravity(Gravity.CENTER);
            tmpTV.setTextSize(15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            tmpTV.setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.reset(this);
    }
}
