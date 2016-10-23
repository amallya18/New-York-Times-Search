package com.codepath.anmallya.nytsearch.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by anmallya on 10/22/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener  {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        DatePickerDialogListener listener = (DatePickerDialogListener) getTargetFragment();
        listener.onFinishEditDialog(yy, mm + 1, dd);
        dismiss();
    }

    public interface DatePickerDialogListener {
        void onFinishEditDialog(int yy, int mm, int dd);
    }
}