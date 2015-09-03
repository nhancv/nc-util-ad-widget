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
    private int positionTouch = -1;
    private Integer[] mapping;

    public ProductAdapter(String[] myDataset, Bus bus, Integer[] mapping) {
        mDataset = myDataset;
        this.bus = bus;
        this.mapping = mapping;
        initialColorList();
    }

    private void initialColorList() {
        //generate color list
        colorList = new Integer[mDataset.length];
        for (int i = 0; i < mDataset.length; i++) {
            int color = Utils.randomColor(0xFF);
            while (true) {
                boolean check = false;
                for (int j = 0; j < i; j++) {
                    if (color == colorList[j]) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    color = Utils.randomColor(0xFF);
                } else {
                    colorList[i] = color;
                    break;
                }
            }
        }
    }

    public void updateUiColorList() {


        notifyDataSetChanged();
    }

    public Integer[] getColorList() {
        return colorList;
    }

    public void setMapping(Integer[] mapping) {
        this.mapping = mapping;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_product, parent, false);
        return new ViewHolder(v);
    }

    private boolean checkPaired(int position) {
        return mapping[position] != -1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position != positionTouch) {
            if (checkPaired(position)) {
                Utils.setBackground(holder.viewProductItem, colorList[position], colorList[position]);
            } else {
                Utils.setBackground(holder.viewProductItem, colorList[position]);
            }
        } else {
            Utils.setBackground(holder.viewProductItem, colorList[positionTouch], 0xFF6699FF);
        }
        holder.mTextView.setText(mDataset[position]);
        holder.viewProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick(v, position);
                bus.post(new ControlBus(ProductAdapter.this, positionTouch));
            }
        });
    }

    public void itemClick(View v, int position) {
        if (positionTouch == position) positionTouch = -1;
        else positionTouch = position;
        notifyDataSetChanged();
    }

    public boolean hasItemClick() {
        return (positionTouch != -1);
    }

    public int getItemClickPos() {
        return positionTouch;
    }

    public int getItemClickColor() {
        return (positionTouch == -1) ? -1 : colorList[positionTouch];
    }

    public String getItemClick() {
        return (positionTouch == -1) ? null : mDataset[positionTouch];
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public void paired() {
        positionTouch = -1;
        notifyDataSetChanged();
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
