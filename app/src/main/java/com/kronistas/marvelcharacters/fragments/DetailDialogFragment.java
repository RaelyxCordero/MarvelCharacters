package com.kronistas.marvelcharacters.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kronistas.marvelcharacters.R;
import com.kronistas.marvelcharacters.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = DetailDialogFragment.class.getSimpleName();
    @InjectView(R.id.genericDescription)
    TextView genericDescription;
    @InjectView(R.id.genericName)
    TextView genericName;
    @InjectView(R.id.okButton)
    Button okButton;
    static String name;
    static String description;

    public static DetailDialogFragment newInstance(Bundle args) {

        name = args.getString("name");
        description = args.getString("description");
        if(description.equals("")){
            description = "There's no description";
        }

        DetailDialogFragment fragment = new DetailDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState)    {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_AppCompat_DropDownUp;

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_detail, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        ButterKnife.inject(this, view);

        genericName.setText(name);
        genericDescription.setText(description);

        okButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.okButton:
                dismiss();
                break;
        }
    }


}