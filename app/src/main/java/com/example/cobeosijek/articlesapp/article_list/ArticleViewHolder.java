package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_item_title)
    TextView articleTitle;

    @BindView(R.id.article_item_author)
    TextView articleAuthor;

    private ArticleAdapter.OnItemClickListener listener;
    private int articleId;


    public ArticleViewHolder(View itemView, ArticleAdapter.OnItemClickListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        this.listener = listener;
    }

    @OnClick
    public void onItemClick() {
        if (listener != null) {
            listener.onItemClick(articleId);
        }
    }

    @OnLongClick
    public boolean onItemLongClick() {
        if (listener != null) {
            listener.onItemLongClick(articleId);
        }
        return true;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


}
