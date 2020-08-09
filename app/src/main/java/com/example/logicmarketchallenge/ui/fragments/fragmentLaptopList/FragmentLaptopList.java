package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.logicmarketchallenge.databinding.FragmentLaptopListBinding;
import com.example.logicmarketchallenge.ui.viewmodels.ViewModelLaptop;
import java.util.List;
/*
 * Home de la app. Fragmento que muestra la lista de objetos Laptop
 */
public class FragmentLaptopList extends Fragment implements AdapterRecyclerViewLaptops.ProductListListener,
        SwipeRefreshLayout.OnRefreshListener {

    public static final String LAPTOP_POSITION = "laptop";
    private AdapterRecyclerViewLaptops adapter;
    private ViewModelLaptop viewModel;
    private FragmentLaptopListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLaptopListBinding.inflate(inflater, container, false);
        View fragmentListView = binding.getRoot();
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
                binding.progressBarProductList.setVisibility(integer);
            }
        };
        viewModel.progressBarIsShowing().observe(requireActivity(),observerProgressBar);

        final Observer<Boolean> observerErrorService = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean error) {
                if(error){
                    Toast.makeText(getContext(), getString(R.string.error_system), Toast.LENGTH_SHORT).show();
                }
            }
        };
        viewModel.isErrorService().observe(requireActivity(),observerErrorService);
    }

    private void initRecyclerViewProducts() {
        adapter = new AdapterRecyclerViewLaptops(this);
        binding.recyclerViewProductList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        binding.recyclerViewProductList.setLayoutManager(layoutManager);
    }

    private void initViews() {
        binding.progressBarProductList.setVisibility(View.VISIBLE);
        binding.swipeReflesh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        viewModel.getDataFromService();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.swipeReflesh.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void showProductDetail(Integer position) {
        Bundle bundle = new Bundle();
        bundle.putInt(LAPTOP_POSITION, position);
        Navigation.findNavController(this.binding.recyclerViewProductList).navigate(R.id.action_laptopList_to_productDetail, bundle);
    }
}