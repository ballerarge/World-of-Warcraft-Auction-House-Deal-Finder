package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by decramrj on 2/12/2017.
 */
public class CompareAdapter extends RecyclerView.Adapter<CompareAdapter.ViewHolder>{

    private ArrayList<WowItem> myItems;
    private Context mContext;
    private int dealRange;
    private ArrayList<AucDisplayItem> optimizedList;

    public CompareAdapter(ArrayList<WowItem> items, Context context, int deal) {
        myItems = items;
        mContext = context;
        dealRange = deal;
        optimizedList = new ArrayList<>();
//        optimizeAuctions();
    }


    @Override
    public CompareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_view_deal, parent, false);
        return new CompareAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompareAdapter.ViewHolder holder, final int position) {
        final AucDisplayItem item = optimizedList.get(position);

        holder.mTitleTextView.setText(item.getName_enus());
        holder.mLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_level), item.getLevel()));
        holder.mPriceTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_price), item.getPriceavg()));
        holder.mQualityTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_quality), item.getQuality()));
        holder.mRequiredLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_reqLevel), item.getRequiredlevel()));
        holder.mBuyoutPrice.setText(String.format(mContext.getResources().getString(R.string.inserted_buyout), item.getBuyout()));
        holder.mQuantityTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_quantity), item.getQuantity()));
    }

    @Override
    public int getItemCount() {

        return optimizedList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mLevelTextView;
        TextView mPriceTextView;
        TextView mQualityTextView;
        TextView mRequiredLevelTextView;
        TextView mBuyoutPrice;
        TextView mQuantityTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.deal_item_title);
            mLevelTextView = (TextView) itemView.findViewById(R.id.deal_item_level);
            mPriceTextView = (TextView) itemView.findViewById(R.id.deal_item_price);
            mQualityTextView = (TextView) itemView.findViewById(R.id.deal_item_quality);
            mRequiredLevelTextView = (TextView) itemView.findViewById(R.id.deal_item_reqLevel);
            mBuyoutPrice = (TextView) itemView.findViewById(R.id.deal_item_buyout_price);
            mQuantityTextView = (TextView) itemView.findViewById(R.id.deal_item_quantity);
        }
    }
}
