package com.example.firabesewallpapers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firabesewallpapers.model.WallpapersModel
import com.example.firabesewallpapers.repository.FirebaseRepositiry

class WallpapersViewModel: ViewModel() {

    private var firebaseRepositiry:FirebaseRepositiry = FirebaseRepositiry()
    private val wallpapersList:MutableLiveData<List<WallpapersModel>> by lazy {
        MutableLiveData<List<WallpapersModel>>().also {
            loadWallpapersData()
        }
    }

    fun getWallpapersList():LiveData<List<WallpapersModel>>{
        return wallpapersList
    }

    fun loadWallpapersData(){
        firebaseRepositiry.queryWallpapers().addOnCompleteListener {
            if (it.isSuccessful){
                val result = it.result
                if (result!!.isEmpty){
                }else{
            wallpapersList.value= result.toObjects(WallpapersModel::class.java)
                }
            }else{
                Log.d("VIEW_MODEL_LOG", "Error: ${it.exception!!.message}")
            }
        }
    }
}