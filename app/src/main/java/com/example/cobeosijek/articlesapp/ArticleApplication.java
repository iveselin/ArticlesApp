package com.example.cobeosijek.articlesapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class ArticleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("articles_database.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public static Realm getDBinstance() {
        return Realm.getDefaultInstance();
    }
}
