package com.example.logicmarketchallenge.ui.fragments.fragmentProductDetail;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.logicmarketchallenge.R;

public class FragmentProductDetail extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentDetailView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        return fragmentDetailView;
    }
}