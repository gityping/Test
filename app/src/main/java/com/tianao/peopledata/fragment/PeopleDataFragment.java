package com.tianao.peopledata.fragment;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.idtk.smallchart.chart.BarChart;
import com.idtk.smallchart.data.BarData;
import com.idtk.smallchart.interfaces.iData.IBarData;
import com.tianao.peopledata.R;
import com.tianao.peopledata.model.People;
import com.tianao.peopledata.util.ACache;
import com.tianao.peopledata.util.HistogramView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class PeopleDataFragment extends Fragment {
    @Bind(R.id.pie)
    PieChartView pie;
    @Bind(R.id.pie2)
    PieChartView pie2;
    @Bind(R.id.column)
    ColumnChartView column;
    @Bind(R.id.tv_all)
    TextView tv_all;
    @Bind(R.id.barChart)
    BarChart barChart;
//    @Bind(R.id.histogramView)
//    HistogramView histogramView;
//    @Bind(R.id.barChart)
//    BarChart barChart;

    private List<People> list = new ArrayList<>();
    private ACache aCache;
    private JSONArray peopleJsonArray;
    private int manCount = 0;
    private int womanCount = 0;
    private int innerCityCount = 0;
    private int outCityCount = 0;
    private int otherCityCount = 0;
    private int youngerCount = 0;
    private int oldCount=0;
    private List<Integer> data = new ArrayList<>();
    private ArrayList<IBarData> mDataList = new ArrayList<>();
    private BarData mBarData = new BarData();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        ButterKnife.bind(this, view);
        aCache = ACache.get(getActivity());
//        intx();
//        barChart.setDataList(mDataList);
//        barChart.setXAxisUnit("X单位");
//        barChart.setYAxisUnit("Y单位");
        return view;
    }

    private void intx() {
        for (int i = 0; i < 8; i++) {
            mPointArrayList.add(new PointF(3, 6));
        }
        mBarData.setValue(mPointArrayList);
        mBarData.setColor(Color.CYAN);
//        mBarData.setPaintWidth(5);
//        mBarData.setTextSize(pxTodp(10));

        mDataList.add(mBarData);
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        peopleJsonArray = aCache.getAsJSONArray("peopleList");
        if (null != peopleJsonArray) {
            for (int i = 0; i < peopleJsonArray.length(); i++) {
                People people = new People();
                try {
                    people.setId(peopleJsonArray.getJSONObject(i).getInt("id"));
                    people.setHz(peopleJsonArray.getJSONObject(i).getString("hz"));
                    people.setName(peopleJsonArray.getJSONObject(i).getString("name"));
                    people.setSex(peopleJsonArray.getJSONObject(i).getString("sex"));
                    people.setAge(peopleJsonArray.getJSONObject(i).getString("age"));
                    people.setHy(peopleJsonArray.getJSONObject(i).getString("hy"));
                    people.setTel(peopleJsonArray.getJSONObject(i).getString("tel"));
                    people.setCity(peopleJsonArray.getJSONObject(i).getString("city"));
                    people.setImg(peopleJsonArray.getJSONObject(i).getString("img"));
                    list.add(people);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        initView();
        List<Integer> listData = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            listData.add(i+10);
//            listData2.add(i+15);

        }
//        initBarChart();
//        showBarChart(listData,"名称",getResources().getColor(R.color.blue));
    }

    private void initBarChart() {
/***图表设置***/
//        //背景颜色
//        barChart.setBackgroundColor(Color.WHITE);
//        //不显示图表网格
//        barChart.setDrawGridBackground(false);
//        //背景阴影
//        barChart.setDrawBarShadow(false);
//        barChart.setHighlightFullBarEnabled(false);
//        //显示边框
//        barChart.setDrawBorders(true);
//        //设置动画效果
////        barChart.animateY(1000,);
////        barChart.animateX(1000,);
//
//        /***XY轴的设置***/
//        //X轴设置显示位置在底部
//        XAxis xAxis = barChart.getXAxis();
//        xAxis = barChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////        xAxis.setAxisMinimum(0f);
////        xAxis.setGranularity(1f);
//        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis = barChart.getAxisLeft();
//        YAxis rightAxis = barChart.getAxisRight();
//        rightAxis = barChart.getAxisRight();
//        //保证Y轴从0开始，不然会上移一点
////        leftAxis.setAxisMinimum(0f);
////        rightAxis.setAxisMinimum(0f);
//
////        /***折线图例 标签 设置***/
////        legend = barChart.getLegend();
////        legend.setForm(Legend.LegendForm.LINE);
////        legend.setTextSize(11f);
////        //显示位置
////        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
////        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
////        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
////        //是否绘制在图表里面
////        legend.setDrawInside(false);
//
//
//        //不显示表边框
//        barChart.setDrawBorders(false);
////        不显示右下角描述内容
//        Description description = new Description();
//        description.setEnabled(false);
//        barChart.setDescription(description);
////        不显示X轴 Y轴线条
//        xAxis.setDrawAxisLine(false);
//        leftAxis.setDrawAxisLine(false);
//        rightAxis.setDrawAxisLine(false);
//        leftAxis.setEnabled(false);
    }
//    public void showBarChart(List<Integer> dateValueList, String name, int color) {
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        for (int i = 0; i < dateValueList.size(); i++) {
//            /**
//             * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
//             * 即可设置柱状图顶部的 icon展示
//             */
//            BarEntry barEntry = new BarEntry(i, (float) dateValueList.get(i));
//            entries.add(barEntry);
//        }
//        // 每一个BarDataSet代表一类柱状图
//        BarDataSet barDataSet = new BarDataSet(entries, name);
////        initBarDataSet(barDataSet, color);
//
//        BarData data = new BarData(barDataSet);
//        barChart.setData(data);
//    }

    private void initView() {
        tv_all.setText("总共" + list.size() + "人");
        //先创造点数据
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSex().equals("男")) {
                manCount++;
            }else {
                womanCount++;
            }
            if (list.get(i).getCity().equals("市内")) {
                innerCityCount++;
            } else if (list.get(i).getCity().equals("市外")) {
                outCityCount++;
            } else {
                otherCityCount++;
            }
            if (Integer.valueOf(list.get(i).getAge()) < 18) {
                youngerCount++;
            } else {
                oldCount++;
            }
        }
        List<SliceValue> values=new ArrayList<>();
        SliceValue sliceValue=new SliceValue((float) manCount,0xff0000ff);
        sliceValue.setLabel("男");
        SliceValue sliceValue1=new SliceValue((float) womanCount,0xff00ff00);
        sliceValue1.setLabel("女");
        values.add(sliceValue);
        values.add(sliceValue1);
        PieChartData  data=new PieChartData(values);
        data.setHasLabels(true);
        pie.setChartRotationEnabled(true);
        pie.setPieChartData(data);

        List<SliceValue> values1=new ArrayList<>();
        SliceValue sliceValue2=new SliceValue((float) innerCityCount,0xff0000ff);
        sliceValue2.setLabel("市内");
        SliceValue sliceValue3=new SliceValue((float) outCityCount,0xff00ff00);
        sliceValue3.setLabel("市外");
        SliceValue sliceValue4=new SliceValue((float) otherCityCount,0xffffff00);
        sliceValue4.setLabel("其他");
        values1.add(sliceValue2);
        values1.add(sliceValue3);
        values1.add(sliceValue4);
        PieChartData  data1 =new PieChartData(values1);
        data1.setHasLabels(true);
        data.setHasLabelsOutside(true);
        pie2.setPieChartData(data1);

//        setChartViewData(column);
//        List<HistogramView.Histogram> myData = new ArrayList<>();
//        HistogramView.Histogram histogram = new HistogramView.Histogram();
//        histogram.setValue(manCount);
//        histogram.setValueName("男");
//        histogram.setValue(womanCount);
//        histogram.setValueName("女");
//        histogram.setValue(innerCityCount);
//        histogram.setValueName("在家");
//        histogram.setValue(outCityCount);
//        histogram.setValueName("外出");
//        histogram.setValue(youngerCount);
//        histogram.setValueName("儿童");
//        histogram.setValue(oldCount);
//        histogram.setValueName("成人");
//        myData.add(histogram);
//        histogramView.setData(myData);

    }
    public void setChartViewData(ColumnChartView chart) {
        data.add(manCount);
        data.add(womanCount);
        data.add(innerCityCount);
        data.add(outCityCount);
        data.add(youngerCount);
        data.add(oldCount);

        //底部标题
        List<String> title = new ArrayList<>();
        //颜色值
        List<Integer> color = new ArrayList<>();
        //X、Y轴值list
        List<AxisValue> axisXValues = new ArrayList<>();
        List<SubcolumnValue> mPointValues = new ArrayList<>();
        //所有的柱子
        List<Column> columns = new ArrayList<>();
        //单个柱子
        Column column = new Column();

        title.add("男");
        title.add("女");
        title.add("在家");
        title.add("外出");
        title.add("儿童");
        title.add("成人");
//        title.add("星期日");

        //颜色值
        color.add(Color.parseColor("#009BDB"));
        color.add(Color.parseColor("#85B74F"));
        color.add(Color.parseColor("#ff0000"));
        color.add(Color.parseColor("#ff0000"));
        color.add(Color.parseColor("#ff0000"));
        color.add(Color.parseColor("#ff0000"));
//        mPointValues.add(new SubcolumnValue((float) data.get(0), color.get(0)));
//        mPointValues.add(new SubcolumnValue((float) data.get(1), color.get(1)));
        //显示几个小柱子 这里为3
        for (int i = 0; i < 6; i++) {
            //值的大小、颜色
            mPointValues.add(new SubcolumnValue((float) data.get(i), color.get(i)));
        }

        //对每个集合的柱子进行遍历
        for (int i = 0; i < 1; i++) {
            //设置X轴的柱子所对应的属性名称(底部文字)
            axisXValues.add(new AxisValue(i).setLabel("男"+"  "+"女"+"  "+"在家"+"  "+"外出"+"  "+"儿童"+"  "+"成人"));
            //将每个属性得列全部添加到List中
            //添加了7个大柱子Column,单个大柱子里面mPointValues大小为3（自行调整)
            columns.add(column);
        }

        //是否显示每个柱子的标签
        column.setHasLabels(true);
        //设置每个柱子的Lable是否选中，为false，表示不用选中，一直显示在柱子上
        column.setHasLabelsOnlyForSelected(false);
        //设置Columns添加到Data中
        ColumnChartData columnData = new ColumnChartData(columns);
        column.setValues(mPointValues);

        //底部
        Axis axisBottom = new Axis(axisXValues);
        //是否显示X轴的网格线
        axisBottom.setHasLines(false);
        //分割线颜色
        axisBottom.setLineColor(Color.parseColor("#ff0000"));
        //字体颜色
        axisBottom.setTextColor(Color.parseColor("#666666"));
        //字体大小
        axisBottom.setTextSize(10);
        //底部文字
        axisBottom.setName("人员分布情况");
        //每个柱子的便签是否倾斜着显示
        axisBottom.setHasTiltedLabels(true);
        //距离各标签之间的距离,包括离Y轴间距 (0-32之间)
        axisBottom.setMaxLabelChars(10);
        //设置是否自动生成轴对象,自动适应表格的范围(设置之后底部标题变成0-5)
        //axisBottom.setAutoGenerated(true);
        axisBottom.setHasSeparationLine(true);
        //设置x轴在底部显示
        columnData.setAxisXBottom(axisBottom);

        //左边  属性与上面一致
        Axis axisLeft = new Axis();
        axisLeft.setHasLines(false);
        axisLeft.setName("人数");
        axisLeft.setHasTiltedLabels(true);
        axisLeft.setTextColor(Color.parseColor("#666666"));
        columnData.setAxisYLeft(axisLeft);

        //设置数据标签的字体大小
        //data.setValueLabelTextSize(12);
        //设置数据标签的字体颜色
        //data.setValueLabelsTextColor(Color.WHITE);
        //设置数据背景是否跟随节点颜色
        //data.setValueLabelBackgroundAuto(true);
        //设置是否有数据背景  是否跟随columvalue的颜色变化
        // data.setValueLabelBackgroundEnabled(true);
        //设置坐标点旁边的文字背景(...)
        //data.setValueLabelBackgroundColor(Color.YELLOW);
        //axisBottom.setMaxLabelChars(5);
        //设置组与组之间的间隔比率,取值范围0-1,1表示组与组之间不留任何间隔
        columnData.setFillRatio(0.7f);
        chart.setInteractive(false);
        //最后将所有值显示在View中
        chart.setColumnChartData(columnData);
    }

}
