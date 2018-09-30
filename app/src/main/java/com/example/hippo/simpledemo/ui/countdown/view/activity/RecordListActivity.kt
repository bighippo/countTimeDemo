package com.example.hippo.simpledemo.ui.countdown.view.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.base.view.BaseMVPActivity
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel
import com.example.hippo.simpledemo.ui.countdown.presenter.RecordListPresenter
import com.example.hippo.simpledemo.ui.countdown.view.RecordListView
import com.example.hippo.simpledemo.ui.countdown.view.adapter.RecordAdapter
import com.example.hippo.simpledemo.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_record_list.*


class RecordListActivity : BaseMVPActivity<RecordListView, RecordListPresenter>(), RecordListView {
    private lateinit var mRecords: List<RecordModel>
    private lateinit var mAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        initData()
    }

    override fun createPresenter():  RecordListPresenter{
        return RecordListPresenter()
    }

    private fun initData () {
        getPresenter().getRecord()
    }

    override fun setRecord(record: List<RecordModel>) {
        mRecords = record

        initView()

        initListener()
    }

    private fun initView() {
        //insert data to this adapt to show record list
        mAdapter = RecordAdapter(mRecords, this)

        val layoutManager = LinearLayoutManager(this)

        rv_record.layoutManager = layoutManager

        rv_record.addItemDecoration(SpaceItemDecoration(30))

        rv_record.adapter = mAdapter
    }

    private fun initListener() {
        mAdapter.setOnRecyclerItemClick(object : RecordAdapter.OnRecyclerItemClick {
            override fun onItemClick(position: Int) {
                Toast.makeText(getContext(), "task is " + mRecords[position].taskName, Toast.LENGTH_SHORT).show()
            }
        })
    }

}