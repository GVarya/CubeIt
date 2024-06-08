package com.example.cube;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendCubesWorker extends Worker {
    String s;
    URL url = null;

    public SendCubesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        s = getInputData().getString("leds");
        try {
            url = new URL(getInputData().getString("url") + s);
        } catch (IOException e) {
            Log.v(TAG, e.getLocalizedMessage());

        }
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            //url = new URL("http://192.168.1.175:80/hello?query1="+s);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            } else {
                Log.v(TAG, "Work finished erroe");
                //Toast.makeText(context, "Не получилось отправить ваш куб", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.v(TAG, e.getLocalizedMessage());
            //Toast.makeText(context, "Проблемы с соединением", Toast.LENGTH_LONG).show();
        }
        return Result.success();
    }
}
