package activity.kids.com.kidzo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;

import activity.kids.com.kidzo.fragments.NamesFragment;
import activity.kids.com.kidzo.utils.Constants;

/**
 * Created by Anup.Lal on 21-08-2015.
 */
public class NamesPagerAdapter extends FragmentPagerAdapter {

    private String mCategoryName;

    public NamesPagerAdapter(FragmentManager fm,String name)
    {
        super(fm);
        mCategoryName=name;
    }



    @Override
    public Fragment getItem(int position) {

        return NamesFragment.newInstance(position,mCategoryName);
    }

    @Override
    public int getCount() {

        if(mCategoryName.equals(Constants.INTENT_DATA_ANIMAl))
            return 10;
        else if(mCategoryName.equals(Constants.INTENT_DATA_BIRD))
            return 10;
        else if(mCategoryName.equals(Constants.INTENT_DATA_MONTH))
            return 12;
        else if(mCategoryName.equals(Constants.INTENT_DATA_DAY))
            return 7;
        else if(mCategoryName.equals(Constants.INTENT_DATA_COLOR))
            return 10;
        else return 10;

    }
}
