package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by decramrj on 2/4/2017.
 */

public class MyItemsFragment extends Fragment {

    private static final String ARG_ITEMS = "items";

    private ArrayList<WowItem> myItems;
    private DatabaseReference mItemRef;
    private Query myItemRef;
    //private WowItem newItem;

    public MyItemsFragment() {
    }

    public static MyItemsFragment newInstance(ArrayList<WowItem> myItems) {
        MyItemsFragment fragment = new MyItemsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_ITEMS, myItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myItems = getArguments().getParcelableArrayList(ARG_ITEMS);
        }

        mItemRef = FirebaseDatabase.getInstance().getReference();
        mItemRef.keepSynced(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_items, container, false);
        Button basicItemSearch = (Button) view.findViewById(R.id.basic_item_button);
        Button viewMyItems = (Button) view.findViewById(R.id.view_items_button);
        ImageButton supportedItems = (ImageButton) view.findViewById(R.id.supported_items_button);

        supportedItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                SupportedItemsFragment fragment = new SupportedItemsFragment();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("myItems");
                ft.commit();
            }
        });

        viewMyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ViewItemsFragment fragment = ViewItemsFragment.newInstance(myItems);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("myItems");
                ft.commit();
            }
        });

        basicItemSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.add_item_prompt);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_item, null, false);
                builder.setView(view);
                final EditText itemEditText = (EditText) view.findViewById(R.id.dialog_add_item_text);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String itemName = itemEditText.getText().toString();
                        myItemRef = mItemRef.orderByValue();
                        myItemRef.keepSynced(true);
                        myItemRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                System.out.println("HERE");
                                int currSize = myItems.size();
                                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                                    if (eventSnapshot.child("name_enus").getValue().equals(itemName)) {
                                        myItems.add(0, eventSnapshot.getValue(WowItem.class));
                                    }
                                }
                                if (currSize == myItems.size()) {
                                    Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("EEE", "The read failed: " + databaseError.getCode());
                            }
                        });
                    }
                });

                builder.setNegativeButton(android.R.string.cancel, null);

                builder.create().show();
            }
        });

        return view;
    }
}
