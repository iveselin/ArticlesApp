package com.example.cobeosijek.articlesapp.article_list;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class BaseRealmModel {

    protected String getValueOrEmpty(String string) {
        return (string != null) ? string : "";
    }
}
