package com.example.hippo.simpledemo.cache;

import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;


public interface DeliveryCacheProviders {


    /**
     *LifeCache is not supplied, the data will be never evicted unless it is required explicitly using EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     *
     *
     * @param deliveryList
     *
     * @param offset
     *        DynamicKey is a wrapper around the key object for those providers which need to handle multiple records,
     *        so they need to provide multiple keys, such us endpoints with pagination, ordering or filtering requirements.
     *        To evict the data associated with one particular key use EvictDynamicKey.
     *
     * @param evictDynamicKey
     *        EvictDynamicKey allows to explicitly evict the data of an specific DynamicKey.
     *
     */
    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<List<DeliveryModel>> getDeliveryList(Observable<List<DeliveryModel>> deliveryList, DynamicKey offset, EvictDynamicKey evictDynamicKey);

}