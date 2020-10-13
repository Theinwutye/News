package com.example.news.ui3.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.api2.ApiClient
import com.example.news.api2.NewApiInterface
import com.example.news.model1.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//5show error,result,loading,1
class HomeViewModel :ViewModel() {


    var apiClient =ApiClient()

    private var result:MutableLiveData<News> = MutableLiveData()

    private var errorStatus:MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage:MutableLiveData<String> = MutableLiveData()

    private var loading:MutableLiveData<Boolean> = MutableLiveData()

    //for search
    private var searchResult:MutableLiveData<News> = MutableLiveData()

    fun getResult():LiveData<News> =result

    fun getErrorStatus():LiveData<Boolean> =errorStatus

    fun getErrorMessage():LiveData<String> =errorMessage

    fun getLoading():MutableLiveData<Boolean> =loading





    //for search
    fun getSearchResult():LiveData<News> =searchResult
    fun loadSearchResult(query: String){

        var apiCall=apiClient.searchNews(query)

        apiCall.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {

                loading.value=false
                errorStatus.value =false
                searchResult.value = response.body()

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                loading.value=false
                errorStatus.value=true
                errorMessage.value=t.toString()
            }

        })
    }





    fun loadResult(){

        var apiCall =apiClient.getTopHeadlines("us","Technology")
        apiCall.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                loading.value=false
                errorStatus.value =false
                result.value = response.body()


            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                loading.value=false
                errorStatus.value=true
                errorMessage.value =t.toString()
            }

        })
    }


}


