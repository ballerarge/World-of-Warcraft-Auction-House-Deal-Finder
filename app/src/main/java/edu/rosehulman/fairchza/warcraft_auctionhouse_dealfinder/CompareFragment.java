package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by decramrj on 2/12/2017.
 */

public class CompareFragment extends Fragment {

    private static final String ARG_ITEMS = "items";
    private static final String ARG_DEAL = "deal";
    private int deal;
    private ArrayList<WowItem> myItems;
    private RecyclerView mRecycler;
    private CompareAdapter mAdapter;

    public static CompareFragment newInstance(ArrayList<WowItem> myItems, int dealRange) {
        CompareFragment fragment = new CompareFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_ITEMS, myItems);
        args.putInt(ARG_DEAL, dealRange);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myItems = getArguments().getParcelableArrayList(ARG_ITEMS);
            deal = getArguments().getInt(ARG_DEAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compare, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.compare_items_recycler);
        mAdapter = new CompareAdapter(myItems, getContext(), deal);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
