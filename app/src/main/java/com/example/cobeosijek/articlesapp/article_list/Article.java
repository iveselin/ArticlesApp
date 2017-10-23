package com.example.cobeosijek.articlesapp.article_list;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class Article extends RealmObject implements Serializable {

    @Required
    @PrimaryKey
    private String articleTitle;
    @Required
    private String articleAuthor;
    @Required
    private String articleDescription;
    @Required
    private String articleType;


    public Article(String articleTitle, String articleAuthor, String articleDescription, ArticleTypeEnum articleType) {
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleDescription = articleDescription;
        this.articleType = articleType.name();
    }

    public Article() {
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public ArticleTypeEnum getArticleType() {
        return (articleType != null) ? ArticleTypeEnum.valueOf(articleType) : null;
    }


}
