package com.example.vivienne.pabx;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vivienne on 12/21/2016.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnTouchListener{
    private LayoutInflater mInflator;
    private View v;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        screenWidth = displaymetrics.widthPixels;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflator = inflater;
        v = inflater.inflate(R.layout.home_frag, container, false);
//        System.out.println("reach homeFrag");

        // hard code the log, should come from system status
        String responseText = "{\"logs\":\n" +
                " [{\n" +
                "  \"id\":\"22fed4c5-ccff-4275-aa5f-b1e8982fde05\",\n" +
                "  \"exten\":\"31115191\",\n" +
                "  \"dest\":\"68306283\",\n" +
                "  \"caller\":\"31840680\",\n" +
                "  \"intime\":\"1482201317\",\n" +
                "  \"picktime\":\"1482201319\",\n" +
                "  \"endtime\":\"1482201343\",\n" +
                "  \"hangupcause\":\"16\",\n" +
                "  \"voicefile_id\":\"528789e6-3117-46d2-9681-8d51b91c13a7\",\n" +
                "  \"caller_name\":\"Ben Crox\",\n" +
                "  \"notes\":\"Test call\",\n" +
                "  \"dtmf\":\"\",\n" +
                "  \"remarks\":\"\"}],\n" +
                "  \"count\":1,\n" +
                "  \"status\":{\"line\":2,\"ringing\":0,\"talking\":0,\"voicemail\":1}\n" +
                "  }\n";
        try{
            JSONObject jsonObj = new JSONObject(responseText);
            JSONArray logs = jsonObj.getJSONArray("logs");
            ListView g1 = (ListView) v.findViewById(R.id.loglist);
            LogAdapter d1 = new LogAdapter(this.getActivity(),logs);
            g1.setAdapter(d1);

        } catch (JSONException e) {
            System.err.println("JSONException: MainActivity");
            e.printStackTrace();
        }

        return v;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return false;
    }

}
