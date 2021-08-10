package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guests.viewmodel.GuestFormViewModel
import com.example.guests.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
    }
    private fun setListeners(){
        button_save.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_save){

        }
    }
}