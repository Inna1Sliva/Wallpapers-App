package com.example.firabesewallpapers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firabesewallpapers.R
import com.example.firabesewallpapers.adapter.WallpapersListAdapter
import com.example.firabesewallpapers.model.WallpapersModel
import com.example.firabesewallpapers.repository.FirebaseRepositiry
import com.example.firabesewallpapers.viewmodel.WallpapersViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), (WallpapersModel) -> Unit {

private val firebaseRepositiry = FirebaseRepositiry()
private var navController:NavController? = null
    private var wallpapersList: List<WallpapersModel> = ArrayList()
    private val wallpapersListAdapter:WallpapersListAdapter= WallpapersListAdapter(wallpapersList,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(maine_toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.title = "Крутые обои"
        navController = Navigation.findNavController(view)

        if (firebaseRepositiry.getUser() == null){
            navController!!.navigate(R.id.action_homeFragment_to_registerFragment)

        }
        wallpapers_list_view.setHasFixedSize(true)
        wallpapers_list_view.layoutManager = GridLayoutManager(context, 3)
        wallpapers_list_view.adapter=wallpapersListAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val wallpapersViewModel: WallpapersViewModel by viewModels()
        wallpapersViewModel.getWallpapersList().observe(viewLifecycleOwner, Observer {
            wallpapersList = it
            wallpapersListAdapter.wallpapersList = wallpapersList
            wallpapersListAdapter.notifyDataSetChanged()
        })
    }
    override fun invoke(wallpaper: WallpapersModel) {
    }
}