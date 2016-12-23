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

//        if (v== null) {
//            LayoutInflater infalInflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = infalInflater.inflate(R.layout.log_item, null);
//        }

        TextView dest = (TextView) v.findViewById(R.id.dest);
        TextView notes = (TextView) v.findViewById(R.id.notes);

        TextView caller = (TextView) v.findViewById(R.id.caller);
        TextView caller_name = (TextView) v.findViewById(R.id.caller_name);
        TextView intime = (TextView) v.findViewById(R.id.intime);
        TextView duration = (TextView) v.findViewById(R.id.duration);
//        TextView picktime = (TextView) v.findViewById(R.id.picktime);

//
        dest.setText("Destination : " + _listDataHeader.get(groupPosition).getDest()+" ");
        notes.setText("Notes : " + _listDataHeader.get(groupPosition).getNotes());

        caller.setText("Caller : " + _listDataHeader.get(groupPosition).getCaller() +" ");
        caller_name.setText(_listDataHeader.get(groupPosition).getCaller_name());
        intime.setText(_listDataHeader.get(groupPosition).getInitime());
//        picktime.setText("picktime : " + _listDataHeader.get(groupPosition).getPicktime());
        duration.setText("Duration  : " +
                (Integer.parseInt(_listDataHeader.get(groupPosition).getEndtime())-Integer.parseInt(_listDataHeader.get(groupPosition).getPicktime())));
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
//        TextView intime = (TextView) v.findViewById(R.id.intime);
//        intime.setText( _listDataHeader.get(groupPosition).getInitime());

        TextView exten = (TextView) v.findViewById(R.id.exten);
        TextView hangupcase = (TextView) v.findViewById(R.id.hangupcase);
        TextView voicefile_id = (TextView) v.findViewById(R.id.voicefile_id);
        TextView dtmf = (TextView) v.findViewById(R.id.dtmf);
        TextView remarks = (TextView) v.findViewById(R.id.remarks);

        exten.setText("exten : " + _listDataHeader.get(groupPosition).getExten());
        hangupcase.setText("hangupcase : " + _listDataHeader.get(groupPosition).getHangupcause());
        voicefile_id.setText("voicefile_id : " + _listDataHeader.get(groupPosition).getVoicefile_id());
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
