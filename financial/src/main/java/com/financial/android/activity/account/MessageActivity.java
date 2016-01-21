package com.financial.android.activity.account;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.Message;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


public class MessageActivity extends BaseActivity {

    private ListView listview_message;
    private List<Message> msgs = null;
    private MessageAdapter msgAdapter;
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;

    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
        initTitleBar();
        listview_message = (ListView) findViewById(R.id.listview_message);

    }


    /**
     * 我的消息
     */
    private void initTitleBar() {
        bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_rl_left.setOnClickListener(this);
        bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_tv_title.setText("消息");
    }


    @Override
    public void initData() {
        msgs = new ArrayList<Message>();
        Message msg1 = new Message("shabi", 1452555566);
        msgs.add(msg1);
        Message msg2 = new Message("shadan", 1452555552);
        msgs.add(msg2);

        msgAdapter = new MessageAdapter(ct,msgs,listview_message);
        listview_message.setAdapter(msgAdapter);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId())
        {
            case R.id.bar_rl_left:
                finish();
                break;
            default:
                break;
        }
    }

    class MessageAdapter extends FXBaseAdapter<Message, ListView> {

        public MessageAdapter(Context context, List<Message> list, ListView view) {
            super(context, list, view);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Message msg=list.get(position);

            if (convertView == null) {
                convertView=View.inflate(context,R.layout.message_item,null);
                holder=new ViewHolder(msg);
                ViewUtils.inject(holder,convertView);
                convertView.setTag(holder);
            } else {
                convertView.getTag();
            }

            holder.tv_message.setText(msg.getMessage());
            return convertView;
        }
    }

    class ViewHolder {
        @ViewInject(R.id.tv_message)
        private TextView tv_message;
        private Message msg;

        public ViewHolder(Message msg) {
            this.msg = msg;
        }

    }
}
