package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import com.example.logicmarketchallenge.core.entities.Laptop;
import java.util.List;
/*
 * contrato compartido entre viewmodel e interactor
 */
public interface FragmentLaptopListContract {

    interface viewModel{
        void responseListLaptop(List<Laptop> laptopList);
        void responseErrorService();
    }

    interface interactor{
        void getProducts();
    }
}
