package activity.kids.com.kidzo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import activity.kids.com.kidzo.utils.Constants;


public class NamesMenuActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton mBtnMonths;
    private ImageButton mBtnDays;
    private ImageButton mBtnAnimals;
    private ImageButton mBtnBirds;
    private ImageButton mBtnVehicles;
    private ImageButton mBtnColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        mBtnMonths=(ImageButton)findViewById(R.id.btn_months);
        mBtnMonths.setOnClickListener(this);
        mBtnDays=(ImageButton)findViewById(R.id.btn_days);
        mBtnDays.setOnClickListener(this);
        mBtnAnimals=(ImageButton)findViewById(R.id.btn_animals);
        mBtnAnimals.setOnClickListener(this);
        mBtnBirds=(ImageButton)findViewById(R.id.btn_birds);
        mBtnBirds.setOnClickListener(this);
        mBtnVehicles=(ImageButton)findViewById(R.id.btn_vehicles);
        mBtnVehicles.setOnClickListener(this);
        mBtnColors=(ImageButton)findViewById(R.id.btn_colors);
        mBtnColors.setOnClickListener(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.push_in_up);
        mBtnAnimals.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.push_in_up);
        mBtnBirds.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        mBtnMonths.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        mBtnDays.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.push_in_down);
        mBtnVehicles.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.push_in_down);
        mBtnColors.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_names, menu);
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
        Intent intentData=new Intent(getBaseContext(),NamesHomeActivity.class);
        switch (view.getId())
        {
            case R.id.btn_months:
                intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_MONTH);
                startActivity(intentData);
                break;
            case R.id.btn_birds:  intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_BIRD);
                startActivity(intentData);
                break;
            case R.id.btn_animals:  intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_ANIMAl);
                startActivity(intentData);
                break;
            case R.id.btn_days:  intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_DAY);
                startActivity(intentData);
                break;
            case R.id.btn_vehicles:  intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_VEHICLE);
                startActivity(intentData);
                break;
            case R.id.btn_colors:  intentData.putExtra(Constants.INTENT_DATA_NAMES_KEY,Constants.INTENT_DATA_COLOR);
                startActivity(intentData);
                break;
        }

    }
}
