package com.navi.github.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navi.github.model.Repo
import com.navi.github.model.Status
import com.navi.github.repository.MainRepository
import com.navi.github.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository,val networkHelper: NetworkHelper) : ViewModel() {
    private var job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val viewModelScope : CoroutineScope
        get() {
            return CoroutineScope(Dispatchers.IO + job +exceptionHandler )
        }


    var userName = ""
    var repoName= ""

    private var _repoResponse = MutableLiveData<List<Repo>>()
    var repoPullReqResponse:LiveData<List<Repo>> = _repoResponse
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getGithubPullRequests(){
        viewModelScope.launch {
           loading.postValue(true)
            val response =  repository.getPullRequestData(userName, repoName)
             withContext(Dispatchers.Main) {
                if(networkHelper.isNetworkConnected()) {
                    if (response.isSuccessful) {
                        _repoResponse.postValue(response.body())
                        loading.value = false
                    } else  onError("Error : ${response.message()} ")
                }else onError("Error : Please connect to Network and try again")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        try {
            job.cancel()
        }catch (e: Exception){
            print(e.message)
        }catch (e: Throwable){
            print(e.message)
        }
        super.onCleared()
    }
}