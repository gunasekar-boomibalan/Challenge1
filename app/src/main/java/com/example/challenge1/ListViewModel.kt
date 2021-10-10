package com.example.challenge1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.*
class ListViewModel : ViewModel() {
    val apiService = ApiService().getApiService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val list = MutableLiveData<ArrayList<Data>>()
    val loadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun load(page : Int) {
        fetchUsers(page)
    }
    private fun fetchUsers(pageNo : Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val request = JsonObject()
            request.addProperty("user_id",  0)
            request.addProperty("page_no", pageNo)
            request.addProperty("slug", "news")
            request.addProperty("limit", 5)

            val response = apiService.artical(request)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if(response.body()?.status == 1) {
                        Singleton.loading = true
                        list.value = response.body()?.data
                        loadError.postValue("")
                        loading.postValue(false)
                    } else{
                        onError("Something went wrong. ")
                    }
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        loadError.postValue("")
        loading.postValue(false)
    }
    private fun onError(message: String) {
        loadError.postValue(message)
        loading.postValue(false)
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}