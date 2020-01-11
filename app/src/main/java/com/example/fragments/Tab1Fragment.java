package com.example.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.com.example.adapters.RecyclerAdapter;
import com.example.gettersandsetters.DailyUpdates;
import com.example.moneycontrol.MoneyControl;
import com.example.moneycontrol.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Tab1Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    String[] type = { "Select", "Income", "Cost"};
    Spinner spinner;
    Button updateButton;
    TextView description;
    TextView amount;
    ArrayList<DailyUpdates> newUpdates = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    int balance = 0;
    TextView userBalance;

    public static SQLiteDatabase sqLiteDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LayoutInflater lf = this.getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_one, container, false); //pass the correct layout name for the fragment
        spinner = view.findViewById(R.id.spinner);
        updateButton = view.findViewById(R.id.update);
        description = view.findViewById(R.id.description);
        amount = view.findViewById(R.id.amount);
        recyclerView = view.findViewById(R.id.recyclerView);
        userBalance = view.findViewById(R.id.balance);

        // DataBase

        try {
            sqLiteDatabase = this.getActivity().openOrCreateDatabase("controlMoney", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS controlMoney (type VARCHAR, description VARCHAR, amount INTEGER, date VARCHAR)");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM controlMoney", null);
            cursor.moveToFirst();

            int colIndexType = cursor.getColumnIndex("type");
            int colIndexDescription = cursor.getColumnIndex("description");
            int colIndexAmount = cursor.getColumnIndex("amount");
            int colIndexDate = cursor.getColumnIndex("date");

            while (cursor != null) {

                DailyUpdates lastDailyUpdates = new DailyUpdates();
                lastDailyUpdates.setType(cursor.getString(colIndexType));
                lastDailyUpdates.setDescription(cursor.getString(colIndexDescription));
                lastDailyUpdates.setAmount(cursor.getString(colIndexAmount));
                lastDailyUpdates.setDate(cursor.getString(colIndexDate));

                newUpdates.add(lastDailyUpdates);

                if (cursor.getString(colIndexType).equals("Income")) {
                    balance = balance + Integer.valueOf(cursor.getString(colIndexAmount));
                } else {
                    balance = balance - Integer.valueOf(cursor.getString(colIndexAmount));
                }
                cursor.moveToNext();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

      //  sqLiteDatabase.execSQL("ALTER TABLE controlMoney ADD new_column_name column_definition");

        MoneyControl.balance.setText("Your balance: " + balance);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        recyclerAdapter = new RecyclerAdapter(getActivity(), newUpdates);
        recyclerView.setAdapter(recyclerAdapter);

        //sqLiteDatabase.execSQL("DELETE FROM controlMoney");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("Select") && !amount.getText().toString().equals("")) {

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);

                    DailyUpdates dailyUpdates = new DailyUpdates();
                    dailyUpdates.setAmount(amount.getText().toString());
                    dailyUpdates.setDescription(description.getText().toString());
                    dailyUpdates.setType(spinner.getSelectedItem().toString());
                    dailyUpdates.setDate(formattedDate);
                    newUpdates.add(dailyUpdates);

                    if (spinner.getSelectedItem().toString().equals("Income")) {
                        balance = balance + Integer.valueOf(amount.getText().toString());
                    } else {
                        balance = balance - Integer.valueOf(amount.getText().toString());
                    }

                    MoneyControl.balance.setText("Your balance: " + balance);

                    recyclerAdapter.notifyDataSetChanged();

                    String sql = "INSERT INTO controlMoney (type, description, amount, date) VALUES (? , ? , ? , ?)";
                    SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
                    sqLiteStatement.bindString(1, spinner.getSelectedItem().toString());
                    sqLiteStatement.bindString(2, description.getText().toString());
                    sqLiteStatement.bindString(3, amount.getText().toString());
                    sqLiteStatement.bindString(4, formattedDate);

                    sqLiteStatement.execute();

                    description.setText("");
                    amount.setText("");

                    spinner.setSelection(0);

                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}