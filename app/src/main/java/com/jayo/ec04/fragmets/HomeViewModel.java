package com.jayo.ec04.fragmets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jayo.ec04.data.db.ShowsRepository;
import com.jayo.ec04.model.ShowEntity;
import com.jayo.ec04.model.Shows;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {


    private ShowsRepository repository;
    public LiveData<List<ShowEntity>> listLiveData = new MutableLiveData<List<ShowEntity>>();


    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new ShowsRepository(application);
    }

    public void addShow(Shows show){
        ShowEntity showEntity = new ShowEntity();
        showEntity.setName(show.getName());
        showEntity.setImageUrl(show.getImgUrl());
        repository.addShow(showEntity);
    }

    public void getShows(){
        listLiveData = repository.getAll();
    }

    public class HomeViewModelFactory implements ViewModelProvider.Factory{

        private final Application application;
        public HomeViewModelFactory(Application myApplication){
            application = myApplication;
        }
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
            return (T) new HomeViewModel((application));
        }

    }
}
