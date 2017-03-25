package com.kronistas.marvelcharacters.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.adapters.RecyclerAdapterComics;
import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.manager.ComicManager;
import com.kronistas.marvelcharacters.api.request.ComicRequest;
import com.kronistas.marvelcharacters.api.request.RequestSignature;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.vo.Character;
import com.kronistas.marvelcharacters.api.vo.Comic;
import com.kronistas.marvelcharacters.utils.Commons;
import com.kronistas.marvelcharacters.utils.Utils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Raelyx on 26/9/2016.
 */
public class ComicsFragment extends Fragment implements Commons.OnRecyclerItemClickedListener{

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.recyclerView)
    RecyclerView recycler;
    @InjectView(R.id.genericLoading)
    LinearLayout loadingLayout;
    @InjectView(R.id.genericContainer)
    CoordinatorLayout recyclerContainerLayout;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.textLoading)
    TextView textLoading;
    static boolean lookingFor;
    static String searched;

    public ArrayList<Comic> comicList;
    public static final String TAG = ComicsFragment.class.getSimpleName();

    public static ComicsFragment newInstance(Bundle args) {

        ComicsFragment.lookingFor = args.getBoolean("lookingFor");
        if (lookingFor) {
            searched = args.getString("lookFor");
            Utils.log(TAG,"searched: "+ searched );
        }


        ComicsFragment fragment = new ComicsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_generic_display, container, false);
        ButterKnife.inject(this, rootView);

        recycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);
        recyclerContainerLayout.setVisibility(View.INVISIBLE);

        comicList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arguments = new Bundle();
                arguments.putString("TAGcaller", TAG);

                FabClickDialogFragment fabClickDialogFragment = FabClickDialogFragment.newInstance(arguments);
                showDialogFragment(fabClickDialogFragment);

            }
        });


        return rootView;
    }

    public void searchOnResponse (String title){

        Utils.log(TAG, "searchOnResponse");

        ArrayList<Comic> found = new ArrayList<>();
        if (title != null){
            for (Comic aux: comicList) {
                Utils.log(TAG, "aux title: " + aux.title);
                if ( (aux.title.equals(title)) || (aux.title.contains(title)) ) {
                    found.add(aux);
                }
            }
        }
        if(!found.isEmpty()){
            Utils.log(TAG, "!found.isEmpty");
            loadingLayout.setVisibility(View.INVISIBLE);
            recyclerContainerLayout.setVisibility(View.VISIBLE);
            loadRecycler(found);
        }else{
            loadingLayout.setVisibility(View.VISIBLE);
            textLoading.setText("Not found: " + title );
            progressBar.setVisibility(View.GONE);
        }

    }

    public void getService(){

        MarvelApi.create(getString(R.string.private_key), getString(R.string.public_key), getContext(), 5*1024*1024);

        ComicRequest comicRequest = new ComicRequest(RequestSignature.create());
        comicRequest.setLimit(100);
        comicRequest.setOffset(0);

        ComicManager client = new ComicManager();
        client.listComics(comicRequest, new Callback<ServiceResponse<Comic>>() {
            @Override
            public void success(ServiceResponse<Comic> comicServiceResponse, Response response) {
                comicList = comicServiceResponse.data.results;
                Utils.log(TAG,"tamaño lista: "+ comicList.size());

                if (lookingFor){
                    Utils.log(TAG,"lookingFor True, searched: "+ searched);
                    searchOnResponse(searched);
                }else {
                    loadRecycler(comicList);
                    loadingLayout.setVisibility(View.INVISIBLE);
                    recyclerContainerLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                textLoading.setText("There is a trouble: " + error.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    public void loadRecycler(ArrayList<Comic> list){
        recycler.setAdapter(new RecyclerAdapterComics(list, getContext(),this));
    }

    private void showDialogFragment(DialogFragment dialogFragment) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(dialogFragment, dialogFragment.getTag());
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onResume() {
        super.onResume();
        getService();
    }

    @Override
    public void onRecyclerItemClicked(Bundle parameter) {
        Utils.log(TAG, parameter.getString("name"));
        Utils.log(TAG, parameter.getString("description"));
        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(parameter);
        showDialogFragment(detailDialogFragment);
    }
}
