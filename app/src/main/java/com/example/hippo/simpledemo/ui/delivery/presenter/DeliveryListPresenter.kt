package com.example.hippo.simpledemo.ui.delivery.presenter

import android.content.Context
import com.example.hippo.simpledemo.cache.CacheProviders
import com.example.hippo.simpledemo.network.api.DeliveryApi
import com.example.hippo.simpledemo.network.base.ApiClient
import com.example.hippo.simpledemo.network.base.ApiErrorModel
import com.example.hippo.simpledemo.network.base.ApiResponse
import com.example.hippo.simpledemo.network.base.NetworkScheduler
import com.example.hippo.simpledemo.ui.base.presenter.BasePresenter
import com.example.hippo.simpledemo.ui.base.view.BaseView
import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel
import com.example.hippo.simpledemo.ui.delivery.view.DeliveryListView
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey

class DeliveryListPresenter : BasePresenter{
    private val TAG = "DeliveryListPresenter"

    private var mDeliveryApi: DeliveryApi = ApiClient.createApi(DeliveryApi::class.java)

    private lateinit var mDeliveryListView: DeliveryListView

    private var mContext: Context ?=null

    override fun attachView(view: BaseView) {
        mDeliveryListView = view as DeliveryListView
    }

    fun setContext(context:Context){
        mContext = context
    }

    fun getDeliveryList() {

        //set cache and get delivery list from api
        val deliveryList = mDeliveryApi.getDeliveryList(0)

        CacheProviders.deliveryCache.getDeliveryList(deliveryList, DynamicKey(0), EvictDynamicKey(false))
                .compose(NetworkScheduler.compose())
                .subscribe(object : ApiResponse<List<DeliveryModel>>(mContext!!) {
                    override fun success(data: List<DeliveryModel>) {
                        mDeliveryListView.onSuccess(data)
                    }

                    override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                        mDeliveryListView.onError(apiErrorModel.message)
                    }
        })
    }

}