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
//        List<Taxi> loadedTaxis = new ArrayList<>();
//        loadedTaxis.add(new Taxi(R.drawable.spurs, "Spurs"));
//        loadedTaxis.add(new Taxi(R.drawable.cavaliers, "Shekem"));
//        loadedTaxis.add(new Taxi(R.drawable.golden, "Habima"));
//        loadedTaxis.add(new Taxi(R.drawable.raptors, "Gordon"));
//        loadedTaxis.add(new Taxi(R.drawable.sixers, "Azrieli"));
//        loadedTaxis.add(new Taxi(R.drawable.rockets, "Hadera"));
//        loadedTaxis.add(new Taxi(R.drawable.knicks, "Apple"));
//        loadedTaxis.add(new Taxi(R.drawable.lakers, "Top10"));
//        loadedTaxis.add(new Taxi(R.drawable.blazers, "Tapuz"));
//        loadedTaxis.add(new Taxi(R.drawable.wizards, "Wizards"));
//        loadedTaxis.add(new Taxi(R.drawable.celtics, "Celtics"));
//        loadedTaxis.add(new Taxi(R.drawable.grizzlies, "Grizzlies"));
//        loadedTaxis.add(new Taxi(R.drawable.heat, "Heat"));
//        loadedTaxis.add(new Taxi(R.drawable.jazz, "Jazz"));
//        loadedTaxis.add(new Taxi(R.drawable.mavericks, "Mavericks"));
//        loadedTaxis.add(new Taxi(R.drawable.thunder, "Thunder"));
//        loadedTaxis.add(new Taxi(R.drawable.timberwolves, "Timberwolves"));
//        Collections.sort(loadedTaxis, (t1, t2) -> Integer.compare(t1.getEtaInMin(), t2.getEtaInMin()));
//        taxis.setValue(loadedTaxis);
    }
}
