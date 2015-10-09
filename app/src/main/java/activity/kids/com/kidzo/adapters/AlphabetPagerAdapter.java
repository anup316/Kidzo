package activity.kids.com.kidzo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import activity.kids.com.kidzo.fragments.AlphabetFragment;

/**
 * Created by Anup.Lal on 17-08-2015.
 */
public class AlphabetPagerAdapter extends FragmentPagerAdapter {

    private JSONArray mAlphabetArray;
    public AlphabetPagerAdapter(FragmentManager fm, JSONArray alphabetArray)
    {
        super(fm);
        mAlphabetArray=alphabetArray;
    }

    @Override
    public Fragment getItem(int position) {

       JSONObject alphabetData=getAlphabetDetails(position);
        String alphabetName="";
        String alphabetColor="";
        try {
            alphabetName=alphabetData.getString("alphabet");
            alphabetColor=alphabetData.getString("color");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return AlphabetFragment.newInstance(position,alphabetName,alphabetColor);
    }

    public  JSONObject getAlphabetDetails(int pos)
    {
        try {
            JSONObject alphabetDetails=mAlphabetArray.getJSONObject(pos);
            return alphabetDetails;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getCount() {
        return 26;
    }
}
