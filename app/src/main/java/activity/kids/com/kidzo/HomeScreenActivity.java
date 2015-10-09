package activity.kids.com.kidzo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class HomeScreenActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mBtnAlphabet;
    private Button mBtnCounting;
    private Button mBtnPoems;
    private Button mBtnNames;
    private TextView mTxvAppName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("6BD2354640ED316BD6C556F8C9DCE7C9")
                .addTestDevice("97EC3288B0A8FDAAAEDB6762FE0E34D7")
                .build();
        mAdView.loadAd(adRequest);



      /*AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/


        mTxvAppName=(TextView)findViewById(R.id.txv_app_name);
        mBtnAlphabet=(Button)findViewById(R.id.btn_a_to_z);
        mBtnCounting=(Button)findViewById(R.id.btn_counting);
        mBtnPoems=(Button)findViewById(R.id.btn_poems);
        mBtnNames =(Button)findViewById(R.id.btn_names);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.push_down_in_rotate);
        mBtnAlphabet.startAnimation(animation);
        animation=AnimationUtils.loadAnimation(this, R.anim.push_down_in_rotate);
        mBtnCounting.startAnimation(animation);
        animation=AnimationUtils.loadAnimation(this, R.anim.slide_in_left_rotate);
        mBtnPoems.startAnimation(animation);
        animation=AnimationUtils.loadAnimation(this, R.anim.push_up_in_x_y);
        mBtnNames.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mTxvAppName.setVisibility(View.VISIBLE);
                animation=AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce);
                mTxvAppName.startAnimation(animation);
                if(mAdView.isClickable())
                {
                    Log.v("STR","Loaded");
                }
                else
                {
                    Log.v("STR","Not loaded");
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mBtnAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AlphabetActivity.class));
            }
        });

        mBtnNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),NamesMenuActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
