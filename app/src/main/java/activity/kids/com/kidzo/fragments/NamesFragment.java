package activity.kids.com.kidzo.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import activity.kids.com.kidzo.R;
import activity.kids.com.kidzo.utils.Constants;

public class NamesFragment extends Fragment {

    private int mPosition;
    private String mCategory;
    private final int mCalendarNames[]={
            R.drawable.jan,R.drawable.feb,R.drawable.mar,R.drawable.apr,R.drawable.may,R.drawable.jun,
            R.drawable.jul,R.drawable.aug,
            R.drawable.sep,R.drawable.oct,R.drawable.nov,R.drawable.dec};

    private final int mAnimalNames[]={
            R.drawable.bear,R.drawable.buffalo,R.drawable.cow,R.drawable.dinosaur,R.drawable.dog,R.drawable.giraff,
            R.drawable.kangaaro,R.drawable.lion,
            R.drawable.sheep,R.drawable.tiger};

    private final int mBirdNames[]={
            R.drawable.crow,R.drawable.eagle,R.drawable.hen,R.drawable.humming,R.drawable.kingfisher,R.drawable.owl,
            R.drawable.peacock,R.drawable.pigeon,
            R.drawable.sparrow,R.drawable.vulture};

    private final int[] mColorHexValues={android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_blue_dark,
            android.R.color.white,android.R.color.holo_orange_dark,android.R.color.holo_purple,android.R.color.darker_gray,
            android.R.color.black,R.color.yellow,R.color.pink};

    private final int[] mWeekdaysName={R.drawable.mon,R.drawable.tue,R.drawable.wed,R.drawable.thur,R.drawable.fri
                                        ,R.drawable.sat,R.drawable.sun};

    private final int[] mVehicles={R.drawable.bicycle,R.drawable.motorcycle,R.drawable.car,R.drawable.van,
            R.drawable.truck,R.drawable.tractor,R.drawable.plane,R.drawable.helicopter,R.drawable.ships,R.drawable.train};


    public static NamesFragment newInstance(int pos,String category) {
        NamesFragment fragment = new NamesFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(Constants.FRAGMENT_POSITION,pos);
        bundle.putString(Constants.INTENT_DATA_NAMES_KEY,category);
        fragment.setArguments(bundle);
        return fragment;
    }

    public NamesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null) {
            mPosition=getArguments().getInt(Constants.FRAGMENT_POSITION);
            mCategory=getArguments().getString(Constants.INTENT_DATA_NAMES_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_names, container, false);
        ImageView imgCal=(ImageView)view.findViewById(R.id.img_calendar);

        if(mCategory.equals(Constants.INTENT_DATA_ANIMAl))
            imgCal.setImageDrawable(getResources().getDrawable(mAnimalNames[mPosition]));
        else if(mCategory.equals(Constants.INTENT_DATA_BIRD))
            imgCal.setImageDrawable(getResources().getDrawable(mBirdNames[mPosition]));
        else if(mCategory.equals(Constants.INTENT_DATA_COLOR))
            imgCal.setBackgroundResource(mColorHexValues[mPosition]);
        else if(mCategory.equals(Constants.INTENT_DATA_DAY))
            imgCal.setImageDrawable(getResources().getDrawable(mWeekdaysName[mPosition]));
        else if(mCategory.equals(Constants.INTENT_DATA_VEHICLE))
            imgCal.setImageDrawable(getResources().getDrawable(mVehicles[mPosition]));
            else
            imgCal.setBackgroundDrawable(getResources().getDrawable(mCalendarNames[mPosition]));
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
