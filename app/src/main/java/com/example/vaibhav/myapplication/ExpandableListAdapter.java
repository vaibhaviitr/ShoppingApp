package com.example.vaibhav.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Tamoghna on 12-06-2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private JSONArray orders;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, JSONArray array) {
        this._context = context;
        this.orders = array;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return orders.optJSONObject(groupPosition).optJSONArray("orders").opt(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final JSONObject child = (JSONObject) getChild(groupPosition, childPosition);
        final String childnameText = child.optString("name");
        final Long childnumberText = child.optLong("quantity");
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        parent.setBackground(_context.getResources().getDrawable(R.mipmap.box));
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.orderitem);
        TextView txt = (TextView) convertView.findViewById(R.id.ordercount);
        txtListChild.setText(childnameText);
        txt.setText(String.valueOf(childnumberText));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return orders.optJSONObject(groupPosition).optJSONArray("orders").length();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return orders.optJSONObject(groupPosition).optString("_id");
    }

    @Override
    public int getGroupCount() {
        return orders.length();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
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
