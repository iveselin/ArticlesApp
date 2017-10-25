package com.example.cobeosijek.articlesapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.cobeosijek.articlesapp.R;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class DialogUtils {

    public static void showDialog(Context from, final int articleId, final OnDeleteListener listener) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(from);
        alertBuilder.setMessage(R.string.dialog_delete_message);

        alertBuilder.setPositiveButton(R.string.dialog_positive_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.deleteArticle(articleId);
            }
        });

        alertBuilder.setNegativeButton(R.string.dialog_negative_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}
