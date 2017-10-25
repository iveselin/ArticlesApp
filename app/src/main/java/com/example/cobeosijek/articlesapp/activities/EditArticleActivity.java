package com.example.cobeosijek.articlesapp.activities;

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
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.ArticleApplication;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.article_list.ArticleTypeEnum;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;
import com.example.cobeosijek.articlesapp.utils.StringUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditArticleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String KEY_ID_SEND = "key_send";

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

    @BindString(R.string.empty_text_error)
    String errorMessage;

    private Article articleToEdit;
    private ArticleTypeEnum typeSelected;
    private ArrayAdapter<ArticleTypeEnum> spinnerAdapter;

    public static Intent getLaunchIntent(Context launchContext, int articleID) {
        return new Intent(launchContext, EditArticleActivity.class).putExtra(KEY_ID_SEND, articleID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        ButterKnife.bind(this);

        getExtras();
        setUI();
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_SEND)) {
            articleToEdit = DBHelper.getInstance().getArticle(getIntent().getIntExtra(KEY_ID_SEND, -1));
            if (articleToEdit == null) {
                Toast.makeText(ArticleApplication.getInstance(), R.string.no_article_error_text, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void setUI() {
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ArticleTypeEnum.values());
        typeInput.setAdapter(spinnerAdapter);
        typeInput.setOnItemSelectedListener(this);

        setArticle();
    }

    private void setArticle() {
        if (articleToEdit != null) {
            authorInput.setText(articleToEdit.getArticleAuthor());
            titleInput.setText(articleToEdit.getArticleTitle());
            descriptionInput.setText(articleToEdit.getArticleDescription());
            typeInput.setSelection(spinnerAdapter.getPosition(articleToEdit.getArticleType()));
        }
    }

    @OnClick(R.id.submit_article)
    protected void editArticle() {
        if (checkUserInputValid()) {
            articleToEdit.setArticleAuthor(authorInput.getText().toString());
            articleToEdit.setArticleDescription(descriptionInput.getText().toString());
            articleToEdit.setArticleTitle(titleInput.getText().toString());
            articleToEdit.setArticleType(typeSelected);

            DBHelper.getInstance().updateArticle(articleToEdit);
            finish();
        }
    }

    @OnClick(R.id.action_bar_back)
    protected void goBack() {
        onBackPressed();
    }

    private boolean checkUserInputValid() {
        boolean inputIsValid = true;

        if (StringUtils.checkIfEmpty(titleInput.getText().toString().trim())) {
            titleInput.setError(errorMessage);
            inputIsValid = false;
        }

        if (StringUtils.checkIfEmpty(authorInput.getText().toString().trim())) {
            authorInput.setError(errorMessage);
            inputIsValid = false;
        }

        if (StringUtils.checkIfEmpty(descriptionInput.getText().toString().trim())) {
            descriptionInput.setError(errorMessage);
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
