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
    private ArrayList<AuctionItem> myAuctions;
    private ArrayList<AucDisplayItem> optimizedList;

    public CompareAdapter(ArrayList<WowItem> items, Context context, int deal, ArrayList<AuctionItem> auctions) {
        myItems = items;
        mContext = context;
        dealRange = deal;
        myAuctions = auctions;
        optimizedList = new ArrayList<>();
        optimizeAuctions();
    }

    private void optimizeAuctions() {
        for (int i = 0; i < myAuctions.size(); i++) {
            for (int j = 0; j < myItems.size(); j++) {
                AuctionItem aucItem = myAuctions.get(i);
                WowItem wowItem = myItems.get(j);
                if (aucItem.getItem().equals(Integer.parseInt(wowItem.getId()))) {
                    optimizedList.add(new AucDisplayItem(wowItem.getLevel(), wowItem.getName_enus(), wowItem.getPriceavg(),
                            wowItem.getPriceNum(), wowItem.getQuality(), wowItem.getRequiredLevel(), Integer.toString(aucItem.getBuyout()),
                            Integer.toString(aucItem.getQuantity())));
                }
            }
        }

        for (int i = optimizedList.size() - 1; i >= 0; i--) {
            Log.d("TTT", "Name of auction: " + optimizedList.get(i).getName_enus());
            Log.d("TTT", "Price of item: " + optimizedList.get(i).getPriceavg());
            Log.d("TTT", "Price of item: " + optimizedList.get(i).getPriceNum());
            Log.d("TTT", "Buyout of item: " + optimizedList.get(i).getBuyout());
            Log.d("TTT", "Quantity of item: " + optimizedList.get(i).getQuantity());
            Log.d("TTT", "------------------------------------------------------------");
            float aucData = (Integer.parseInt(optimizedList.get(i).getBuyout()) * 1.0f) / (Integer.parseInt(optimizedList.get(i).getQuantity()) * 1.0f);
            float itemData = (Integer.parseInt(optimizedList.get(i).getPriceNum()) * 100 * 1.0f) * (dealRange * 1.0f / 100);

            if (aucData > itemData) {
                optimizedList.remove(optimizedList.get(i));
            }
        }

        for (int i = 0; i < optimizedList.size(); i++) {
            String copper = optimizedList.get(i).getBuyout().substring(optimizedList.get(i).getBuyout().length()-2);
            copper = copper + "c";
            if (optimizedList.get(i).getBuyout().length() > 2){
            String silver = optimizedList.get(i).getBuyout().substring(optimizedList.get(i).getBuyout().length()-4, optimizedList.get(i).getBuyout().length()-2);
            silver = silver + "s ";
                if(optimizedList.get(i).getBuyout().length() > 4) {
                    String gold = optimizedList.get(i).getBuyout().substring(0, optimizedList.get(i).getBuyout().length() - 4);
                    gold = gold + "g ";
                    optimizedList.get(i).setBuyout(gold + silver + copper);
                } else {
                    optimizedList.get(i).setBuyout(silver + copper);
                }} else {
                optimizedList.get(i).setBuyout(copper);
            }
        }
        Log.d("TTT", "Size of optimizedList: " + optimizedList.size());
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
