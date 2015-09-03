package com.active.sunnypoint;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.active.sunnypoint.dagger.ControlBus;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NhanCao on 01-Sep-15.
 */
public class SalonBoardAdapter extends RecyclerView.Adapter<SalonBoardAdapter.ViewHolder> {
    Bus bus;
    private String TAG = SalonBoardAdapter.class.getName();
    private String[] mDataset;
    private Integer[] colorList;
    private int positionTouch = -1;
    private int productTouchColor = -1;
    private Integer[] mapping;

    public SalonBoardAdapter(String[] myDataset, Bus bus, Integer[] mapping) {
        mDataset = myDataset;
        this.bus = bus;
        this.mapping = mapping;
        initialColorList();
    }

    private void initialColorList() {
        //generate color list
        colorList = new Integer[getItemCount()];
        for (int i = 0; i < colorList.length; i++) {
            colorList[i] = Utils.COLORDEFAULT;
        }
    }

    public int getProductTouchColor() {
        return productTouchColor;
    }

    public void setProductTouchColor(int productTouchColor) {
        this.productTouchColor = productTouchColor;
        notifyDataSetChanged();
    }

    @Override
    public SalonBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_salonboard, parent, false);
        return new ViewHolder(v);
    }

    private boolean checkPaired(int position) {
        for (int i = 0; i < mapping.length; i++) {
            if (mapping[i] == position) return true;
        }
        return false;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btnSave.setEnabled(false);
        if (position != positionTouch) {
            if (checkPaired(position)) {
                Utils.setBackground(holder.viewSalonBoardItem, colorList[position], colorList[position]);
            } else {
                Utils.setBackground(holder.viewSalonBoardItem, colorList[position]);
            }
        } else {
            if (productTouchColor == -1) {
                Utils.setBackground(holder.viewSalonBoardItem, colorList[positionTouch], 0xFF6699FF);
            } else {
                Utils.setBackground(holder.viewSalonBoardItem, productTouchColor, 0xFF6699FF);
                holder.btnSave.setEnabled(true);
            }
        }

        holder.txtView.setText(mDataset[position]);
        holder.viewSalonBoardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick(v, position);
                bus.post(new ControlBus(SalonBoardAdapter.this, position));
            }
        });
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new ControlBus(SalonBoardAdapter.this, -2)); //save command
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

    public void updateColorList(int color) {
        for (int i = 0; i < getItemCount(); i++) {
            if (colorList[i] == color) colorList[i] = Utils.COLORDEFAULT;
        }
        colorList[getItemClickPos()] = color;
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public void paired() {
        positionTouch = -1;
        productTouchColor = -1;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.viewSalonBoardItem)
        View viewSalonBoardItem;
        @Bind(R.id.btnSave)
        Button btnSave;
        @Bind(R.id.txtView)
        TextView txtView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
