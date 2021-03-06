package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.databinding.FragmentAllBinding
import com.example.guests.service.constants.GuestConstants
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.GuestListener
import com.example.guests.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        val binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //RecyclerView
        // 1 - obter a recicler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
        // 2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                startActivity(intent.putExtras(bundle))
            }

            override fun onDelete(id: Int) {
                allGuestsViewModel.delete(id)
                allGuestsViewModel.load(GuestConstants.Companion.FILTER.EMPTY)
            }
        }
        mAdapter.attachListener(mListener)
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load(GuestConstants.Companion.FILTER.EMPTY)
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, {
            mAdapter.updateGuests(it)
        })
    }


}