package com.example.tarena.renter.util;

import android.os.Handler;
import android.os.Looper;

import com.example.tarena.renter.bean.NewHouse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by tarena on 2017/7/13.
 */

public class HttpUtil {
    public static void getNewHouse(final String url, final OnResponselistener<Document> listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Document document= Jsoup.connect(url).get();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onResponse(document);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public interface OnResponselistener<T>{
        void onResponse(T t);
    }
}
