package com.example.cobeosijek.articlesapp.activities;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.onboard.FirstOnboardFragment;
import com.example.cobeosijek.articlesapp.onboard.OnBackPressedFragmentListener;
import com.example.cobeosijek.articlesapp.onboard.SecondOnboardFragment;
import com.example.cobeosijek.articlesapp.onboard.ThirdOnboardFragment;

public class OnboardingActivity extends AppCompatActivity implements OnBackPressedFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            FirstOnboardFragment firstFragment = new FirstOnboardFragment();
            firstFragment.setOnBackClickedListener(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public void onBackClicked(int fragmentNumber) {
        onBackPressed();
    }

    @Override
    public void onForwardClicked(int fragmentNumber) {
        switch (fragmentNumber) {
            case 1:
                SecondOnboardFragment secondFragment = new SecondOnboardFragment();
                secondFragment.setOnBackClickedListener(this);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, secondFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                ThirdOnboardFragment thirdFrament = new ThirdOnboardFragment();
                thirdFrament.setOnBackClickedListener(this);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, thirdFrament)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                startActivity(ArticlesActivity.getLaunchIntent(this));
                break;
        }
    }

}
