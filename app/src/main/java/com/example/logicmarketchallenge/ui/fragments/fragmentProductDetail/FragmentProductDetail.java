package com.example.logicmarketchallenge.ui.fragments.fragmentProductDetail;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.example.logicmarketchallenge.R;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.databinding.FragmentProductDetailBinding;
import com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList.FragmentLaptopList;
import com.example.logicmarketchallenge.ui.viewmodels.ViewModelLaptop;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
/*
 * se muestra el detalle de cada ítem del Recyclerview
 */
public class FragmentProductDetail extends Fragment {

    private ViewModelLaptop viewModel;
    private Integer position;
    private FragmentProductDetailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View fragmentDetailView = binding.getRoot();
        return fragmentDetailView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        setBtnAddToCart();
    }

    private void getData() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            position = bundle.getInt(FragmentLaptopList.LAPTOP_POSITION);
            setupViewModel();
        }
    }

    private void setBtnAddToCart() {
        binding.constraitBtnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Agregaste " + viewModel.getLaptopsList().getValue().get(position).getTitle() + " a tu carrito",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(ViewModelLaptop.class);
        viewModel.getLaptopsList().observe(requireActivity(), new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptopList) {
                binding.textViewDetailTitle.setText(laptopList.get(position).getTitle());
                binding.textViewDetailDescription.setText(laptopList.get(position).getDescription());
                if(getActivity() != null){
                    Glide.with(getActivity()).load(laptopList.get(position).getImage())
                            .placeholder(R.drawable.ic_laptop)
                            .error(R.drawable.ic_laptop)
                            .into(binding.imageViewProduct);
                }
            }
        });
    }
}