package online.damfood.damfood.intro;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import online.damfood.damfood.R;
import online.damfood.damfood.adapter.IntroPagerAdapter;
import online.damfood.damfood.login.LoginActivity;
import online.damfood.damfood.main.MainActivity;

public class IntroActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private final String TAG = IntroActivity.class.getSimpleName();

    private ArrayList<ImageView> indicators;
    private List<SlideFragment> fragmentList;
    private ViewPager slideViewPager;
    private LinearLayout layoutIndicator;
    private TextView tvLewati, tvMasuk;
    private final int NUM_PAGES = 3;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            this.finish();
        }

        setContentView(R.layout.activity_intro);

        fragmentList = new ArrayList<>();
        fragmentList.add(SlideFragment.newInstance(R.string.slide_1_title, R.string.slide_1_desc, R.drawable.logo, R.color.bgSlider1));
        fragmentList.add(SlideFragment.newInstance(R.string.slide_2_title, R.string.slide_2_desc, R.drawable.logo, R.color.bgSlider2));
        fragmentList.add(SlideFragment.newInstance(R.string.slide_3_title, R.string.slide_3_desc, R.drawable.logo, R.color.bgSlider3));


        indicators = new ArrayList<>(NUM_PAGES);
        layoutIndicator = (LinearLayout) findViewById(R.id.layout_indicator);
        tvMasuk= (TextView) findViewById(R.id.tv_masuk);
        tvMasuk.setVisibility(View.GONE);
        tvMasuk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                launchHomeScreen();

            }
        });

        tvLewati = (TextView) findViewById(R.id.tv_lewati);
        tvLewati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                tvLewati.setTextColor(Color.GRAY);
                launchHomeScreen();
            }
        });

        slideViewPager = (ViewPager) findViewById(R.id.pager_slide);
        IntroPagerAdapter pagerAdapter = new IntroPagerAdapter(getSupportFragmentManager(), fragmentList);
        slideViewPager.setAdapter(pagerAdapter);
        slideViewPager.addOnPageChangeListener(this);

        Log.e(TAG, "onCreate: " + indicators.size());

        setupPagerIndicator(NUM_PAGES);

    }

    private void setupPagerIndicator(final int size) {
        for (int i = 0; i < size; i++) {
            indicators.add(new ImageView(IntroActivity.this));
            indicators.get(i).setImageDrawable(
                    ContextCompat.getDrawable(IntroActivity.this,
                            (i == 0) ? R.drawable.dot_indicator : R.drawable.dot_indicator_disabled)
            );

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = getResources().getDimensionPixelSize(R.dimen.dimen_size_10dp);
            params.setMargins(
                    margin,
                    0,
                    margin,
                    0
            );
            layoutIndicator.addView(indicators.get(i), params);
        }
        indicators.get(0).setSelected(true);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        IntroActivity.this.finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < NUM_PAGES; i++) {
            indicators.get(i).setImageDrawable(ContextCompat.getDrawable(IntroActivity.this, R.drawable.dot_indicator_disabled));
        }
        indicators.get(position).setImageDrawable(ContextCompat.getDrawable(IntroActivity.this, R.drawable.dot_indicator));

        tvMasuk.setVisibility(
                (position == (NUM_PAGES - 1)) ? View.VISIBLE : View.GONE
        );

        tvLewati.setVisibility(
                (position != (NUM_PAGES - 1)) ? View.VISIBLE : View.GONE
        );
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        prefManager.setFirstTimeLaunch(false);
        IntroActivity.this.finish();
    }
}
