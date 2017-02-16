package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by decramrj on 2/12/2017.
 */

public class SupportedItemsFragment extends Fragment {

    DatabaseReference mItemsRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemsRef = FirebaseDatabase.getInstance().getReference();
        mItemsRef.keepSynced(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.supported_items, container, false);
        final TextView supportedItems = (TextView) view.findViewById(R.id.supported_items_text);
        mItemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder sb = new StringBuilder();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    sb.append(eventSnapshot.child("name_enus").getValue());
                    sb.append("\n");
                }
                supportedItems.setText(sb.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("EEE", "The read failed: " + databaseError.getCode());
            }
        });

        return view;
    }

}
