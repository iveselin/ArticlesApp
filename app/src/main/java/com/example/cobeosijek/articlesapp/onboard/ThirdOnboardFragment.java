package com.example.cobeosijek.articlesapp.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ivan on 25.10.2017..
 */

public class ThirdOnboardFragment extends Fragment {
    
    @BindView(R.id.back_icon)
    ImageView backIcon;

    @BindView(R.id.forward_icon)
    ImageView forwardIcon;

    @BindView(R.id.main_text)
    TextView welcomeText;

    private OnBackPressedFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onboard_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setUI();
    }

    private void setUI() {
        welcomeText.setText("Ok now you are ready, start!");
    }

    public void setOnBackClickedListener(OnBackPressedFragmentListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.back_icon)
    protected void onBackClick() {
        listener.onBackClicked(3);
    }

    @OnClick(R.id.forward_icon)
    protected void OnForwardClick() {
        listener.onForwardClicked(3);
    }
}
