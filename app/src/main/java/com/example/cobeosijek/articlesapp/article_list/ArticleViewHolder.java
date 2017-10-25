package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ArticleAdapter.OnItemClickListener listener;
    TextView articleTitle;
    TextView articleAuthor;
    private int articleId;


    public ArticleViewHolder(View itemView, ArticleAdapter.OnItemClickListener listener) {
        super(itemView);

        this.articleTitle = itemView.findViewById(R.id.article_item_title);
        this.articleAuthor = itemView.findViewById(R.id.article_item_author);
        this.listener = listener;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClick(view, articleId);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (listener != null) {
            listener.onItemLongClick(view, articleId);
        }
        return true;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


}
