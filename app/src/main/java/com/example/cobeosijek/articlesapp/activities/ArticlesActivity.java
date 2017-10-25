package com.example.cobeosijek.articlesapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.ArticleAdapter;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;
import com.example.cobeosijek.articlesapp.utils.DialogUtils;
import com.example.cobeosijek.articlesapp.utils.OnDeleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener, ArticleAdapter.OnItemClickListener, OnDeleteListener {

    // TODO: 25/10/2017 add 3 fragments, and display them in a activity before this one, implement forward and back buttons and use backstack to do so

    @BindView(R.id.no_data_message)
    TextView noDataMessage;
    @BindView(R.id.articles_list)
    RecyclerView articlesList;
    @BindView(R.id.add_articles)
    FloatingActionButton addArticleBTN;

    private ArticleAdapter articleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);


        setUI();
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }

    private void setUI() {
        addArticleBTN.setOnClickListener(this);

        articleAdapter = new ArticleAdapter();
        articleAdapter.setOnItemClickListener(this);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        articlesList.addItemDecoration(itemDecoration);
        articlesList.setLayoutManager(layoutManager);
        articlesList.setAdapter(articleAdapter);

        loadData();
    }


    public void loadData() {
        articleAdapter.setArticleList(DBHelper.getInstance().getArticles());

        if (articleAdapter.getItemCount() < 1) {
            noDataMessage.setVisibility(View.VISIBLE);
        } else {
            noDataMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_articles:
                startActivity(AddArticleActivity.getLaunchIntent(this));
                break;
        }
    }

    @Override
    public void onItemClick(int articleId) {
        startActivity(ArticleDetailsActivity.getLaunchIntent(this, articleId));
    }

    @Override
    public void onItemLongClick(final int articleId) {
        DialogUtils.showDialog(this, articleId, this);
    }

    @Override
    public void deleteArticle(int articleId) {
        DBHelper.getInstance().removeArticle(articleId);
        loadData();
    }
}
