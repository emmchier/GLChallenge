package com.example.logicmarketchallenge.ui.viewmodels;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList.FragmentLaptopListContract;
import com.example.logicmarketchallenge.ui.fragments.fragmentLaptopList.FragmentLaptopListInteractor;
import java.util.List;
/*
 * viewmodel que obtiene y guarda datos del servicio.
 */
public class ViewModelLaptop extends ViewModel implements FragmentLaptopListContract.viewModel {

    private FragmentLaptopListContract.interactor interactor;

    private MutableLiveData<List<Laptop>> laptopList;
    private MutableLiveData<Boolean> errorService;
    private MutableLiveData<Integer> progressBarShowing;

    public ViewModelLaptop() {
        interactor = new FragmentLaptopListInteractor(this);
        laptopList = new MutableLiveData<>();
        errorService = new MutableLiveData<>();
        progressBarShowing = new MutableLiveData<>();
        getDataFromService();
    }

    public void getDataFromService(){
        progressBarShowing.setValue(View.VISIBLE);
        interactor.getProducts();
    }

    public LiveData<Integer> progressBarIsShowing(){
        return progressBarShowing;
    }

    public LiveData<Boolean> isErrorService(){
        return errorService;
    }

    public LiveData<List<Laptop>> getLaptopsList(){
        return laptopList;
    }

    @Override
    public void responseListLaptop(List<Laptop> laptopList) {
        this.laptopList.postValue(laptopList);
        this.errorService.postValue(false);
        this.progressBarShowing.postValue(View.GONE);
    }

    @Override
    public void responseErrorService() {
        this.errorService.setValue(true);
        this.progressBarShowing.postValue(View.GONE);
    }
}
