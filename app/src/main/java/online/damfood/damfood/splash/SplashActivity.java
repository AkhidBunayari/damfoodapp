package online.damfood.damfood.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import online.damfood.damfood.R;
import online.damfood.damfood.intro.IntroActivity;
import online.damfood.damfood.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    //Waktu splash 3 detik
    private static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {

            /*
			 * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(i);

                    // Close this activity
                    finish();
                }
            }, TIME_OUT);
        }

        //Mematikan fungsi backkey saat splash berlangsung
        @Override
        public void onBackPressed() {
            return;
        }
}
