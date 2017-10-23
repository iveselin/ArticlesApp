package com.example.cobeosijek.articlesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.article_list.ArticleTypeEnum;
import com.example.cobeosijek.articlesapp.article_list.ArticlesActivity;

public class AddArticleActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backIcon;
    EditText authorInput;
    EditText titleInput;
    EditText descriptionInput;
    Spinner typeInput;
    Button submitArticle;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AddArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        setUI();
    }

    private void setUI() {
        backIcon = findViewById(R.id.action_bar_back);
        authorInput = findViewById(R.id.article_author_input);
        titleInput = findViewById(R.id.article_title_input);
        descriptionInput = findViewById(R.id.article_description_text_input);
        typeInput = findViewById(R.id.article_type_input);
        submitArticle = findViewById(R.id.submit_article);

        backIcon.setOnClickListener(this);
        submitArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_bar_back:
                onBackPressed();
                break;
            case R.id.submit_article:
                sendArticleBack();
                break;
        }
    }

    private void sendArticleBack() {
        // TODO: 23/10/2017 check user input!!
        Article article = new Article(titleInput.getText().toString(), authorInput.getText().toString(), descriptionInput.getText().toString(), ArticleTypeEnum.DEVELOP);
        setResult(RESULT_OK, ArticlesActivity.getResultArticleIntent(article));
        finish();
    }
}
