package com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.core.service.RetrofitService;
import com.example.logicmarketchallenge.core.service.Service;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLaptopListInteractor implements FragmentLaptopListContract.interactor{

    private FragmentLaptopListContract.viewModel viewModel;
    private Service service;

    public FragmentLaptopListInteractor(FragmentLaptopListContract.viewModel viewModel) {
        service = RetrofitService.getRetrofitInstance().create(Service.class);
        this.viewModel = viewModel;
    }

    public void getProducts() {
        final Call<List<Laptop>> laptopsList = service.getProductList();
        laptopsList.enqueue(new Callback<List<Laptop>>() {
            @Override
            public void onResponse(Call<List<Laptop>> call, Response<List<Laptop>> response) {
                if(response.isSuccessful()){
                    viewModel.responseListLaptop(response.body());
                } else {
                    viewModel.responseErrorService();
                }
            }

            @Override
            public void onFailure(Call<List<Laptop>> call, Throwable t) {
                viewModel.responseErrorService();
            }
        });
    }
}
