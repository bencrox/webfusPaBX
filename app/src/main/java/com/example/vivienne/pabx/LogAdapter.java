package com.example.vivienne.pabx;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vivienne on 12/22/2016.
 */
public class LogAdapter extends BaseExpandableListAdapter  {
    protected LayoutInflater inflator;
    private Context context;
//    public JSONArray datasource;
    private ArrayList<LogRec> _listDataHeader; // header titles
    // child data in format of header title, child title
    private  ArrayList<LogRec> _listDataChild;
//    TableLayout detailTable;
    public LogAdapter(Context c, ArrayList<LogRec> listDataHeader,
                      ArrayList<LogRec> listChildData) {
        context = c;
//        inflator = LayoutInflater.from(c);
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                                  View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.log_item, null);
        }
//        v = inflator.inflate(R.layout.log_item, parent, false);
//        detailTable =(TableLayout) v.findViewById(R.id.detail_list);
//        detailTable.setVisibility(View.GONE);
//        if (datasource==null || datasource.length()==0) {
//            Log.e("PaBX", "cannot get any logs in LogAdapter.");
//        } else {
//            try {
//                JSONObject d = datasource.getJSONObject(groupPosition);
//                TextView exten = (TextView) v.findViewById(R.id.exten);
//                TextView dest = (TextView) v.findViewById(R.id.dest);
//                TextView notes = (TextView) v.findViewById(R.id.notes);
//
//                exten.setText("exten : " + d.getString("exten"));
//                dest.setText("dest : " + d.getString("dest"));
//                notes.setText("notes : " + d.getString("notes"));
//                contact.setText(d.getString("price") + " " + d.getString("rent"));
//                date.setText(d.getString("modified"));
//            } catch (Exception e) {
//                Log.e("PaBX",  "Fav Adaptoer Get View " + e.toString());
//            }


//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("PaBX", "view is click.");
////                    detailTable.setVisibility(detailTable.isShown() ? View.GONE : View.VISIBLE);
//
//
//
//                }
//            });
//        }
//        String headerTitle = (String) getGroup(groupPosition);
        if (v== null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.log_item, null);
        }

        TextView exten = (TextView) v.findViewById(R.id.exten);
        TextView dest = (TextView) v.findViewById(R.id.dest);
        TextView notes = (TextView) v.findViewById(R.id.notes);

        exten.setText("exten : " + _listDataHeader.get(groupPosition).getExten());
        dest.setText("dest : " + _listDataHeader.get(groupPosition).getDest());
        notes.setText("notes : " + _listDataHeader.get(groupPosition).getNotes());

        return v;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View v, ViewGroup parent) {

//        final String childText = (String) getChild(groupPosition, childPosition);

        if (v == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = infalInflater.inflate(R.layout.call_detail, null);
        }

        TextView caller = (TextView) v.findViewById(R.id.caller);
        TextView intime = (TextView) v.findViewById(R.id.intime);
        TextView picktime = (TextView) v.findViewById(R.id.picktime);
        TextView endtime = (TextView) v.findViewById(R.id.endtime);
        TextView hangupcase = (TextView) v.findViewById(R.id.hangupcase);
        TextView voicefile_id = (TextView) v.findViewById(R.id.voicefile_id);
        TextView caller_name = (TextView) v.findViewById(R.id.caller_name);
        TextView dtmf = (TextView) v.findViewById(R.id.dtmf);
        TextView remarks = (TextView) v.findViewById(R.id.remarks);

        caller.setText("caller : " + _listDataHeader.get(groupPosition).getCaller());
        intime.setText("intime : " + _listDataHeader.get(groupPosition).getInitime());
        picktime.setText("picktime : " + _listDataHeader.get(groupPosition).getPicktime());
        endtime.setText("endtime : " + _listDataHeader.get(groupPosition).getEndtime());
        hangupcase.setText("hangupcase : " + _listDataHeader.get(groupPosition).getHangupcause());
        voicefile_id.setText("voicefile_id : " + _listDataHeader.get(groupPosition).getVoicefile_id());
        caller_name.setText("caller_name : " + _listDataHeader.get(groupPosition).getCaller_name());
        dtmf.setText("dtmf : " + _listDataHeader.get(groupPosition).getDtmf());
        remarks.setText("remarks : " + _listDataHeader.get(groupPosition).getRemarks());
        return v;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        //do not use this function
        return 10;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
