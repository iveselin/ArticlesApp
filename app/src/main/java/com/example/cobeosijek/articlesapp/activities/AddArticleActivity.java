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

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.article_list.ArticleTypeEnum;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;
import com.example.cobeosijek.articlesapp.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddArticleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.action_bar_back)
    ImageView backIcon;
    @BindView(R.id.article_author_input)
    EditText authorInput;
    @BindView(R.id.article_title_input)
    EditText titleInput;
    @BindView(R.id.article_description_text_input)
    EditText descriptionInput;
    @BindView(R.id.article_type_input)
    Spinner typeInput;
    @BindView(R.id.submit_article)
    Button submitArticle;

    private ArticleTypeEnum typeSelected;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, AddArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        ButterKnife.bind(this);

        setUI();
    }

    private void setUI() {
        // TODO: 25/10/2017 what if enum needs to be translated?!
        ArrayAdapter<ArticleTypeEnum> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ArticleTypeEnum.values());
        typeInput.setAdapter(spinnerAdapter);
        typeInput.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.action_bar_back)
    protected void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.submit_article)
    protected void saveArticle() {
        if (checkUserInputValid()) {
            Article articleToSave = new Article(titleInput.getText().toString().trim(),
                    authorInput.getText().toString().trim(),
                    descriptionInput.getText().toString().trim(),
                    typeSelected);
            DBHelper.getInstance().addArticle(articleToSave);
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


}
