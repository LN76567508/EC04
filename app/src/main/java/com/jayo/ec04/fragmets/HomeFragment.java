package com.jayo.ec04.fragmets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jayo.ec04.data.response.ShowResponse;
import com.jayo.ec04.data.retrofit.RetrofitHelper;
import com.jayo.ec04.databinding.FragmentHomeBinding;
import com.jayo.ec04.model.Movie;
import com.jayo.ec04.model.Series;
import com.jayo.ec04.model.Shows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RVResumeAdapter adapter = new RVResumeAdapter(getData());
        binding.rvMoviesResume.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvMoviesResume.setLayoutManager(layoutManager);

        RetrofitHelper.getService().getShows().enqueue(new Callback<ShowResponse>() {
            @Override
            public void onResponse(Call<ShowResponse> call, Response<ShowResponse> response) {
                if(response.isSuccessful()){
                    //assert response.body() != null;
                    showMovies(response.body().getShowsList());
                }
            }

            @Override
            public void onFailure(Call<ShowResponse> call, Throwable t) {

            }
        });
        homeViewModel.listLiveData.observe(requireActivity(),showList->{
            //Mostrar en Recycler

        });
        homeViewModel.getShows();
    }

    private void showMovies(List<Shows> showsList) {
        RVShowAdapter adapter= new RVShowAdapter(showsList, show -> {
            homeViewModel.addShow(show);

        });
        binding.rvShows.setAdapter(adapter);
    }

    //Se Crean las Series o Peliculas
    private List<Shows> getData(){
        List<Shows> shows = new ArrayList<>();
        shows.add(new Movie("","https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/0B691F512D942D1B346F4067DE17D537E06CD1C884CBADEF2147E51DBC7BFB41/scale?width=1200&aspectRatio=1.78&format=jpeg",""));
        shows.add(new Movie("","https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/7FEF9793F384603E356360ED43B0DA5ACC0D7CEC61453AEC4EE7609AF2378FEE/scale?width=1200&aspectRatio=1.78&format=jpeg",""));
        shows.add(new Movie("","https://fotografias.antena3.com/clipping/cmsimages01/2017/01/28/78CFC4B5-6E96-40A4-83EF-2411B3F54463/98.jpg?crop=960,540,x0,y0&width=1900&height=1069&optimize=high&format=webply",""));
        shows.add(new Movie("","https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/DA31A112650774BC400905E1A5822AC5D78E8C1A5E42CE4AD3708883DD5701F1/scale?width=1200&aspectRatio=1.78&format=jpeg",""));
        //shows.add(new Series("Sex Education","https://www.cinemascomics.com/wp-content/uploads/2020/01/17b5648dc97c211f2d85b1d91b40dce0.jpg",8));

        return shows;
    }
}