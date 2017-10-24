package com.example.cobeosijek.articlesapp.activities;

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

import com.example.cobeosijek.articlesapp.ArticleApplication;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.article_list.ArticleTypeEnum;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;
import com.example.cobeosijek.articlesapp.utils.StringUtils;

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
        if (checkUserInputValid()) {
            Article articleToSave = new Article(titleInput.getText().toString().trim(),
                    authorInput.getText().toString().trim(),
                    descriptionInput.getText().toString().trim(),
                    typeSelected);
            dbHelper.addArticle(articleToSave);
            finish();
        }

    }

    private boolean checkUserInputValid() {
        boolean inputIsValid = true;

        if (StringUtils.checkIfEmpty(titleInput.getText().toString().trim())) {
            titleInput.setError(getString(R.string.empty_text_error));
            inputIsValid = false;
        }

        if (StringUtils.checkIfEmpty(authorInput.getText().toString().trim())) {
            authorInput.setError(getString(R.string.empty_text_error));
            inputIsValid = false;
        }

        if (StringUtils.checkIfEmpty(descriptionInput.getText().toString().trim())) {
            descriptionInput.setError(getString(R.string.empty_text_error));
            inputIsValid = false;
        }

        return inputIsValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        typeSelected = (ArticleTypeEnum) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeSelected = ArticleTypeEnum.OTHER;
    }

//    private void sendArticleBack() {
//
//        Article article = new Article(titleInput.getText().toString(), authorInput.getText().toString(), descriptionInput.getText().toString(), ArticleTypeEnum.DEVELOP);
//        setResult(RESULT_OK, ArticlesActivity.getResultArticleIntent(article));
//        finish();
//    }
}
