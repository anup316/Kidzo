package activity.kids.com.kidzo;

import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import activity.kids.com.kidzo.adapters.AlphabetPagerAdapter;
import activity.kids.com.kidzo.adapters.ZoomOutPageTransformer;
import activity.kids.com.kidzo.fragments.AlphabetFragment;


public class AlphabetActivity extends AppCompatActivity implements AlphabetFragment.OnFragmentInteractionListener, View.OnClickListener {

    private ViewPager mPager;
    private ImageButton mBtnBack;
    private ImageButton mBtnNext;
    private TextView mTxvTitle;
    private int mPosition;
    private Handler mHandler;
    JSONArray alphabetDetailArray;
    private TextToSpeech mSpeak;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        mBtnNext = (ImageButton) findViewById(R.id.btnNext);
        mBtnNext.setOnClickListener(this);
        mBtnBack = (ImageButton) findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(this);
        mTxvTitle = (TextView) findViewById(R.id.txv_title);
        try {
            alphabetDetailArray = new JSONArray(loadAlphabetDataFromAsset());

            /*for (int i=0; i<alphabetDetailArray.length();i++)
            {
                JSONObject object =alphabetDetailArray.getJSONObject(i);

                JSONObject alphObj=object.getJSONObject(String.valueOf(i));

                 String colorName=alphObj.getString("color");
                 String alphabetName=alphObj.getString("alphabet");
                Log.v("COLOR",colorName+alphabetName);
            }
*/
            mSpeak = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        mSpeak.setLanguage(Locale.US);
                        mSpeak.setSpeechRate((float) 0.8);
                        mSpeak.speak("A for apple", TextToSpeech.QUEUE_FLUSH, null);
                        mTxvTitle.setText("A for apple");
                    }
                }
            });

            mPager = (ViewPager) findViewById(R.id.viewPagerAlphabet);
            mPager.setAdapter(new AlphabetPagerAdapter(getSupportFragmentManager(), alphabetDetailArray));
            mPager.setPageTransformer(true, new ZoomOutPageTransformer());
            mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                }

                @Override
                public void onPageSelected(int position) {

                    Log.d("VIEWPAGER", String.valueOf(position));

                    try {
                        JSONObject alphabetDetails = alphabetDetailArray.getJSONObject(position);
                        String alphabetName = alphabetDetails.getString("soundtext");
                        mTxvTitle.setText(alphabetName);
                        mSpeak.speak(alphabetName, TextToSpeech.QUEUE_FLUSH, null);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


            mPosition = 0;
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if(!mSpeak.isSpeaking()) {
                        int pos = mPager.getCurrentItem();
                        if (pos + 1 == 26)
                            pos = -1;
                        mPager.setCurrentItem(pos + 1);
                    }
                }
            };
            mTimer = new Timer();
            TimerTask timerTask;
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = mHandler.obtainMessage();
                    mHandler.sendMessage(msg);
                }
            };
            mTimer.schedule(timerTask, 6000, 6000);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alphabet, menu);
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
    public void onFragmentInteraction(int position) {
        mPosition = position;

    }

    @Override
    public void onStop() {
        super.onStop();
        mTimer.cancel();

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

    private String loadAlphabetDataFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("alphabets.json");
            int size = is.available();

            byte buffer[] = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


}
