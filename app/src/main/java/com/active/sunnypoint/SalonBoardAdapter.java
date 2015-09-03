package com.active.sunnypoint;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NhanCao on 01-Sep-15.
 */
public class SalonBoardAdapter extends RecyclerView.Adapter<SalonBoardAdapter.ViewHolder> {
    private String[] mDataset;

    public SalonBoardAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SalonBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_salonboard, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtView.setText(mDataset[position]);


    }

    @Override
    public int getItemCount() {
        return mDataset.length;
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
