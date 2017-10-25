package com.example.cobeosijek.articlesapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class ArticleApplication extends Application {

    private static ArticleApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        setInstance(this);

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private static void setInstance(ArticleApplication instanceToAdd) {
        instance = instanceToAdd;
    }

    public static ArticleApplication getInstance() {
        return instance;
    }
}
