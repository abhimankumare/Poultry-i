package com.example.poultry_i.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.poultry_i.R
import com.example.poultry_i.activity.MainActivity
import com.example.poultry_i.model.DataBatch
import com.example.poultry_i.model.DataMachine


internal class DataListAdapter(
    private var dataList: List<DataBatch>
) :
    RecyclerView.Adapter<DataListAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

       //
        var nuofchicks: TextView = view.findViewById(R.id.nuofchicks)
        var flock: TextView = view.findViewById(R.id.flock)
        var dateof: TextView = view.findViewById(R.id.dateof)
        var psno: TextView = view.findViewById(R.id.psno)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_details_data, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //if(dataList.size != 0 ){
           // holder.no_data.visibility = View.GONE
            val data = dataList[position]
            holder.nuofchicks.text = data.nuofchicks
            holder.flock.text = data.flock
            holder.dateof.text = data.dateof
            holder.psno.text = data.psno




    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}