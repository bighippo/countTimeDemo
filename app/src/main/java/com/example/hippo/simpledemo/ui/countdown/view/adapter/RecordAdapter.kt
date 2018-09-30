package com.example.hippo.simpledemo.ui.countdown.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel
import kotlinx.android.synthetic.main.item_record.view.*

class RecordAdapter(modelList: List<RecordModel>, context: Context) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    private var mContext : Context = context

    private var mOnRecyclerItemClick: OnRecyclerItemClick? = null

    private var mData : List<RecordModel> = modelList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_record, parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView!!){
            //set the start time
            tv_start.text = "START TIME: " + mData[position].start

            tv_task.text = mData[position].taskName

            rl_root.setOnClickListener{
                if (mOnRecyclerItemClick != null)
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