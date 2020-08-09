package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.logicmarketchallenge.R;
import com.example.logicmarketchallenge.adapters.AdapterRecyclerViewLaptops;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.ui.viewmodels.ViewModelLaptop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentLaptopList extends Fragment implements AdapterRecyclerViewLaptops.ProductListListener,
        SwipeRefreshLayout.OnRefreshListener {

    public static final String LAPTOP_POSITION = "laptop";

    @BindView(R.id.recyclerViewProductList)
    RecyclerView recyclerViewProductList;

    @BindView(R.id.progressBarProductList)
    ProgressBar progressBarProductList;

    @BindView(R.id.swipeReflesh)
    SwipeRefreshLayout swipeReflesh;

    private AdapterRecyclerViewLaptops adapter;
    private ViewModelLaptop viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentListView = inflater.inflate(R.layout.fragment_laptop_list, container, false);
        ButterKnife.bind(this, fragmentListView);
        initViews();
        initRecyclerViewProducts();
        setUpViewModel();

        return fragmentListView;
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(ViewModelLaptop.class);

        final Observer<List<Laptop>> observerLaptops = new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptopList) {
                adapter.loadProductList(laptopList);
            }
        };

        viewModel.getLaptopsList().observe(requireActivity(),observerLaptops);

        final Observer<Integer> observerProgressBar = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBarProductList.setVisibility(integer);
            }
        };
        viewModel.progressBarIsShowing().observe(requireActivity(),observerProgressBar);

        final Observer<Boolean> observerErrorService = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean error) {
                if(error){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        };
        viewModel.isErrorService().observe(requireActivity(),observerErrorService);

    }

    private void initRecyclerViewProducts() {
        adapter = new AdapterRecyclerViewLaptops(this);
        recyclerViewProductList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerViewProductList.setLayoutManager(layoutManager);
    }

    private void initViews() {
        progressBarProductList.setVisibility(View.VISIBLE);
        swipeReflesh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        viewModel.getDataFromService();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeReflesh.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void showProductDetail(Integer posicion) {
        Bundle bundle = new Bundle();
        bundle.putInt(LAPTOP_POSITION, posicion);
        Navigation.findNavController(this.recyclerViewProductList).navigate(R.id.action_laptopList_to_productDetail, bundle);
    }
}