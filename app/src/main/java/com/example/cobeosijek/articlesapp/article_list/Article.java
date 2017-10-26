package com.example.cobeosijek.articlesapp.article_list;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import static com.example.cobeosijek.articlesapp.base.BaseModel.*;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class Article extends RealmObject implements Serializable {

    @PrimaryKey
    private int articleID;
    @Required
    private String articleTitle;
    @Required
    private String articleAuthor;
    @Required
    private String articleDescription;
    @Required
    private String articleType;

    public Article() {
    }

    public Article(String articleTitle, String articleAuthor, String articleDescription, ArticleTypeEnum articleType) {
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleDescription = articleDescription;
        this.articleType = articleType.name();
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public void setArticleType(ArticleTypeEnum articleType) {
        this.articleType = articleType.name();
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return getValueOrEmpty(articleTitle);
    }

    public String getArticleAuthor() {
        return getValueOrEmpty(articleAuthor);
    }

    public String getArticleDescription() {
        return getValueOrEmpty(articleDescription);
    }

    public ArticleTypeEnum getArticleType() {
        return (articleType != null) ? ArticleTypeEnum.valueOf(articleType) : null;
    }

    public String getArticleTypeString() {
        return (articleType != null) ? ArticleTypeEnum.valueOf(articleType).toString() : "";
    }

    public int getArticleID() {
        return articleID;
    }
}
