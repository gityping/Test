package com.tianao.peopledata.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tianao.peopledata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity {
//    private ACache aCache;
//    private List<Feedback> list = new ArrayList<>();
    private JSONArray jsonArray;
    private JSONArray feedbackJsonArray;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.et_content)
    EditText et_content;
    @Bind(R.id.et_contact)
    EditText et_contact;
    @Bind(R.id.bt_commit)
    Button bt_commit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
//        aCache = ACache.get(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        feedbackJsonArray = aCache.getAsJSONArray("feedbackList");
//        if (null != feedbackJsonArray) {
//            for (int i = 0; i < feedbackJsonArray.length(); i++) {
//                Feedback feedback = new Feedback();
//                try {
//                    feedback.setContent(feedbackJsonArray.getJSONObject(i).getString("content"));
//                    feedback.setDate(feedbackJsonArray.getJSONObject(i).getString("date"));
//                    list.add(feedback);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private void initView() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        tvAdd = findViewById(R.id.tv_add);
//        tvAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                Feedback feedback = new Feedback();
//                feedback.setContent(etContent.getText().toString().trim());
//                feedback.setDate(df.format(new Date()));
//                list.add(feedback);
//                Log.e("Contact=", list.toString());
//                jsonArray = new JSONArray();
//                for (int i = 0; i < list.size(); i++) {
//                    JSONObject object = new JSONObject();
//                    try {
//                        object.put("content", list.get(i).getContent());
//                        object.put("date", list.get(i).getDate());
//                        jsonArray.put(object);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                aCache.put("feedbackList", jsonArray);
//                finish();
//            }
//        });
    }
}
