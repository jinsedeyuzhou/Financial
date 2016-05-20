package com.financial.android.bean;

/**
 * Created by wyy on 2016/2/4.
 */
public class Product {
    //项目ID
    private String id;
    //项目名称
    private String item_prefix;
    //项目编号
    private String item_name;
    //特殊标记
    private String special_flag;
    private String status;
    //最低限额
    private double minInvest;
    //年化率
    private double invester_year_rate;
    //投资期限
    private String finance_period;
    //进度
    private String progress;
    //项目到期日
    private long finance_end_date;
    //项目起息日
    private long interest_start_date;
    //项目开始时间
    private long invest_start_date;
    //项目结束时间
    private long invest_end_date;
    //最大投资金额
    private double max_finance_money;
    //剩余金额
    private double suplus;
    //是否可以转让
    private String assign;

    public Product() {
    }



    public Product(String id, String finance_period, double max_finance_money, double invester_year_rate, String item_prefix, String item_name, String status, String progress, double suplus, double minInvest, String special_flag)
    {
        this.id = id;
        this.item_prefix = item_prefix;
        this.special_flag = special_flag;
        this.item_name = item_name;
        this.status = status;
        this.minInvest = minInvest;
        this.invester_year_rate = invester_year_rate;
        this.finance_period = finance_period;
        this.progress = progress;
        this.max_finance_money = max_finance_money;
        this.suplus = suplus;
    }
    public Product(String id, String finance_period, double max_finance_money, double invester_year_rate, String item_prefix, String item_name, String status, String progress, double suplus, double minInvest, String special_flag, String assign)
    {
        this.id = id;
        this.item_prefix = item_prefix;
        this.special_flag = special_flag;
        this.item_name = item_name;
        this.status = status;
        this.minInvest = minInvest;
        this.invester_year_rate = invester_year_rate;
        this.finance_period = finance_period;
        this.progress = progress;
        this.max_finance_money = max_finance_money;
        this.suplus = suplus;
        this.assign=assign;
    }


    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public double getSuplus() {
        return suplus;
    }

    public void setSuplus(double suplus) {
        this.suplus = suplus;
    }

    public String getFinance_period() {
        return finance_period;
    }

    public void setFinance_period(String finance_period) {
        this.finance_period = finance_period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getInvester_year_rate() {
        return invester_year_rate;
    }

    public void setInvester_year_rate(double invester_year_rate) {
        this.invester_year_rate = invester_year_rate;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_prefix() {
        return item_prefix;
    }

    public void setItem_prefix(String item_prefix) {
        this.item_prefix = item_prefix;
    }

    public double getMinInvest() {
        return minInvest;
    }

    public void setMinInvest(double minInvest) {
        this.minInvest = minInvest;
    }

    public String getSpecial_flag() {
        return special_flag;
    }

    public void setSpecial_flag(String special_flag) {
        this.special_flag = special_flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //列表


    public long getFinance_end_date() {
        return finance_end_date;
    }

    public void setFinance_end_date(long finance_end_date) {
        this.finance_end_date = finance_end_date;
    }

    public long getInterest_start_date() {
        return interest_start_date;
    }

    public void setInterest_start_date(long interest_start_date) {
        this.interest_start_date = interest_start_date;
    }

    public long getInvest_end_date() {
        return invest_end_date;
    }

    public void setInvest_end_date(long invest_end_date) {
        this.invest_end_date = invest_end_date;
    }

    public long getInvest_start_date() {
        return invest_start_date;
    }

    public void setInvest_start_date(long invest_start_date) {
        this.invest_start_date = invest_start_date;
    }

    public double getMax_finance_money() {
        return max_finance_money;
    }

    public void setMax_finance_money(double max_finance_money) {
        this.max_finance_money = max_finance_money;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Product{" +
                "finance_end_date=" + finance_end_date +
                ", id='" + id + '\'' +
                ", item_prefix='" + item_prefix + '\'' +
                ", item_name='" + item_name + '\'' +
                ", special_flag='" + special_flag + '\'' +
                ", status='" + status + '\'' +
                ", minInvest='" + minInvest + '\'' +
                ", invester_year_rate='" + invester_year_rate + '\'' +
                ", finance_period='" + finance_period + '\'' +
                ", progress='" + progress + '\'' +
                ", interest_start_date=" + interest_start_date +
                ", invest_start_date=" + invest_start_date +
                ", invest_end_date=" + invest_end_date +
                ", max_finance_money=" + max_finance_money +
                '}';
    }
}
