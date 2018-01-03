package com.example.serpentcs.room_sample.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import com.example.serpentcs.room_sample.MainActivity;
import com.example.serpentcs.room_sample.database.DetailsDatabase;
import com.example.serpentcs.room_sample.entity.Details;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.serpentcs.room_sample.MainActivity.binding;

/**
 * Created by serpentcs on 30/12/17.
 */

public class DatabaseInitializer {
    public static final String TAG = DatabaseInitializer.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    public static void insertOnUIThread(@NonNull final DetailsDatabase db,
                                        final ArrayList<Details> detailsArrayList) {
        Log.e(TAG, "insertOnUIThread: ");

        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e(TAG, "doInBackground: ");
                insertDataToModel(db, detailsArrayList);
                return null;
            }
        });
    }

    private static Details addDetails(final DetailsDatabase db, Details details) {
        db.detailsDao().insertAll(details);
        return details;
    }

    private static void insertDataToModel(DetailsDatabase db, ArrayList<Details> detailsArrayList)
    {
        for (Details detailsList : detailsArrayList)
        {
            Details details = new Details("", "", "");
            details.setName(detailsList.getName());
            details.setUsername(detailsList.getUsername());
            details.setPassword(detailsList.getPassword());
            Log.e(TAG, "insertDataToModel: " + details.toString());
            addDetails(db, details);
        }
    }

    public static void getAllRecords(final DetailsDatabase db)
    {
        final List<Details> detailsList = new ArrayList<>();

        Observable<List<Details>> records = Observable.fromCallable
                (() -> db.detailsDao().getAllDetails());

        Log.e(TAG, "getAllRecords:");

        records
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Details>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(List<Details> detailList)
                    {
                        detailsList.addAll(detailList);
                        Log.e(TAG, "onNext: " + detailsList.size());

                        if (detailsList.size() > 0)
                        {
                            Log.e(TAG, "btnShowRecord: size > 0");

                            for (Details details : detailsList)
                            {

                               binding.tvShow.setText(details.getName() + " "
                                        + details.getUsername()
                                        + " " + details.getPassword() + "");
                            }
                        } else {
                            binding.tvShow.setText("There is No Data Available");
                        }
                    }
                });
    }
}
