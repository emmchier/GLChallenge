package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import com.example.logicmarketchallenge.core.entities.Laptop;
import java.util.List;

public interface FragmentLaptopListContract {

    interface viewModel{
        void responseListLaptop(List<Laptop> laptopList);
        void responseErrorService();
    }

    interface interactor{
        void getProducts();
    }

}
