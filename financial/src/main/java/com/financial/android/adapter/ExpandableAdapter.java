package com.financial.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.financial.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroups;
    private List<String>  mChilds;
    private List<List<String>> mlists;

    public ExpandableAdapter(Context mContext, List<String> mquests, List<String> manswers) {
        this.mContext = mContext;
        this.mGroups = mquests;
        this.mChilds = manswers;
    }

    public void setQuesList(List<String> quesList) {
        this.mGroups = quesList;
    }

    public void setAnswList(List<String> answList) {
        this.mChilds = answList;
    }

    public void setmList(List<String> answList) {
        for(int i = 0; i < answList.size(); i++){
            List<String> strings = new ArrayList<String>();
            strings.add(answList.get(i));
            mlists.add(strings);
        }
    }
    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChilds.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder quesHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandlist_group, null);
            quesHolder = new GroupViewHolder();
            quesHolder.tvQues = (TextView) convertView.findViewById(R.id.tv_question_expgroup);
            quesHolder.ivQues = (ImageView) convertView.findViewById(R.id.iv_ques_expgroup);
            convertView.setTag(quesHolder);
        }else {
            quesHolder = (GroupViewHolder) convertView.getTag();
        }

        if(!isExpanded){
            quesHolder.ivQues.setImageResource(R.drawable.ic_arrows_gray_down);
        }else {
            quesHolder.ivQues.setImageResource(R.drawable.ic_arrows_gray_up);
        }
        quesHolder.tvQues.setText(mGroups.get(groupPosition));
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder answHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandlist_child, null);
            answHolder = new ChildViewHolder();
            answHolder.tvAnsw = (TextView) convertView.findViewById(R.id.tv_answ_explist);
            convertView.setTag(answHolder);
        }else {
            answHolder = (ChildViewHolder) convertView.getTag();
        }
//        answHolder.tvAnsw.setText(mList.get(groupPosition).get(childPosition));
        answHolder.tvAnsw.setText(mChilds.get(groupPosition));
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class GroupViewHolder {
        TextView tvQues;
        ImageView ivQues;
    }
    static class ChildViewHolder {
        TextView tvAnsw;
    }
}
