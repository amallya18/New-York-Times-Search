package com.codepath.anmallya.nytsearch.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codepath.anmallya.nytsearch.R;
import com.codepath.anmallya.nytsearch.helper.Constants;
import com.codepath.anmallya.nytsearch.helper.Utils;
import com.codepath.anmallya.nytsearch.model.Filter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by anmallya on 10/22/2016.
 */

public class FilterFragment extends DialogFragment implements DatePickerFragment.DatePickerDialogListener {

    private EditText mEditText;
    private Spinner mSpinner;
    private CheckBox cbSports, cbFashion, cbArts, cbEdu, cbHealth;

    @Override
    public void onStart()
    {
        super.onStart();
        if (getDialog() == null)
            return;
        setDimensions();
    }

    private void setDimensions(){
        int dialogHeight = Utils.convertDpToPixel(Constants.DIALOG_FRAGMENT_HEIGHT);
        int dialogWidth = Utils.convertDpToPixel(Constants.DIALOG_FRAGMENT_WIDTH);
        System.out.println("#############"+dialogHeight+" "+dialogWidth);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }


    public FilterFragment() {
    }

    public static FilterFragment newInstance(String title) {
        FilterFragment frag = new FilterFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container);
        setViews(view);
        addValues();
        setButtons(view);
        setEditText(view);
        return view;
    }

    private void setViews(View view){
        mEditText = (EditText) view.findViewById(R.id.et_date);
        mSpinner=(Spinner) view.findViewById(R.id.mySpinner);
        cbSports = (CheckBox)view.findViewById(R.id.checkbox_sports);
        cbArts = (CheckBox)view.findViewById(R.id.checkbox_arts);
        cbFashion = (CheckBox)view.findViewById(R.id.checkbox_fashion);
        cbEdu = (CheckBox)view.findViewById(R.id.checkbox_education);
        cbHealth = (CheckBox)view.findViewById(R.id.checkbox_health);
    }

    private void addValues(){
        Filter filter = Filter.getInstance();
        mEditText.setText(filter.getBeginDateFormatted());
        for(String s:filter.getNewsDesk()){
            switch(s){
                case("Sports"):
                     cbSports.setChecked(true);
                     break;
                case("Arts"):
                    cbArts.setChecked(true);
                    break;
                case("Fashion & Style"):
                    cbFashion.setChecked(true);
                    break;
                case("Education"):
                    cbEdu.setChecked(true);
                    break;
                case("Health"):
                    cbHealth.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void setEditText(View view){
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private void saveClicked(){
        Filter filter = Filter.getInstance();
        filter.setSort(mSpinner.getSelectedItem().toString());
        ArrayList<String> newsDesk = new ArrayList<String>();
        if(cbSports.isChecked()) newsDesk.add("Sports");
        if(cbArts.isChecked()) newsDesk.add("Arts");
        if(cbFashion.isChecked()) newsDesk.add("Fashion & Style");
        if(cbEdu.isChecked()) newsDesk.add("Education");
        if(cbHealth.isChecked()) newsDesk.add("Health");

        filter.setNewsDesk(newsDesk);
        filter.setBeginDateFormatted(mEditText.getText().toString());
        filter.setBeginDate((String)mEditText.getTag());
        getDialog().dismiss();
    }

    public void cancelClicked(){
        getDialog().dismiss();
    }


    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setTargetFragment(FilterFragment.this, 300);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onFinishEditDialog(int yy, int mm, int dd){
        mEditText.setText(mm+"/"+dd+"/"+yy);
        mEditText.setTag(yy+""+edit(dd)+""+edit(mm)+"");
    }


    private String edit(int n){
        if(n<10){
            return "0"+n;
        }
        return n+"";
    }

    void setButtons(View view){
        Button saveButton = (Button) view.findViewById(R.id.save_btn);
        saveButton.setOnClickListener(v -> saveClicked());

        Button cancelButton = (Button) view.findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(v -> cancelClicked());
    }
}