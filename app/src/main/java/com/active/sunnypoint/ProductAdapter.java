package com.active.sunnypoint;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.active.sunnypoint.dagger.ControlBus;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NhanCao on 01-Sep-15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Bus bus;
    private String[] mDataset;
    private Integer[] colorList;

    public ProductAdapter(String[] myDataset, Bus bus) {
        mDataset = myDataset;
        this.bus = bus;
        initialColorList();
    }

    private void initialColorList() {
        //generate color list
        colorList = new Integer[mDataset.length];
        for (int i = 0; i < mDataset.length; i++) {
            colorList[i] = Utils.randomColor(0xFF);
        }
    }

    public void updateUiColorList() {


        notifyDataSetChanged();
    }

    public Integer[] getColorList() {
        return colorList;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Utils.setBackground(holder.viewProductItem, colorList[position]);
        holder.mTextView.setText(mDataset[position]);
        holder.viewProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new ControlBus(this, position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.viewProductItem)
        View viewProductItem;
        @Bind(R.id.txtView)
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
