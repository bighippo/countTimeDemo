package com.example.hippo.simpledemo.ui.countdown.view.activity

import android.os.Bundle
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.base.view.BaseMVPActivity
import com.example.hippo.simpledemo.ui.countdown.presenter.PersonalInfoPresenter
import com.example.hippo.simpledemo.ui.countdown.view.PersonalInfoView

class PersonInfoActivity : BaseMVPActivity<PersonalInfoView, PersonalInfoPresenter>(), PersonalInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_info)
    }

    override fun createPresenter():  PersonalInfoPresenter{
        return PersonalInfoPresenter()
    }
}