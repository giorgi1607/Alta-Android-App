package giorgi.gt.altaapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import giorgi.gt.altaapp.DATABASE.database
import giorgi.gt.altaapp.DATABASE.product_dao
import giorgi.gt.altaapp.DATABASE.product_detailed_modal
import giorgi.gt.altaapp.DATABASE.product_table
import giorgi.gt.altaapp.paging.*
import giorgi.gt.altaapp.scraping.scrapping
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewmodall(application: Application) : AndroidViewModel(application) {


    var dao:product_dao
    var get_database_data:LiveData<List<product_table>>



    init {
        var database_dao=database.createdb(application.applicationContext).productdao()
        var repository=repo(database_dao)
        dao=repository.dao
        get_database_data=dao.getalldata()

    }

    companion object{

        var detailed_view=MutableLiveData<product_detailed_modal>()

        val product_checker=MutableLiveData<Boolean>()



    }

//    fun  get_item_by_title(title: String):LiveData<List<product_table>>  {
//
//
//
//    }























    fun search_product_paging( link:String)=Pager(
        PagingConfig(5,maxSize = 100,enablePlaceholders = false),
        pagingSourceFactory = {searchpagingsource(link)}




    ).flow



    fun get_kitchen_pageing()=Pager(
        PagingConfig(23,maxSize = 100,enablePlaceholders = false),
        pagingSourceFactory = {kitchenpagingsource()}
    ).flow

    fun get_split_paging()=Pager(
        PagingConfig(2,maxSize = 100,enablePlaceholders = false),
        pagingSourceFactory = {splitpagingsource()}
    ).flow

    fun smartphone_split_paging()=Pager(
        PagingConfig(13,maxSize = 100,enablePlaceholders = false),
        pagingSourceFactory = {smartphonepagingsoure()}
    ).flow

    fun notebook_category_handler()= Pager(config = PagingConfig(pageSize = 8,maxSize = 100,
        enablePlaceholders = false

    ),pagingSourceFactory = { notebookpagingsource() }



    ).flow



   fun get_product_by_title(title:String):MutableLiveData<product_table>{
       var data=MutableLiveData<product_table>()
       viewModelScope.launch (Dispatchers.IO){

          data.postValue( dao.get_data_by_title(title))
       }
       return data

   }






    @RequiresApi(Build.VERSION_CODES.N)

    fun get_product_detailed_view(url:String) :MutableLiveData<product_detailed_modal>{

        viewModelScope.launch(Dispatchers.IO) {

            detailed_view.postValue(scrapping.item_detailed_view(url))


        }
        return  detailed_view
        }











    fun getbanner_pager()=Pager(
        PagingConfig(pageSize = 8,maxSize = 100,enablePlaceholders = true)

        ,pagingSourceFactory =  {bannerpagingsource()}

        ).flow













    fun delete_everything_fromlocaldb(){
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete_everythin()


        }
    }
   fun delete_product( product_website_id:String){

       viewModelScope.launch(Dispatchers.IO) {
           dao.delete(product_website_id)
       }

   }

   fun checkitem_in_db(title:String):MutableLiveData<Boolean>{

       var checker=MutableLiveData<Boolean>()

       viewModelScope.launch(Dispatchers.IO) {

           checker.postValue(dao.check_item(title))
       }
   return checker
   }



    fun add_in_db(item:product_table){

        viewModelScope.launch(Dispatchers.IO) {
            dao.add_to_favourites(item)

        }
    }

















}