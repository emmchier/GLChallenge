package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.logicmarketchallenge.R;

public class FragmentLaptopList extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentListView = inflater.inflate(R.layout.fragment_laptop_list, container, false);

        return fragmentListView;
    }
}