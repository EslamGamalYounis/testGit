package com.example.rxmultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just(1,2,3,4,5)
                .subscribeOn(Schedulers.io())
                .doOnNext(c-> Log.d(TAG,"Eslam upStream: "+c+" current thread :"+Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .subscribe(o-> Log.d(TAG,"Eslam downStream:"+o+" current thread :"+Thread.currentThread().getName()));

        sleep(300);
    }

    public void sleep(int i){
        try {
            //TODO remove todo
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
