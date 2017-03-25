package com.kronistas.marvelcharacters.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.adapters.RecyclerAdapterCharacters;
import com.kronistas.marvelcharacters.api.MarvelApi;
import com.kronistas.marvelcharacters.api.manager.CharacterManager;
import com.kronistas.marvelcharacters.api.request.CharacterRequest;
import com.kronistas.marvelcharacters.api.request.RequestSignature;
import com.kronistas.marvelcharacters.api.response.ServiceResponse;
import com.kronistas.marvelcharacters.api.vo.Character;
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
public class CharsFragment extends Fragment implements Commons.OnRecyclerItemClickedListener{

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


    public ArrayList<Character> characterList;
    public static final String TAG = CharsFragment.class.getSimpleName();

    public static CharsFragment newInstance(Bundle args) {

        CharsFragment.lookingFor = args.getBoolean("lookingFor");
        if (lookingFor) {
            searched = args.getString("lookFor");
            Utils.log(TAG,"searched: "+ searched );
        }


        CharsFragment fragment = new CharsFragment();
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
        characterList = new ArrayList<>();

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



    public void searchOnResponse (String name){

        Utils.log(TAG, "searchOnResponse");

        ArrayList<Character> found = new ArrayList<>();
        if (name != null){
            for (Character aux: characterList) {
                Utils.log(TAG, "aux name: " + aux.name);
                if ( (aux.name.equals(name)) || (aux.name.contains(name)) ) {
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
            textLoading.setText("Not found: " + name );
            progressBar.setVisibility(View.GONE);
        }

    }



    @Override
    public void onPause() {
        super.onPause();
    }

    public void loadRecycler(ArrayList<Character> list){
        recycler.setAdapter(new RecyclerAdapterCharacters(list, getContext(),this));
    }

    public void getService(){

        MarvelApi.create(getString(R.string.private_key), getString(R.string.public_key), getContext(), 5*1024*1024);

        CharacterRequest characterRequest = new CharacterRequest(RequestSignature.create());
        characterRequest.setLimit(100);
        characterRequest.setOffset(0);

        CharacterManager client = new CharacterManager();
        client.listCharacters(characterRequest, new Callback<ServiceResponse<Character>>() {
            @Override
            public void success(ServiceResponse<Character> characterServiceResponse, Response response) {
                characterList = characterServiceResponse.data.results;
                Utils.log(TAG,"tamaño lista: "+characterList.size());

                if (lookingFor){
                    searchOnResponse(searched);
                }else {
                    loadRecycler(characterList);
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

    @Override
    public void onResume() {
        super.onResume();

        getService();
    }

    private void showDialogFragment(DialogFragment dialogFragment) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(dialogFragment, dialogFragment.getTag());
        ft.commitAllowingStateLoss();
    }


    @Override
    public void onRecyclerItemClicked(Bundle parameter) {
        Utils.log(TAG, parameter.getString("name"));
        Utils.log(TAG, parameter.getString("description"));
        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(parameter);
        showDialogFragment(detailDialogFragment);
    }
}
