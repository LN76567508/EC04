package com.jayo.ec04.data.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jayo.ec04.model.ShowEntity;

import java.util.List;

public class ShowsRepository {

    private ShowDao dao;
    private PlayZoomDatabase db;

    public ShowsRepository(Application application) {
        db = PlayZoomDatabase.getInstance(application);
        dao = db.showDao();
    }

    public void addShow(ShowEntity showEntity){
        PlayZoomDatabase.dataBaseWriteExecuter.execute(()->
                dao.addShow(showEntity));
        dao.addShow(showEntity);

    }

    public LiveData<List<ShowEntity>> getAll(){
        return dao.getAll();

    }
}
