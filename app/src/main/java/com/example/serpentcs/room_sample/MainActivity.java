package com.example.serpentcs.room_sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.serpentcs.room_sample.database.DetailsDatabase;
import com.example.serpentcs.room_sample.databinding.ActivityMainBinding;
import com.example.serpentcs.room_sample.entity.Details;
import com.example.serpentcs.room_sample.utils.DatabaseInitializer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();
    public static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        binding.btnClickHere.setOnClickListener(v -> {

            ArrayList<Details> detailsArrayList = new ArrayList<>();
            detailsArrayList.add(new Details(
                    binding.tvname.getText().toString(),
                    binding.tvusername.getText().toString(),
                    binding.tvpassword.getText().toString()));

            Log.e(TAG, "btnClickHere: INNN::::" + binding.tvname.getText().toString());
            Log.e(TAG, "btnClickHere: INNN::::" + binding.tvusername.getText().toString());
            Log.e(TAG, "btnClickHere: INNN:::" + binding.tvpassword.getText().toString());

            DatabaseInitializer.insertOnUIThread(DetailsDatabase.getInstance(MainActivity.this)
                    ,detailsArrayList);

            binding.tvname.setText("");
            binding.tvusername.setText("");
            binding.tvpassword.setText("");
        });

        binding.btnShowRecord.setOnClickListener(v -> {
            Log.e(TAG, "btnShowRecord: INNN");
            List<Details> detailsList = null;

             DatabaseInitializer.getAllRecords(DetailsDatabase.getInstance(MainActivity.this));

        });
    }
}
