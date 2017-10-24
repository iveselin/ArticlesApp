package com.example.cobeosijek.articlesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditArticleActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String KEY_ID_SEND = "key_send";

    private Article articleToEdit;
    private DBHelper dbHelper;
    private ArticleTypeEnum typeSelected;
    ImageView backIcon;
    EditText authorInput;
    EditText titleInput;
    EditText descriptionInput;
    Spinner typeInput;
    Button submitArticle;

    public static Intent getLaunchIntent(Context launchContext, int articleID) {
        return new Intent(launchContext, EditArticleActivity.class).putExtra(KEY_ID_SEND, articleID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        dbHelper = new DBHelper(ArticleApplication.getDBinstance());
        getExtras();
        setUI();
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_SEND)) {
            articleToEdit = dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_SEND, -1));
        }
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

        if (articleToEdit != null) {
            authorInput.setText(articleToEdit.getArticleAuthor());
            titleInput.setText(articleToEdit.getArticleTitle());
            descriptionInput.setText(articleToEdit.getArticleDescription());
            typeInput.setSelection(spinnerAdapter.getPosition(articleToEdit.getArticleType()));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_bar_back:
                onBackPressed();
                break;
            case R.id.submit_article:
                editArticle();
                break;
        }
    }

    private void editArticle() {
        articleToEdit.setArticleAuthor(authorInput.getText().toString());
        articleToEdit.setArticleDescription(descriptionInput.getText().toString());
        articleToEdit.setArticleTitle(titleInput.getText().toString());
        articleToEdit.setArticleType(typeSelected);
        dbHelper.updateArticle(articleToEdit);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        switch (i) {
//            case 0:
//                typeSelected = ArticleTypeEnum.POLITIC;
//                break;
//            case 1:
//                typeSelected = ArticleTypeEnum.SPORT;
//                break;
//            case 2:
//                typeSelected = ArticleTypeEnum.DEVELOP;
//                break;
//            case 3:
//                typeSelected = ArticleTypeEnum.OTHER;
//                break;
//        }
        typeSelected = (ArticleTypeEnum) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeSelected = ArticleTypeEnum.OTHER;
    }
}
