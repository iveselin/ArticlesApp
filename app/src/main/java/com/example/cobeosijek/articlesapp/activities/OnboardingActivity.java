package com.example.cobeosijek.articlesapp.activities;

import android.os.Bundle;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.base.BaseActivity;
import com.example.cobeosijek.articlesapp.onboard.FirstOnBoardFragment;
import com.example.cobeosijek.articlesapp.onboard.OnBackPressedFragmentListener;
import com.example.cobeosijek.articlesapp.onboard.SecondOnBoardFragment;
import com.example.cobeosijek.articlesapp.onboard.ThirdOnBoardFragment;

public class OnboardingActivity extends BaseActivity implements OnBackPressedFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            FirstOnBoardFragment firstFragment = FirstOnBoardFragment.newInstance();
            firstFragment.setOnBackClickedListener(this);
            addFragment(firstFragment, R.id.fragment_container);
        }
    }

    @Override
    public void onBackClicked(int fragmentNumber) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onForwardClicked(int fragmentNumber) {
        switch (fragmentNumber) {
            case 1:
                SecondOnBoardFragment secondFragment = SecondOnBoardFragment.newInstance();
                secondFragment.setOnBackClickedListener(this);
                replaceFragment(secondFragment, R.id.fragment_container, true);
                break;
            case 2:
                ThirdOnBoardFragment thirdFragment = ThirdOnBoardFragment.newInstance();
                thirdFragment.setOnBackClickedListener(this);
                replaceFragment(thirdFragment, R.id.fragment_container, true);
                break;
            case 3:
                startActivity(ArticlesActivity.getLaunchIntent(this));
                finish();
                break;
        }
    }


}
