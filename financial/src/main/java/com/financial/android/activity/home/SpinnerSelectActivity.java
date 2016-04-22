package com.financial.android.activity.home;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.Person;
import com.financial.android.bean.ProvinceBean;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wyy on 2016/4/7.
 */
public class SpinnerSelectActivity extends BaseActivity {

    private Spinner spinner_country;
    private Spinner spinner_school;
    private Spinner spinner_city;
    private List<Person> personList;
    ArrayAdapter<String> cityAdapter = null;    //地级适配器
    ArrayAdapter<String> schoolAdapter = null;    //县级适配器
    private int cityPosition=3;
    private String[] province;
    private String[][] city;
    private Button btn_time_select;
    private Button btn_city_select;
    private TimePickerView pickerView;
    private ArrayList<ProvinceBean> options1Items;
    private ArrayList<ArrayList<String>> options2Items;
    private ArrayList<ArrayList<ArrayList<String>>> options3Items;
    private OptionsPickerView optionsPickerView;
    //三级联动


    @Override
    public void initView() {
        setContentView(R.layout.activity_select_spinner);
        initTitleBar();
        spinner_country = (Spinner) findViewById(R.id.spinner_country);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_school = (Spinner) findViewById(R.id.spinner_school);
        btn_time_select = (Button) findViewById(R.id.btn_time_select);
        btn_city_select = (Button) findViewById(R.id.btn_city_select);
        btn_time_select.setOnClickListener(this);
        btn_city_select.setOnClickListener(this);

        pickerView = new TimePickerView(ct, TimePickerView.Type.YEAR_MONTH_DAY);

        //控制时间范围
        Calendar calendar=Calendar.getInstance();
        pickerView.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pickerView.setTime(new Date());
        pickerView.setCyclic(false);
        pickerView.setCancelable(true);
        pickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                btn_time_select.setText(getTime(date));
            }
        });
        //弹出时间选择器





    }

    private void initTitleBar() {
        TextView bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        RelativeLayout bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setOnClickListener(this);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_tv_title.setText("条件查询");

    }

    @Override
    public void initData() {

        options1Items = new ArrayList<ProvinceBean>();
        options2Items = new ArrayList<ArrayList<String>>();
        options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

        optionsPickerView = new OptionsPickerView(ct);
        //选项1
        options1Items.add(new ProvinceBean(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
        options1Items.add(new ProvinceBean(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
        options1Items.add(new ProvinceBean(3,"广西","嗯～～",""));

        //选项2
        ArrayList<String> options2Items_01=new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02=new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03=new ArrayList<String>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);



        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01=new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02=new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03=new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04=new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05=new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01=new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02=new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01=new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);



        personList = new ArrayList<Person>();
        personList.add(new Person("a","北京"));
        personList.add(new Person("a","上海"));
        personList.add(new Person("a", "天津"));
        //三级联动效果
        optionsPickerView.setPicker(options1Items,options2Items,options3Items,true);
        //设置选择的三级单位
        optionsPickerView.setLabels("省","市","区");
        optionsPickerView.setTitle("选择城市");
        optionsPickerView.setCyclic(false,true,true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        optionsPickerView.setSelectOptions(1, 1, 1);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                btn_city_select.setText(tx);
//                vMasker.setVisibility(View.GONE);
            }
        });

        SpinnerAdapter adapter=new SpinnerAdapter(ct,personList,spinner_country);


        spinner_country.setAdapter(adapter);
        spinner_country.setGravity(Gravity.CENTER);
       spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               showToast("position" + personList.get(position).toString());
               //将地级适配器的值改变为city[position]中的值
               cityAdapter = new ArrayAdapter<String>(ct,
                       android.R.layout.simple_spinner_item, city[position]);
               // 设置二级下拉列表的选项内容适配器
               spinner_school.setAdapter(cityAdapter);
               cityPosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        initSpinner();

    }

    private void initSpinner() {
        //省级选项值
        province = new String[] {"北京","上海","天津","广东"};
        //地级选项值
        city = new String[][]
                 {
                         { "东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                                 "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
                                 "延庆县" },
                         { "长宁区", "静安区", "普陀区", "闸北区", "虹口区" },
                         { "和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区",
                                 "东丽区" },
                         { "广州", "深圳", "韶关" // ,"珠海","汕头","佛山","湛江","肇庆","江门","茂名","惠州","梅州",
                                 // "汕尾","河源","阳江","清远","东莞","中山","潮州","揭阳","云浮"
                         }
                 };


        cityAdapter = new ArrayAdapter<String>(ct,
                android.R.layout.simple_spinner_item, province);
        spinner_city.setAdapter(cityAdapter);
        spinner_city.setGravity(Gravity.CENTER);
        spinner_city.setSelection(3, true);  //设置默认选中项，此处为默认选中第4个值

        schoolAdapter = new ArrayAdapter<String>(ct,
                android.R.layout.simple_spinner_item, city[3]);
        spinner_school.setAdapter(schoolAdapter);
        spinner_city.setGravity(Gravity.CENTER_HORIZONTAL);
        spinner_school.setSelection(0, true);  //默认选中第0个


        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //position为当前省级选中的值的序号

                //将地级适配器的值改变为city[position]中的值
                cityAdapter = new ArrayAdapter<String>(ct,
                        android.R.layout.simple_spinner_item, city[position]);
                // 设置二级下拉列表的选项内容适配器
                spinner_school.setAdapter(cityAdapter);
                cityPosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        provinceSpinner.getSelectedItem().toString();
//
//        citySpinner.getSelectedItem().toString();
//
//        countySpinner.getSelectedItem().toString();

        spinner_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bar_rl_left:
                onBackPressed();

                break;
            case R.id.btn_city_select:
                optionsPickerView.show();
                break;
            case R.id.btn_time_select:
                pickerView.show();
                break;
            default:
                break;
        }
    }

    class SpinnerAdapter extends FXBaseAdapter<Person,Spinner>
    {
        private List<Person> persons;

        public SpinnerAdapter(Context context, List<Person> list, Spinner view) {
            super(context, list, view);
            this.persons=list;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            Person person=persons.get(position);
            if (convertView==null)
            {
                convertView=View.inflate(ct,R.layout.item_spinner,null);

                holder=new ViewHolder(person);
                ViewUtils.inject(holder, convertView);
                convertView.setTag(holder);
            }
            else
            {
                holder= (ViewHolder) convertView.getTag();
            }

            holder.tv_spinner_name.setText(person.getAddress());
            return convertView;
        }


        class ViewHolder
        {
            private Person person;
            @ViewInject(R.id.tv_spinner_name)
            private TextView tv_spinner_name;

            public ViewHolder(Person person) {
                this.person=person;
            }
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
