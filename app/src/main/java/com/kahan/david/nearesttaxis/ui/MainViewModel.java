package com.kahan.david.nearesttaxis.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kahan.david.nearesttaxis.model.StubTaxisRepository;
import com.kahan.david.nearesttaxis.model.Taxi;
import com.kahan.david.nearesttaxis.model.TaxisRepository;

import java.util.Collections;
import java.util.List;

/**
 * Created by david on 17/05/2018.
 */
public class MainViewModel extends ViewModel{

    private TaxisRepository mTaxisRepository;
    private MutableLiveData<List<Taxi>> taxis;

    public MainViewModel() {
        mTaxisRepository = StubTaxisRepository.getInstance();
    }

    public LiveData<List<Taxi>> getTaxis() {
        if (taxis == null){
            taxis = new MutableLiveData<>();
            loadTaxis();
        }
        return taxis;
    }

    public void loadTaxis() {
        mTaxisRepository.getTaxis(loadedTaxis -> {
            Collections.sort(loadedTaxis, (t1, t2) -> Integer.compare(t1.getEtaInMin(), t2.getEtaInMin()));
            taxis.setValue(loadedTaxis);
        });
    }
}
