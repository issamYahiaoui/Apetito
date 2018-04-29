package com.esi.tdm.apetito.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.esi.tdm.apetito.R
import com.esi.tdm.apetito.activities.DishInfoActivity
import com.esi.tdm.apetito.adapters.DishesAdapter
import com.esi.tdm.apetito.models.Dish
import com.esi.tdm.apetito.utlis.Utils
import kotlinx.android.synthetic.main.fragment_dish_infos.*


/**
 * A simple [Fragment] subclass.
 */
class PrincipaleDishesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater!!.inflate(R.layout.fragment_principale_dishes, container, false)
        var listView = view.findViewById<ListView>(R.id.principalDishes) as ListView
        var utils = Utils()
        var adapter = activity?.let { DishesAdapter(it,utils.populateDishes(8)) }
        listView.adapter = adapter

        listView.setOnItemClickListener{adapterView,view,i,l ->

            if (isTwoPan()){
                this!!.activity?.let { displayDetail(it,i) }
            }
            else{
                val intent = Intent(activity , DishInfoActivity::class.java)
                intent.putExtra("index",i)
                startActivity(intent)
            }
        }
        return view
    }

    fun isTwoPan() = activity?.findViewById<View>(R.id.fragment6) !=null
    fun displayDetail(_ctx: Context, i:Int){
        var list = mutableListOf<Dish>()
        var utils = Utils()
        list = utils.populateDishesEntries(_ctx) as MutableList<Dish>
        dishImage.setImageResource(list.get(i).listImage)
        dishPriceDetail.setText(list.get(i).price.toString())
        dishNameDetail.setText(list.get(i).name)
        dishDescription.setText(list.get(i).description)
    }

}// Required empty public constructor