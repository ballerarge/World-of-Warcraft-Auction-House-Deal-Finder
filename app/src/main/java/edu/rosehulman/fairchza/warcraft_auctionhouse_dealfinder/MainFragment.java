package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by decramrj on 2/4/2017.
 */

public class MainFragment extends Fragment {

    // Figure out how to use this below. Will need it.
    private int dealRange = 0;

    private static final String ARG_ITEMS = "items";

    private ArrayList<WowItem> myItems = new ArrayList<>();

    public MainFragment() {
    }

    public static MainFragment newInstance(ArrayList<WowItem> myItems) {
        MainFragment fragment = new MainFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView dealRangeTextView = (TextView) view.findViewById(R.id.deal_range_display);
        Button editItems = (Button) view.findViewById(R.id.edit_items_button);
        Button editDeals = (Button) view.findViewById(R.id.edit_deal_button);
        Button showDeals = (Button) view.findViewById(R.id.show_deals_button);

        editDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.deal_builder_title);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_deal_change, null, false);
                builder.setView(view);
                final EditText captionEditText = (EditText) view.findViewById(R.id.dialog_deal_edit_text);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String caption = captionEditText.getText().toString();
                        dealRangeTextView.setText(getResources().getString(R.string.deal_display_default) + " " + caption);
                        dealRange = Integer.parseInt(caption);
                    }
                });

                builder.setNegativeButton(android.R.string.cancel, null);
                builder.create().show();
            }
        });

        editItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                MyItemsFragment fragment = MyItemsFragment.newInstance(myItems);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("mainFragment");
                ft.commit();
            }
        });


        return view;
    }
}
