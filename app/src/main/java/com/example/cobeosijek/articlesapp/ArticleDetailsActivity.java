package com.example.cobeosijek.articlesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;

public class ArticleDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_ID_SEND = "id_sent";

    ImageView backIcon;
    ImageView editIcon;
    TextView titleOutput;
    TextView authorOutput;
    TextView typeOutput;
    TextView descriptionOutput;

    DBHelper dbHelper;
    Article articleToDisplay;

    public static Intent getLaunchIntent(Context context, int articleID) {
        return new Intent(context, ArticleDetailsActivity.class).putExtra(KEY_ID_SEND, articleID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        dbHelper = new DBHelper(ArticleApplication.getDBinstance());
        getExtras();
        setUI();
    }

    @Override
    protected void onResume() {
        loadArticle();
        super.onResume();
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_SEND)) {
            if (dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_SEND, -1)) != null) {
                articleToDisplay = dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_SEND, -1));
            } else {
                Toast.makeText(getApplicationContext(), R.string.no_article_error_text, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void setUI() {
        backIcon = findViewById(R.id.action_bar_back);
        editIcon = findViewById(R.id.action_bar_edit);
        titleOutput = findViewById(R.id.article_title_output);
        authorOutput = findViewById(R.id.article_author_output);
        typeOutput = findViewById(R.id.article_type_output);
        descriptionOutput = findViewById(R.id.article_description_text_output);

        backIcon.setOnClickListener(this);
        editIcon.setOnClickListener(this);

        descriptionOutput.setMovementMethod(new ScrollingMovementMethod());

        loadArticle();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_bar_back:
                onBackPressed();
                break;
            case R.id.action_bar_edit:
                startActivity(EditArticleActivity.getLaunchIntent(this, articleToDisplay.getArticleID()));
                break;
        }
    }

    private void loadArticle() {
        getExtras();

        if (articleToDisplay != null) {
            titleOutput.setText(articleToDisplay.getArticleTitle());
            authorOutput.setText(String.format(getString(R.string.author_format), articleToDisplay.getArticleAuthor()));
            typeOutput.setText(String.format(getString(R.string.type_format), articleToDisplay.getArticleTypeString()));
            descriptionOutput.setText(articleToDisplay.getArticleDescription());
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_article_error_text, Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
