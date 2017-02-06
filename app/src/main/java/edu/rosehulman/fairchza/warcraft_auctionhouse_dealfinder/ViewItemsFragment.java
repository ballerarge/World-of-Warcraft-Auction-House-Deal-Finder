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
 * Created by decramrj on 2/5/2017.
 */
public class ViewItemsFragment extends Fragment {

    private static final String ARG_ITEMS = "items";
    private RecyclerView mRecycler;
    private ViewItemsAdapter mAdapter;
    private ArrayList<WowItem> myItems;

    public static ViewItemsFragment newInstance(ArrayList<WowItem> myItems) {
        ViewItemsFragment fragment = new ViewItemsFragment();
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
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.view_items_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ViewItemsAdapter(myItems, getContext(), getActivity());
        mRecycler.setAdapter(mAdapter);

        return view;
    }


}
