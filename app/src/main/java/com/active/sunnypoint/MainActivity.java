package com.active.sunnypoint;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.active.sunnypoint.dagger.ControlBus;
import com.active.sunnypoint.dagger.DaggerUiComponent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;


public class MainActivity extends Activity {
    @Bind(R.id.viewProduct)
    RecyclerView viewProduct;
    @Bind(R.id.viewSalonBoard)
    RecyclerView viewSalonBoard;
    RecyclerView.Adapter viewProductAdapter;
    RecyclerView.LayoutManager viewProductLayoutManager;
    RecyclerView.Adapter viewSalonBoardAdapter;
    RecyclerView.LayoutManager viewSalonBoardLayoutManager;
    @Inject
    Bus bus;
    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUiComponent.builder().singletonComponent(((BaseApp) getApplication()).getSingletonComponent()).build().inject(this);
        bus.register(this);
        Timber.tag(MainActivity.class.getSimpleName());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();
    }

    private void setupViews() {
        int sizeProduct = 20;

        viewProduct.setHasFixedSize(true);
        viewProductLayoutManager = new LinearLayoutManager(this);
        viewProduct.setLayoutManager(viewProductLayoutManager);
        String[] datasetProduct = new String[sizeProduct];
        for (int i = 0; i < sizeProduct; i++) {
            datasetProduct[i] = String.valueOf(i);
        }
        viewProductAdapter = new ProductAdapter(datasetProduct, bus);
        viewProduct.setAdapter(viewProductAdapter);

        viewSalonBoard.setHasFixedSize(true);
        viewSalonBoardLayoutManager = new LinearLayoutManager(this);
        viewSalonBoard.setLayoutManager(viewSalonBoardLayoutManager);
        String[] datasetSalonBoard = new String[15];
        for (int i = 0; i < 15; i++) {
            datasetSalonBoard[i] = String.valueOf(i);
        }
        viewSalonBoardAdapter = new SalonBoardAdapter(datasetSalonBoard);
        viewSalonBoard.setAdapter(viewSalonBoardAdapter);


    }

    @Subscribe
    public void controlBus(ControlBus busData) {
        Log.e(TAG, "controlBus " + busData.data);
    }

}
