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
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.AddArticleActivity;
import com.example.cobeosijek.articlesapp.ArticleApplication;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.db_utils.DBHelper;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener, ArticleAdapter.OnItemClickListener {

//    private static final String KEY_ARTICLE_SEND = "article";
//    private static final int KEY_ARTICLE_REQUEST = 22;

    TextView noDataMessage;
    RecyclerView articlesList;
    FloatingActionButton addArticleBTN;
    ArticleAdapter articleAdapter;
    DBHelper dbHelper;

//    public static Intent getResultArticleIntent(Article article) {
//        return new Intent().putExtra(KEY_ARTICLE_SEND, article);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        dbHelper = new DBHelper(ArticleApplication.getDBinstance());
        setUI();
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
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

        loadData();
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case KEY_ARTICLE_REQUEST:
//                    if (data.getSerializableExtra(KEY_ARTICLE_SEND) != null) {
//                        dbHelper.addArticle((Article) data.getSerializableExtra(KEY_ARTICLE_SEND));
//                    }
//                    break;
//            }
//            loadData();
//        }
//    }

    public void loadData() {
        articleAdapter.setArticleList(dbHelper.getArticles());

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
//              startActivityForResult(AddArticleActivity.getLaunchIntent(this),KEY_ARTICLE_REQUEST);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: 23.10.2017. go to descrip. activity 
        Toast.makeText(getApplicationContext(), "Item was clicked, but not deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        dbHelper.removeArticle(articleAdapter.getArticle(position));
        loadData();
    }
}
