package com.example.hippo.simpledemo.ui.delivery.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel

import kotlinx.android.synthetic.main.item_delivery.view.*

class DeliveryAdapter: RecyclerView.Adapter<DeliveryAdapter.ViewHolder>{
    var mContext : Context

    private var mOnRecyclerItemClick: OnRecyclerItemClick? = null

    private var mData : List<DeliveryModel>

    constructor(context: Context, modelList: List<DeliveryModel>) {
        mContext = context
        mData = modelList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(parent.context, R.layout.item_delivery, null))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView!!){
            //set the delivery pic
            Glide.with(mContext)
                    .load(mData[position].imageUrl)
                    //.placeholder(R.drawable.placeholder)
                    //.error()
                    .into(iv_delivery)

            //set the delivery description
            tv_delivery_description.text = mData[position].description

            //set the delivery location
            tv_delivery_location.text = mData[position].location.address

            //set item click listener
            rl_root.setOnClickListener{
                if(mOnRecyclerItemClick != null)
                    mOnRecyclerItemClick!!.onItemClick(position)
            }
        }
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item)

    interface OnRecyclerItemClick {
        fun onItemClick(position: Int)
    }

    fun setOnRecyclerItemClick(mOnRecyclerItemClick: OnRecyclerItemClick) {
        this.mOnRecyclerItemClick = mOnRecyclerItemClick
    }
}