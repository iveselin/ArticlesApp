package com.example.cobeosijek.articlesapp.article_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.AddArticleActivity;
import com.example.cobeosijek.articlesapp.ArticleApplication;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener, ArticleAdapter.OnItemClickListener {

    private static final String KEY_ARTICLE_SEND = "article";
    private static final int KEY_ARTICLE_REQUEST = 22;

    TextView noDataMessage;
    RecyclerView articlesList;
    FloatingActionButton addArticleBTN;
    ArticleAdapter articleAdapter;
    DBHelper dbHelper;

    public static Intent getResultArticleIntent(Article article) {
        return new Intent().putExtra(KEY_ARTICLE_SEND, article);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        dbHelper = new DBHelper(ArticleApplication.getDBinstance());
        setUI();
    }

    private void setUI() {
        noDataMessage = findViewById(R.id.no_data_message);
        addArticleBTN = findViewById(R.id.add_articles);
        articlesList = findViewById(R.id.articles_list);

        addArticleBTN.setOnClickListener(this);

        articleAdapter = new ArticleAdapter();
        articleAdapter.setOnItemClickListener(this);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        articlesList.addItemDecoration(itemDecoration);
        articlesList.setLayoutManager(layoutManager);
        articlesList.setAdapter(articleAdapter);

        articleAdapter.setArticleList(dbHelper.getArticles());

        // TODO: 23/10/2017  put into loadData method
        if (articleAdapter.getItemCount() < 1) {
            noDataMessage.setVisibility(View.VISIBLE);
        } else {
            noDataMessage.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case KEY_ARTICLE_REQUEST:
                    // TODO: 23/10/2017 check if there is any and notifiy adapter
                    dbHelper.addArticle((Article) data.getSerializableExtra(KEY_ARTICLE_SEND));
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_articles:
                startActivityForResult(AddArticleActivity.getLaunchIntent(this), KEY_ARTICLE_REQUEST);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: 23/10/2017 on long click
        dbHelper.removeArticle(articleAdapter.getArticle(position));
        // TODO: 23/10/2017 notify recycler view
    }
}
