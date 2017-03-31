package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by decramrj on 2/12/2017.
 */
public class CompareAdapter extends RecyclerView.Adapter<CompareAdapter.ViewHolder>{

    private ArrayList<WowItem> myItems;
    private Context mContext;
    private int dealRange;
    private ArrayList<AucDisplayItem> auctionList;
    private DatabaseReference mAuctionRef;

    public CompareAdapter(ArrayList<WowItem> items, Context context, int deal) {
        myItems = items;
        mContext = context;
        dealRange = deal;
        auctionList = new ArrayList<>();

        mAuctionRef = FirebaseDatabase.getInstance().getReference("auctions");
        mAuctionRef.keepSynced(true);

        fillAuctionList();
    }

    private void fillAuctionList() {
        mAuctionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < myItems.size(); i++) {
                    String id = myItems.get(i).id;
                    int aucPrice = (int) dataSnapshot.child("14959").child(id).getValue();
                    String name = myItems.get(i).getName_enus();
                    String itemPrice = Integer.toString(myItems.get(i).getPrice());
                    String quality = myItems.get(i).getQualityName();
                    String itemLevel = Integer.toString(myItems.get(i).getLevel());
                    String reqLevel = Integer.toString(myItems.get(i).getRequiredLevel());

                    auctionList.add(new AucDisplayItem(itemLevel, name, itemPrice,
                            quality, reqLevel, Integer.toString(aucPrice)));
                }

                Log.d("AUCTIONITEMS", (dataSnapshot.child("14959").child("124442").getValue()).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("EEE", "The read failed: " + databaseError.getCode());
            }
        });
    }


    @Override
    public CompareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_view_deal, parent, false);
        return new CompareAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompareAdapter.ViewHolder holder, final int position) {
        final AucDisplayItem item = auctionList.get(position);

        holder.mTitleTextView.setText(item.getName_enus());
        holder.mLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_level), item.getLevel()));
        holder.mPriceTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_price), item.getPriceavg()));
        holder.mQualityTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_quality), item.getQuality()));
        holder.mRequiredLevelTextView.setText(String.format(mContext.getResources().getString(R.string.inserted_reqLevel), item.getRequiredlevel()));
        holder.mBuyoutPrice.setText(String.format(mContext.getResources().getString(R.string.inserted_buyout), item.getBuyout()));
    }

    @Override
    public int getItemCount() {

        return auctionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mLevelTextView;
        TextView mPriceTextView;
        TextView mQualityTextView;
        TextView mRequiredLevelTextView;
        TextView mBuyoutPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.deal_item_title);
            mLevelTextView = (TextView) itemView.findViewById(R.id.deal_item_level);
            mPriceTextView = (TextView) itemView.findViewById(R.id.deal_item_price);
            mQualityTextView = (TextView) itemView.findViewById(R.id.deal_item_quality);
            mRequiredLevelTextView = (TextView) itemView.findViewById(R.id.deal_item_reqLevel);
            mBuyoutPrice = (TextView) itemView.findViewById(R.id.deal_item_buyout_price);
        }
    }
}
