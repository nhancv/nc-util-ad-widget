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
    ProductAdapter viewProductAdapter;
    RecyclerView.LayoutManager viewProductLayoutManager;
    SalonBoardAdapter viewSalonBoardAdapter;
    RecyclerView.LayoutManager viewSalonBoardLayoutManager;
    @Inject
    Bus bus;
    private String TAG = MainActivity.class.getName();
    private Integer[] mapping;

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
        mapping = new Integer[sizeProduct];
        for (int i = 0; i < sizeProduct; i++) {
            datasetProduct[i] = String.valueOf(i);
            mapping[i] = -1;
        }
        viewProductAdapter = new ProductAdapter(datasetProduct, bus, mapping);
        viewProduct.setAdapter(viewProductAdapter);

        viewSalonBoard.setHasFixedSize(true);
        viewSalonBoardLayoutManager = new LinearLayoutManager(this);
        viewSalonBoard.setLayoutManager(viewSalonBoardLayoutManager);
        String[] datasetSalonBoard = new String[15];
        for (int i = 0; i < 15; i++) {
            datasetSalonBoard[i] = String.valueOf(i);
        }
        viewSalonBoardAdapter = new SalonBoardAdapter(datasetSalonBoard, bus, mapping);
        viewSalonBoard.setAdapter(viewSalonBoardAdapter);


    }

    @Subscribe
    public void controlBus(ControlBus busData) {

        if (busData.className instanceof ProductAdapter) {
            Log.e(TAG, "ProductAdapter " + busData.dataCommand);
            viewSalonBoardAdapter.setProductTouchColor(viewProductAdapter.getItemClickColor());

        } else if (busData.className instanceof SalonBoardAdapter) {
            Log.e(TAG, "SalonBoardAdapter " + busData.dataCommand);
            if (busData.dataCommand == -2) {
                mapping[viewProductAdapter.getItemClickPos()] = viewSalonBoardAdapter.getItemClickPos();
                viewSalonBoardAdapter.updateColorList(viewProductAdapter.getItemClickColor());
                viewSalonBoardAdapter.paired();
                viewProductAdapter.paired();
            }

        }

    }

}
