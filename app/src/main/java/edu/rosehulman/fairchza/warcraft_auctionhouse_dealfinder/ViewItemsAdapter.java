package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by decramrj on 2/5/2017.
 */
public class ViewItemsAdapter extends RecyclerView.Adapter<ViewItemsAdapter.ViewHolder> {

    private ArrayList<WowItem> myItems;
    private Context mContext;
    private Activity mActivity;

    public ViewItemsAdapter(ArrayList<WowItem> items, Context context, Activity activity) {
        myItems = items;
        mContext = context;
        mActivity = activity;
    }

    @Override
    public ViewItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewItemsAdapter.ViewHolder holder, final int position) {
        final WowItem item = myItems.get(position);
        holder.mTitleTextView.setText(item.getName_enus());
        holder.mLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_level), item.getLevel()));
        holder.mPriceTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_price), item.getPriceavg()));
        holder.mQualityTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_quality), item.getQuality()));
        holder.mRequiredLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_reqLevel), item.getRequiredLevel()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.remove_dialog_option);
                View view = mActivity.getLayoutInflater().inflate(R.layout.dialog_delete, null, false);
                builder.setView(view);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myItems.remove(position);
                        notifyItemRangeChanged(position, myItems.size());
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton(android.R.string.cancel, null);

                builder.create().show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mLevelTextView;
        TextView mPriceTextView;
        TextView mQualityTextView;
        TextView mRequiredLevelTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.item_title);
            mLevelTextView = (TextView) itemView.findViewById(R.id.item_level);
            mPriceTextView = (TextView) itemView.findViewById(R.id.item_price);
            mQualityTextView = (TextView) itemView.findViewById(R.id.item_quality);
            mRequiredLevelTextView = (TextView) itemView.findViewById(R.id.item_reqLevel);
        }
    }
}
