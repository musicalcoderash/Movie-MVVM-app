package com.example.moviemvvmapp.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviemvvmapp.data.repository.NetworkState
import com.example.moviemvvmapp.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val movieRepository: MoviePageListRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePageList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePageList.value?.isEmpty()?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}