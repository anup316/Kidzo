package activity.kids.com.kidzo;

import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import activity.kids.com.kidzo.adapters.NamesPagerAdapter;
import activity.kids.com.kidzo.utils.Constants;


public class NamesHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private TextToSpeech mSpeak;

    private final String mCalendarName[] = {"January", "February", "March", "April", "May", " June", "July", "August",
            "September", "October", "November", "December"};
    private final String mAnimalName[] = {"bear", "buffalo", "cow", "dinosaur", "dog", "giraffe", "kangaaro", "lion", "sheep", "tiger"};
    private final String mBirdName[] = {"crow", "eagle", "hen", "humming", "kingfisher", "owl", "peacock", "pigeon", "sparrow", "vulture"};
    private final String mWeekDays[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private final String mVehicles[] = {"bicycle", "motorcycle", "car", "van", "truck", "tractor", "aeroplane", "helicopter", "ship", "train"};
    private final String mColors[] = {"Red", "Green", "Blue", "white", "orange", "purple", "grey", "black", "yellow", "pink"};


    private LinearLayout mLayoutAds;
    private ImageButton mBtnBack;
    private ImageButton mBtnNext;
    private TextView mTxvTitle;

    private Handler mNamesLoopHandler;
    private Timer mLoopTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_home);
        mBtnNext = (ImageButton) findViewById(R.id.btnNext);
        mBtnNext.setOnClickListener(this);
        mBtnBack = (ImageButton) findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(this);
        mTxvTitle = (TextView) findViewById(R.id.txv_title);
        mLayoutAds = (LinearLayout) findViewById(R.id.lyt_ads);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("6BD2354640ED316BD6C556F8C9DCE7C9")
                .addTestDevice("97EC3288B0A8FDAAAEDB6762FE0E34D7")
                .build();
        mAdView.loadAd(adRequest);


        final String name = getIntent().getStringExtra(Constants.INTENT_DATA_NAMES_KEY);
        String arraytoSpeak[];
        if (name.equals(Constants.INTENT_DATA_ANIMAl))
            arraytoSpeak = mAnimalName;
        else if (name.equals(Constants.INTENT_DATA_BIRD))
            arraytoSpeak = mBirdName;
        else if (name.equals(Constants.INTENT_DATA_COLOR))
            arraytoSpeak = mColors;
        else if (name.equals(Constants.INTENT_DATA_DAY))
            arraytoSpeak = mWeekDays;
        else if (name.equals(Constants.INTENT_DATA_VEHICLE))
            arraytoSpeak = mVehicles;
        else arraytoSpeak = mCalendarName;
        final String StringName[] = arraytoSpeak;
        mPager = (ViewPager) findViewById(R.id.view_pager_names);
        mPager.setAdapter(new NamesPagerAdapter(getSupportFragmentManager(), name));


        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSpeak.speak(StringName[position], TextToSpeech.QUEUE_FLUSH, null);
                mTxvTitle.setText(StringName[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mSpeak = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                String speakFirst = null;
                if (status != TextToSpeech.ERROR) {
                    mSpeak.setLanguage(Locale.US);
                    mSpeak.setSpeechRate((float) 0.8);
                    if (name.equals(Constants.INTENT_DATA_BIRD))
                        speakFirst = mBirdName[0];
                    else if (name.equals(Constants.INTENT_DATA_ANIMAl))
                        speakFirst = mAnimalName[0];
                    else if (name.equals(Constants.INTENT_DATA_DAY))
                        speakFirst = mWeekDays[0];
                    else if (name.equals(Constants.INTENT_DATA_MONTH))
                        speakFirst = mCalendarName[0];
                    else if (name.equals(Constants.INTENT_DATA_VEHICLE))
                        speakFirst = mVehicles[0];
                    else speakFirst = mColors[0];
                    mSpeak.speak(speakFirst, TextToSpeech.QUEUE_FLUSH, null);
                    mTxvTitle.setText(speakFirst);
                }
            }
        });

        mNamesLoopHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(!mSpeak.isSpeaking()) {
                    int pos = mPager.getCurrentItem();
                    if (pos + 1 == 10)
                        pos = -1;
                    mPager.setCurrentItem(pos + 1);
                }
            }
        };

        mLoopTimer=new Timer();
        TimerTask mTimertask =new TimerTask() {
            @Override
            public void run() {

                Message msg = mNamesLoopHandler.obtainMessage();
                mNamesLoopHandler.sendMessage(msg);
            }
        };
        mLoopTimer.schedule(mTimertask,6000,6000);

    }

    @Override
    public void onStop() {
        super.onStop();
        mLoopTimer.cancel();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_names_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                int posBack = mPager.getCurrentItem();
                mPager.setCurrentItem(posBack - 1);
                break;
            case R.id.btnNext:
                int posFront = mPager.getCurrentItem();
                mPager.setCurrentItem(posFront + 1);
                break;

        }

    }
}
