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
import com.example.hippo.simpledemo.util.AppConstants
import com.example.hippo.simpledemo.util.NetworkUtil
import com.example.hippo.simpledemo.util.SharedPreferencesUtil
import com.example.hippo.simpledemo.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_delivery_list.*
import kotlinx.android.synthetic.main.top_bar.*
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

            //set cache available flag
            SharedPreferencesUtil.saveData(activity, AppConstants.CACHE_AVAILABLE,true)

            initView()
        }
    }

    //load layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_delivery_list, container,false)
    }

    //load data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(NetworkUtil.isNetworkAvailable(activity)) {
            init()
        } else {
            if (SharedPreferencesUtil.getData(activity, AppConstants.CACHE_AVAILABLE, false) as Boolean)
                init()

            else
                showNoNet()
        }
    }

    private fun hideNoNet(){
        rv_net_unavailable.visibility = View.GONE
        top_bar.visibility = View.VISIBLE
    }


    private fun showNoNet(){
        top_bar.visibility = View.GONE
        btn_reload.setOnClickListener{
            if(NetworkUtil.isNetworkAvailable(activity)) {
               init()
            } else {
                Toast.makeText(activity, R.string.net_unavailable, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        hideNoNet()

        mDeliveryListPresenter.attachView(mDeliveryListView)
        mDeliveryListPresenter.setContext(activity as Context)

        initData()
    }

    private fun initData() {
        mDeliveryListPresenter.getDeliveryList()
    }

    private fun initView() {
        initTitle()

        //insert data to this adapt to show delivery list
        mAdapter = DeliveryAdapter(mDeliveryList)

        val layoutManager = LinearLayoutManager(activity)

        rv_delivery_list.layoutManager = layoutManager

        rv_delivery_list.addItemDecoration(SpaceItemDecoration(30))

        rv_delivery_list.adapter = mAdapter


        //set list item click listener
        recycleItemClickListener()
    }


    private fun initTitle() {
        iv_back.visibility = View.GONE

        tv_title.setText(R.string.delivery_list_title)
    }


    private fun recycleItemClickListener() {
        mAdapter.setOnRecyclerItemClick(object : DeliveryAdapter.OnRecyclerItemClick {
            override fun onItemClick(position: Int) {
                start(DeliveryDetailFragment.newInstance(mDeliveryList[position]))
            }
        })
    }
}