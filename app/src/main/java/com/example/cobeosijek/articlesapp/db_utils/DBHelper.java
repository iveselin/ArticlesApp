package com.example.cobeosijek.articlesapp.db_utils;

import com.example.cobeosijek.articlesapp.article_list.Article;

import java.util.List;

import io.realm.Realm;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class DBHelper {
    Realm realm;

    public DBHelper(Realm realm) {
        this.realm = realm;
    }

    public void addArticle(Article article) {
        if (article != null) {
            int id;
            realm.beginTransaction();
            if (realm.where(Article.class).count() == 0) {
                id = 0;
            } else {
                id = realm.where(Article.class).max("articleID").intValue() + 1;
            }
            article.setArticleID(id);
            realm.copyToRealmOrUpdate(article);
            realm.commitTransaction();
        }
    }

    public List<Article> getArticles() {
        return realm.copyFromRealm(realm.where(Article.class).findAll());
    }

    public void updateArticle(Article article) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(article);
        realm.commitTransaction();
    }

    public void removeArticle(Article article) {
        // TODO: 23/10/2017 implement checks
        realm.beginTransaction();
        Article articleToDelete = realm.where(Article.class).equalTo("articleTitle", article.getArticleTitle()).findFirst();
        articleToDelete.deleteFromRealm();
        realm.commitTransaction();
    }

    public Article getArticle(int articleID) {
        return realm.copyFromRealm(realm.where(Article.class).equalTo("articleID", articleID).findFirst());
    }
}
