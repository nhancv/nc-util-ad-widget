package com.active.sunnypoint;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by NhanCao on 01-Sep-15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private String[] mDataset;

    public ProductAdapter(String[] myDataset) {
        mDataset = myDataset;
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
        GradientDrawable border = new GradientDrawable();
        border.setColor(0x00000000);
        border.setCornerRadius(5);
        border.setStroke(1, Utils.randomColor(0xFF));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            holder.viewProductItem.setBackgroundDrawable(border);
        } else {
            holder.viewProductItem.setBackground(border);
        }

        holder.mTextView.setText(mDataset[position]);
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
