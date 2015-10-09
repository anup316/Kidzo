package activity.kids.com.kidzo.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import activity.kids.com.kidzo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlphabetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlphabetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlphabetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ALPHABETNAME = "alphabet";
    private static final String ARG_ALPHABETCOLOR = "color";
    private static final String ARG_PARAM2 = "pos";
    private String mAlphabetColor;
    private String mAlphabetName;
    private int mPosition;
    private TextView mCharacterCapsOn;
    private TextView mCharacterCapsOff;
    private ImageView mAlphabetImage;
    private OnFragmentInteractionListener mListener;
    private  JSONArray alphDetails;
    private final int alphabetImages[]={
            R.drawable.apples,R.drawable.ball,R.drawable.cat,R.drawable.doggy,R.drawable.elephant,R.drawable.frog,
            R.drawable.goat,R.drawable.horse,
            R.drawable.icecream,R.drawable.jug,R.drawable.kite,R.drawable.lamp,R.drawable.monkey,
            R.drawable.net,R.drawable.octopus,R.drawable.parrot,R.drawable.queen,R.drawable.rose,R.drawable.ship,
            R.drawable.tomato,R.drawable.umbrella,R.drawable.vann,R.drawable.watch,R.drawable.xmas,R.drawable.yak,
            R.drawable.zebra};


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AlphabetFragment.
     */

    public static AlphabetFragment newInstance(int position,String alphabet, String color ) {
        AlphabetFragment fragment = new AlphabetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM2, position);
        args.putString(ARG_ALPHABETNAME, alphabet);
        args.putString(ARG_ALPHABETCOLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    public AlphabetFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM2);
            mAlphabetColor = getArguments().getString(ARG_ALPHABETCOLOR);
            mAlphabetName = getArguments().getString(ARG_ALPHABETNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_alphabet, container, false);
        mCharacterCapsOn=(TextView)view.findViewById(R.id.txv_character_caps);
        mCharacterCapsOff=(TextView)view.findViewById(R.id.txv_character_nocaps);
        mCharacterCapsOn.setText(mAlphabetName);
        mCharacterCapsOn.setTextColor(Color.parseColor(mAlphabetColor));
        mCharacterCapsOff.setText(mAlphabetName.toLowerCase());
        mCharacterCapsOff.setTextColor(Color.parseColor(mAlphabetColor));
        mAlphabetImage=(ImageView)view.findViewById(R.id.img_alphabet);
        mAlphabetImage.setImageResource(alphabetImages[mPosition]);
        /*Animation mAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.scale);
        mAlphabetImage.setAnimation(mAnimation);*/
        mListener.onFragmentInteraction(mPosition);
        return view;
    }

  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int fragPosition);
    }

}
