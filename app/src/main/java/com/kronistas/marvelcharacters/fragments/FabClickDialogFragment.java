package com.kronistas.marvelcharacters.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class FabClickDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = FabClickDialogFragment.class.getSimpleName();
    @InjectView(R.id.whichCharacter)
    EditText whichCharacter;
    @InjectView(R.id.searchButton)
    Button searchButton;
    static String caller;

    public static FabClickDialogFragment newInstance(Bundle args) {

        caller = args.getString("TAGcaller");
        Utils.log(TAG,"Dialog Caller: "+ caller);

        FabClickDialogFragment fragment = new FabClickDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState)    {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_AppCompat_DropDownUp;

        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback = (FragmentIterationListener) activity;
        }catch (Exception ex){
            Utils.log(TAG,"El Activity debe implementar la interfaz FragmentIterationListener");

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_fab, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        ButterKnife.inject(this, view);

        searchButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.searchButton:
                Bundle bundle = new Bundle();
                if (!whichCharacter.getText().toString().equals("")){

                    bundle.putString("lookFor", whichCharacter.getText().toString());
                    Utils.log(TAG,"lookFor a enviar: " +whichCharacter.getText().toString());
                    bundle.putBoolean("lookingFor", true);
                    Utils.log(TAG,"lookingFor true");

                }else{

                    bundle.putBoolean("lookingFor", false);
                    Utils.log(TAG,"lookingFor false");
                }

                bundle.putString("TAGcaller", caller);
                Utils.log(TAG,"TAGcaller a enviar: "+caller);

                mCallback.onFragmentIteration(bundle);
                dismiss();
                break;
        }
    }

    private FragmentIterationListener mCallback = null;
    public interface FragmentIterationListener{
        public void onFragmentIteration(Bundle parameters);
    }

}