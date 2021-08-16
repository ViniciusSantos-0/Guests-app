package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.databinding.FragmentAbsentBinding
import com.example.guests.databinding.FragmentPresentBinding
import com.example.guests.service.constants.GuestConstants
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.GuestListener
import com.example.guests.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private lateinit var absentViewModel: GuestsViewModel
    private var _binding: FragmentAbsentBinding? = null
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        absentViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //RecyclerView
        // 1 - obter a recicler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_absent)
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
                absentViewModel.delete(id)
                absentViewModel.load(GuestConstants.Companion.FILTER.ABSENT)
            }
        }
        mAdapter.attachListener(mListener)
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        absentViewModel.load(GuestConstants.Companion.FILTER.ABSENT)
    }

    private fun observer() {
        absentViewModel.guestList.observe(viewLifecycleOwner, {
            mAdapter.updateGuests(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}