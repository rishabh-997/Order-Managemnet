package com.example.ordermanagement.HomeActivity.MVP.Prepared;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ordermanagement.HomeActivity.MVP.AdapterForOrder.AdapterForOrder;
import com.example.ordermanagement.HomeActivity.Model.ClientList;
import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreparedFragment extends Fragment implements PreparedContract.view
{
    PreparedContract.presenter presenter;
    AdapterForOrder adapterForOrder;

    RecyclerView recyclerView;
    List<ClientList> lists=new ArrayList<>();

    ProgressBar progressBar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PreparedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_cancelled, container, false);
        presenter=new PreparedPresenter(this);
        ButterKnife.bind(getContext(),view);

        recyclerView=view.findViewById(R.id.orderlist_recycler);
        progressBar=view.findViewById(R.id.order_bar);
        presenter.getOrders(getContext());
        return view;
    }


    @Override
    public void showOrder(OrderListResponse body)
    {
        progressBar.setVisibility(View.GONE);
        lists=body.getClient_list();
        adapterForOrder=new AdapterForOrder(lists,getContext());
        adapterForOrder.setname("Prepared");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterForOrder);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public static PreparedFragment newInstance(String param1, String param2) {
        PreparedFragment fragment = new PreparedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
