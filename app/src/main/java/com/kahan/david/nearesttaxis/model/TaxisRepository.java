package com.kahan.david.nearesttaxis.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by david on 19/05/2018.
 */
public interface TaxisRepository {

    interface LoadTaxisCallback {
        void onTaxisLoaded(List<Taxi> loadedTaxis);
    }

    void getTaxis(@NonNull LoadTaxisCallback callback);

//    void refreshData();
}
