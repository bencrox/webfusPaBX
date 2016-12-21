package com.example.vivienne.pabx;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Created by Vivienne on 12/22/2016.
 */
public class LogAdapter extends BaseAdapter {
    protected LayoutInflater inflator;
    private Context context;
    public JSONArray datasource;

    public LogAdapter(Context c,JSONArray dataSource) {
        context = c;
        inflator = LayoutInflater.from(c);
        datasource = dataSource;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {

        v = inflator.inflate(R.layout.log_item, parent, false);
        if (datasource==null || datasource.length()==0) {
            Log.e("PaBX", "cannot get any logs in LogAdapter.");
        } else {
            try {
                JSONObject d = datasource.getJSONObject(position);
                TextView exten = (TextView) v.findViewById(R.id.exten);
//                TextView contact = (TextView) v.findViewById(R.id.contract);
//                TextView date = (TextView) v.findViewById(R.id.time);
                exten.setText("exten : " + d.getString("exten"));
//                contact.setText(d.getString("price") + " " + d.getString("rent"));
//                date.setText(d.getString("modified"));
            } catch (Exception e) {
                Log.e("ORM", "Fav Adaptoer Get View " + e.toString());
            }


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return v;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public int getCount() {
        if (datasource==null) return 1;
        if (datasource.length()==0) { return 1;}
        return datasource.length();
    }
}
