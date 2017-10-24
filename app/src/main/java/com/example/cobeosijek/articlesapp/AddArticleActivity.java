package com.example.cobeosijek.articlesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.article_list.ArticleTypeEnum;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;

public class AddArticleActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ImageView backIcon;
    EditText authorInput;
    EditText titleInput;
    EditText descriptionInput;
    Spinner typeInput;
    Button submitArticle;
    DBHelper dbHelper;
    private ArticleTypeEnum typeSelected;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AddArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        dbHelper = new DBHelper(ArticleApplication.getDBinstance());
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

        ArrayAdapter<ArticleTypeEnum> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ArticleTypeEnum.values());
        typeInput.setAdapter(spinnerAdapter);
        typeInput.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_bar_back:
                onBackPressed();
                break;
            case R.id.submit_article:
                //sendArticleBack();
                saveArticle();
                break;
        }
    }

    private void saveArticle() {
        // TODO: 23.10.2017. check user input 
        Article articleToSave = new Article(titleInput.getText().toString(), authorInput.getText().toString(), descriptionInput.getText().toString(), typeSelected);
        dbHelper.addArticle(articleToSave);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: 23.10.2017. not like this!!
        switch (i) {
            case 0:
                typeSelected = ArticleTypeEnum.POLITIC;
                break;
            case 1:
                typeSelected = ArticleTypeEnum.SPORT;
                break;
            case 2:
                typeSelected = ArticleTypeEnum.DEVELOP;
                break;
            case 3:
                typeSelected = ArticleTypeEnum.OTHER;
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeSelected = ArticleTypeEnum.OTHER;
    }

//    private void sendArticleBack() {
//        // TODO: 23/10/2017 check user input!!
//        Article article = new Article(titleInput.getText().toString(), authorInput.getText().toString(), descriptionInput.getText().toString(), ArticleTypeEnum.DEVELOP);
//        setResult(RESULT_OK, ArticlesActivity.getResultArticleIntent(article));
//        finish();
//    }
}
