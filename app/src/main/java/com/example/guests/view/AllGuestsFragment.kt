package com.example.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.databinding.FragmentAllBinding
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //RecyclerView
        // 1 - obter a recicler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
        // 2 - Definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        observer()


        allGuestsViewModel.load()

        return root
    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}