package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.articlesapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private List<Article> articles = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setArticleList(List<Article> articleList) {
        articles.clear();
        articles.addAll(articleList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View articleView = inflater.inflate(R.layout.article_item, parent, false);

        return new ArticleViewHolder(articleView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        if (articles.isEmpty() || articles.get(position) == null) {
            return;
        }

        Article article = articles.get(position);

        holder.articleTitle.setText(article.getArticleTitle());
        holder.articleAuthor.setText(article.getArticleAuthor());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public Article getArticle(int position) {
        return articles.get(position);
    }
}
