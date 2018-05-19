package com.kahan.david.nearesttaxis.model;

import android.support.annotation.NonNull;

import com.kahan.david.nearesttaxis.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by david on 19/05/2018.
 */
public class StubTaxisRepository implements TaxisRepository {

    private List<Taxi> taxis = new ArrayList<>();

    private static StubTaxisRepository repository = null;

    private StubTaxisRepository() {
        taxis.add(new Taxi(R.drawable.spurs, "Spurs"));
        taxis.add(new Taxi(R.drawable.cavaliers, "Shekem"));
        taxis.add(new Taxi(R.drawable.golden, "Habima"));
        taxis.add(new Taxi(R.drawable.raptors, "Gordon"));
        taxis.add(new Taxi(R.drawable.sixers, "Azrieli"));
        taxis.add(new Taxi(R.drawable.rockets, "Hadera"));
        taxis.add(new Taxi(R.drawable.knicks, "Apple"));
        taxis.add(new Taxi(R.drawable.lakers, "Top10"));
        taxis.add(new Taxi(R.drawable.blazers, "Tapuz"));
        taxis.add(new Taxi(R.drawable.wizards, "Wizards"));
        taxis.add(new Taxi(R.drawable.celtics, "Celtics"));
        taxis.add(new Taxi(R.drawable.grizzlies, "Grizzlies"));
        taxis.add(new Taxi(R.drawable.heat, "Heat"));
        taxis.add(new Taxi(R.drawable.jazz, "Jazz"));
        taxis.add(new Taxi(R.drawable.mavericks, "Mavericks"));
        taxis.add(new Taxi(R.drawable.thunder, "Thunder"));
        taxis.add(new Taxi(R.drawable.timberwolves, "Timberwolves"));
    }

    public synchronized static TaxisRepository getInstance() {
        if (repository == null) {
            repository = new StubTaxisRepository();
        }
        return repository;
    }

    @Override
    public void getTaxis(@NonNull LoadTaxisCallback callback) {
        for (Taxi taxi : taxis) {
            taxi.setEtaInMin(new Random().nextInt(120) + 1);
        }
        callback.onTaxisLoaded(taxis);
    }

//    @Override
//    public void refreshData() {
//
//    }
}
