package com.example.logicmarketchallenge.ui.fragments.fragmentProductDetail;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.logicmarketchallenge.R;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList.FragmentLaptopList;
import com.example.logicmarketchallenge.ui.viewmodels.ViewModelLaptop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentProductDetail extends Fragment {

    @BindView(R.id.imageViewProduct)
    ImageView imageViewProduct;

    @BindView(R.id.textViewDetailTitle)
    TextView textViewDetailTitle;

    @BindView(R.id.textViewDetailDescription)
    TextView textViewDetailDescription;

    private ViewModelLaptop viewModel;
    private Integer posicion;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentDetailView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, fragmentDetailView);

        return fragmentDetailView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            posicion = bundle.getInt(FragmentLaptopList.LAPTOP_POSITION);
            setupViewModel();
        }
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(ViewModelLaptop.class);

        viewModel.getLaptopsList().observe(requireActivity(), new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptopList) {
                textViewDetailTitle.setText(laptopList.get(posicion).getTitle());
                textViewDetailDescription.setText(laptopList.get(posicion).getDescription());
                if(getActivity() != null){
                    Glide.with(getActivity()).load(laptopList.get(posicion).getImage())
                            .placeholder(R.drawable.ic_laptop)
                            .error(R.drawable.ic_laptop)
                            .into(imageViewProduct);
                }
            }
        });
    }
}