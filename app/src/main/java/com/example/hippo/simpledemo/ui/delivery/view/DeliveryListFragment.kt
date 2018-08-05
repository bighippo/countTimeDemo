package com.example.hippo.simpledemo.ui.delivery.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel
import com.example.hippo.simpledemo.ui.delivery.presenter.DeliveryListPresenter
import com.example.hippo.simpledemo.ui.delivery.view.adapter.DeliveryAdapter
import com.example.hippo.simpledemo.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_delivery_list.*
import me.yokeyword.fragmentation.SupportFragment

class DeliveryListFragment :SupportFragment(){
    private lateinit var mDeliveryList : List<DeliveryModel>
    private lateinit var mAdapter: DeliveryAdapter

    private var mDeliveryListPresenter = DeliveryListPresenter()

    companion object {
        fun newInstance(): DeliveryListFragment{
            val args = Bundle()
            val fragment =  DeliveryListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    //the view attach in this fragment
    private var mDeliveryListView = object : DeliveryListView {
        override fun onError(error: String) {
            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        }

        override fun onSuccess(model: List<DeliveryModel>) {
            mDeliveryList = model

            initView()
        }
    }

    //load layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_delivery_list, container,false)
    }

    //load data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mDeliveryListPresenter.attachView(mDeliveryListView)
        mDeliveryListPresenter.setContext(activity as Context)

        initData()
    }

    private fun initData() {
        mDeliveryListPresenter.getDeliveryList()
    }

    private fun initView() {
        mAdapter = DeliveryAdapter(activity as Context, mDeliveryList)

        val layoutManager = LinearLayoutManager(activity)

        rv_delivery_list.layoutManager = layoutManager

        rv_delivery_list.addItemDecoration(SpaceItemDecoration(30))

        rv_delivery_list.adapter = mAdapter
    }
}