package com.example.poultry_i.fragment

import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poultry_i.R
import com.example.poultry_i.adapter.DataListAdapter
import com.example.poultry_i.common.Utils
import com.example.poultry_i.model.DataBatch
import com.example.poultry_i.model.DataMachine
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var datalistAdapter: DataListAdapter
lateinit var btn_saveetails: AppCompatButton
lateinit var rv_detailsdata: RecyclerView
lateinit var ll_dataform: LinearLayout
lateinit var btn_submitdetails: AppCompatButton
lateinit var sub_data_table: LinearLayout
lateinit var tbrow: TableRow

lateinit var button_addc: FloatingActionButton
lateinit var et_nuofchicks: EditText
lateinit var et_flockno: EditText
lateinit var et_dateofplacement: EditText
lateinit var et_pno: EditText
lateinit var temp_data_Birds: LinearLayout
lateinit var tv_birds: TextView
lateinit var nuofchicks: TextView
lateinit var flock: TextView
lateinit var dateof: TextView
lateinit var psno: TextView
lateinit var no_data: TextView



private val batchList = ArrayList<DataBatch>()


class DetailsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    internal lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Batch List"
        initView(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {

        button_addc = view.findViewById(R.id.button_addc)
        nuofchicks = view.findViewById(R.id.nuofchicks)
        flock = view.findViewById(R.id.flock)
        dateof = view.findViewById(R.id.dateof)
        psno = view.findViewById(R.id.psno)
        no_data = view.findViewById(R.id.no_data)
        temp_data_Birds = view.findViewById(R.id.temp_data_Birds)
        tv_birds = view.findViewById(R.id.tv_birds)

        btn_submitdetails = view.findViewById(R.id.btn_submitdetails)
        btn_saveetails = view.findViewById(R.id.btn_saveetails)
        rv_detailsdata = view.findViewById(R.id.rv_detailsdata)
        ll_dataform = view.findViewById(R.id.ll_dataform)
        sub_data_table = view.findViewById(R.id.sub_data_table)
        et_nuofchicks = view.findViewById(R.id.et_nuofchicks)
        et_flockno = view.findViewById(R.id.et_flockno)
        et_dateofplacement = view.findViewById(R.id.et_dateofplacement)
        et_pno = view.findViewById(R.id.et_pno)
        tbrow = view.findViewById(R.id.tbrow)

        if(batchList.size != 0){
            datalistAdapter = DataListAdapter(batchList)
            val layoutManager = LinearLayoutManager(context)
            rv_detailsdata.layoutManager = layoutManager
            rv_detailsdata.itemAnimator = DefaultItemAnimator()
            rv_detailsdata.adapter = datalistAdapter
            datalistAdapter.notifyDataSetChanged()
        }else{
            no_data.visibility = View.VISIBLE
        }


        button_addc.setOnClickListener(View.OnClickListener {
            rv_detailsdata.visibility = View.GONE
            btn_saveetails.visibility = View.VISIBLE
            ll_dataform.visibility = View.VISIBLE
            button_addc.visibility = View.GONE
            no_data.visibility = View.GONE

        })




        btn_saveetails.setOnClickListener(View.OnClickListener {
            ll_dataform.visibility = View.GONE
            nuofchicks.setText(et_nuofchicks.text.toString())
            flock.setText(et_flockno.text.toString())
            dateof.setText(et_dateofplacement.text.toString())
            psno.setText(et_pno.text.toString())

            val datalist = DataBatch(et_nuofchicks.text.toString(),
                et_flockno.text.toString(),
                et_dateofplacement.text.toString(),
                et_pno.text.toString())

            batchList.add(datalist)
            
            tv_birds.visibility = View.VISIBLE
            temp_data_Birds.visibility = View.VISIBLE
            sub_data_table.visibility = View.VISIBLE

            btn_saveetails.visibility = View.GONE
            btn_submitdetails.visibility = View.VISIBLE

//            datalistAdapter = DataListAdapter(batchList)
//            val layoutManager = LinearLayoutManager(context)
//            rv_detailsdata.layoutManager = layoutManager
//            rv_detailsdata.itemAnimator = DefaultItemAnimator()
//            rv_detailsdata.adapter = datalistAdapter
//            datalistAdapter.notifyDataSetChanged()


//            Utils.showDialog1(
//                "Do you want to add Table Row?",
//                DialogInterface.OnClickListener { dialog, which ->
//                    when (which) {
//                        DialogInterface.BUTTON_POSITIVE -> {
//                            val myLayout = view.findViewById(R.id.ll_dataform) as LinearLayout
//                            val newLayout: View =
//                                layoutInflater.inflate(R.layout.subdata_details_table, null, false)
//                            myLayout.addView(newLayout)
//                            dialog.dismiss()
//                        }
//
//                    }
//                }, requireContext()
//            )

        })

        btn_submitdetails.setOnClickListener(View.OnClickListener {
            val myLayout = view.findViewById(R.id.sub_data_table) as LinearLayout
            val newLayout: View =
                layoutInflater.inflate(R.layout.subdata_details_table, null, false)
            myLayout.addView(newLayout)

        })


//        ll_dataform.visibility = View.GONE
//
//        btn_adddetails.setOnClickListener(View.OnClickListener {
//
//            et_title.setText("")
//            et_description.setText("")
//            et_machinnumber.setText("")
//            rv_detailsdata.visibility = View.GONE
//            btn_adddetails.visibility = View.GONE
//            btn_submitdetails.visibility = View.VISIBLE
//            ll_dataform.visibility = View.VISIBLE
//
//        })
//
//
//        btn_submitdetails.setOnClickListener(View.OnClickListener {
//
//            val datalist = DataMachine(et_title.text.toString(), et_description.text.toString(), et_machinnumber.text.toString())
//            batchList.add(datalist)
//
//            rv_detailsdata.visibility = View.VISIBLE
//            btn_adddetails.visibility = View.VISIBLE
//            btn_submitdetails.visibility = View.GONE
//            ll_dataform.visibility = View.GONE
//
//
//            datalistAdapter = DataListAdapter(batchList)
//            val layoutManager = LinearLayoutManager(context)
//            rv_detailsdata.layoutManager = layoutManager
//            rv_detailsdata.itemAnimator = DefaultItemAnimator()
//            rv_detailsdata.adapter = datalistAdapter
//            datalistAdapter.notifyDataSetChanged()
//
//
//
//        })
//
//
//    }
    }
}


