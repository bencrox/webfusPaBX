package com.example.vivienne.pabx;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.in;

/**
 * Created by Vivienne on 12/21/2016.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnTouchListener{
    private LayoutInflater mInflator;
    private View v;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<LogRec> listDataHeader;
    ArrayList<LogRec> listDataChild;

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

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflator = inflater;
        v = inflater.inflate(R.layout.home_frag, container, false);

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
                "  \"remarks\":\"\"},{\n" +
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
                "  }";

        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.loglist);
        // preparing list data
        prepareListData(responseText);
        listAdapter = new LogAdapter(getContext(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);





        return v;
    }

    private void prepareListData(String responseText) {
        listDataHeader = new ArrayList<LogRec>();
        listDataChild = new ArrayList<LogRec>();
        LogRec temp =new LogRec();
        try{
            JSONObject jsonObj = new JSONObject(responseText);
            JSONArray logs = jsonObj.getJSONArray("logs");
            for(int i=0; i<logs.length();i++) {
                JSONObject d = logs.getJSONObject(i);

//                        String id, String exten, String dest, String caller,
//                        String initime, String picktime, String endtime, String hangupcause,
//                        String voicefile_id, String caller_name, String notes,
//                        String dtmf, String remarks

                if (d.has("id")){
                    temp.setId( d.getString("id"));
                }
                if (d.has("exten")){
                    temp.setExten( d.getString("exten"));
                }
                if (d.has("dest")){
                    temp.setDest( d.getString("dest"));
                }
                if (d.has("caller")){
                    temp.setCaller( d.getString("caller"));
                }
                if (d.has("initime")){
                    temp.setInitime( d.getString("initime"));
                }
                if (d.has("picktime")){
                    temp.setPicktime( d.getString("picktime"));
                }
                if (d.has("endtime")){
                    temp.setEndtime( d.getString("endtime"));
                }
                if (d.has("hangupcause")){
                    temp.setHangupcause( d.getString("hangupcause"));
                }
                if (d.has("voicefile_id")){
                    temp.setVoicefile_id( d.getString("voicefile_id"));
                }
                if (d.has("caller_name")){
                    temp.setCaller_name( d.getString("caller_name"));
                }
                if (d.has("notes")){
                    temp.setNotes( d.getString("notes"));
                    System.out.println("listDataHeader add: " +d.getString("notes"));
                    System.out.println("listDataHeader add temp: " +temp.getNotes());
                }
                if (d.has("dtmf")){
                    temp.setDtmf( d.getString("dtmf"));
                }
                if (d.has("remarks")){
                    temp.setRemarks( d.getString("remarks"));
                }

//                System.out.println("listDataHeader add: " +d.getString("notes"));
//                System.out.println("listDataHeader add temp: " +temp.getNotes());
                listDataHeader.add(temp);
                listDataChild.add(temp);

            }
        } catch (JSONException e) {
            System.err.println("JSONException: HomeFrag");
            e.printStackTrace();
        }
        System.out.println("list index: " );
        System.out.println(listDataChild.get(0).getNotes());
        System.out.println(listDataChild.get(1).getNotes());
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
