package com.financial.android.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.financial.android.R;
import com.financial.android.adapter.SortAdapter;
import com.financial.android.base.BaseActivity;
import com.financial.android.bean.SortModel;
import com.financial.android.utils.CharacterParser;
import com.financial.android.utils.PinyinComparator;
import com.financial.android.view.ClearEditText;
import com.financial.android.view.Sidebar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PositionActivity extends BaseActivity {


    private ClearEditText mClearEditText;
    private Sidebar sideBar;
    private ListView listview;
    private LayoutInflater infalter;
    private SortAdapter adapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private TextView floating_header;
    private RelativeLayout re_newfriends;
    private RelativeLayout re_chatroom;
    private TextView tv_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
    }

    @Override
    public void initView() {

        initTitleBar();

        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        sideBar = (Sidebar) findViewById(R.id.sidebar);
        floating_header = (TextView) findViewById(R.id.floating_header);
        listview = (ListView) findViewById(R.id.listView);
        infalter= LayoutInflater.from(ct);
        View headView = infalter.inflate(R.layout.item_contact_list_header,
                null);
        listview.addHeaderView(headView);
        View footerView = infalter.inflate(R.layout.item_contact_list_footer,
                null);
        listview.addFooterView(footerView);

        re_newfriends = (RelativeLayout) headView.findViewById(R.id.re_newfriends);
        re_chatroom = (RelativeLayout) headView.findViewById(R.id.re_chatroom);
        re_newfriends.setOnClickListener(this);
        re_chatroom.setOnClickListener(this);
        tv_total = (TextView) footerView.findViewById(R.id.tv_total);

        SourceDateList = filledData(getResources().getStringArray(R.array.date));
        tv_total.setText(SourceDateList.size() + "位联系人");
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        listview.setAdapter(adapter);
        sideBar.setTextView(floating_header);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                if (position != 0 && position != SourceDateList.size() + 1) {
                    Intent intent=new Intent();
                    intent.putExtra("cityName",((SortModel) adapter.getItem(position - 1)).getName());
                    setResult(10, intent);
                    PositionActivity.this.finish();
                    Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position - 1)).getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new Sidebar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listview.setSelection(position);
                }

            }
        });


        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * 设置标题
     */
    private void initTitleBar() {
        RelativeLayout bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_rl_left.setOnClickListener(this);
        TextView bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_tv_title.setText("城市选择");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(String [] date){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        SortModel sortModel;
        for(int i=0; i<date.length; i++){
            sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;


        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bar_rl_left:
                PositionActivity.this.finish();
                break;
            case R.id.re_chatroom:
                Toast.makeText(getApplication(), "chatroom", Toast.LENGTH_SHORT).show();
                break;
            case R.id.re_newfriends:
                Toast.makeText(getApplication(), "newfriends", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
