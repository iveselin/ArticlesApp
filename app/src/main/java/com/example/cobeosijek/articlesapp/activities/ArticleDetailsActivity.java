package com.example.cobeosijek.articlesapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.Article;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailsActivity extends AppCompatActivity {

    private static final String KEY_ID_SEND = "id_sent";

    @BindView(R.id.action_bar_back)
    ImageView backIcon;

    @BindView(R.id.action_bar_edit)
    ImageView editIcon;

    @BindView(R.id.article_title_output)
    TextView titleOutput;

    @BindView(R.id.article_author_output)
    TextView authorOutput;

    @BindView(R.id.article_type_output)
    TextView typeOutput;

    @BindView(R.id.article_description_text_output)
    TextView descriptionOutput;

    private Article articleToDisplay;

    public static Intent getLaunchIntent(Context context, int articleID) {
        return new Intent(context, ArticleDetailsActivity.class).putExtra(KEY_ID_SEND, articleID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);

        getExtras();
        setUI();
    }

    @Override
    protected void onResume() {
        getExtras();
        loadArticle();
        super.onResume();
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_SEND)) {
            articleToDisplay = DBHelper.getInstance().getArticle(getIntent().getIntExtra(KEY_ID_SEND, -1));
        }

        if (articleToDisplay == null) {
            Toast.makeText(getApplicationContext(), R.string.no_article_error_text, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void setUI() {
        // TODO: 25/10/2017 maybe scrollview
        descriptionOutput.setMovementMethod(new ScrollingMovementMethod());

        loadArticle();
    }

    @OnClick(R.id.action_bar_back)
    public void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.action_bar_edit)
    public void startEdit() {
        startActivity(EditArticleActivity.getLaunchIntent(this, articleToDisplay.getArticleID()));
    }

    private void loadArticle() {
        if (articleToDisplay != null) {
            titleOutput.setText(articleToDisplay.getArticleTitle());
            authorOutput.setText(String.format(getString(R.string.author_format), articleToDisplay.getArticleAuthor()));
            typeOutput.setText(String.format(getString(R.string.type_format), articleToDisplay.getArticleTypeString()));
            descriptionOutput.setText(articleToDisplay.getArticleDescription());
        }
    }
}
