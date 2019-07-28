package com.tianao.peopledata.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tianao.peopledata.R;
import com.tianao.peopledata.model.People;
import com.tianao.peopledata.util.ACache;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PeopleDetailsActivity extends AppCompatActivity {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.et_hz)
    EditText et_hz;
    @Bind(R.id.et_name)
    EditText et_name;
//    @Bind(R.id.et_sex)
//    EditText et_sex;
    @Bind(R.id.et_age)
    EditText et_age;
//    @Bind(R.id.et_hy)
//    EditText et_hy;
    @Bind(R.id.et_tel)
    EditText et_tel;
//    @Bind(R.id.et_city)
//    EditText et_city;
    @Bind(R.id.bt_add)
    Button bt_add;
    @Bind(R.id.tv_del)
    TextView tv_del;
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Bind(R.id.tv_hy)
    TextView tv_hy;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.iv_image)
    ImageView iv_image;
    private ACache aCache;
    private List<People> list = new ArrayList<>();
    private JSONArray jsonArray;
    private JSONArray peopleJsonArray;
    private AlertDialog.Builder builder;
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    private String imgUrl = "http://img2.imgtn.bdimg.com/it/u=1117035161,2826823365&fm=26&gp=0.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        aCache = ACache.get(this);
        ButterKnife.bind(this);
        Fresco.initialize(this);
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        initView();
    }

    @Override
    protected void onResume() {
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
    }

    private void initView() {
        et_hz.setText(getIntent().getStringExtra("hz"));
        et_name.setText(getIntent().getStringExtra("name"));
        tv_sex.setText(getIntent().getStringExtra("sex"));
        et_age.setText(getIntent().getStringExtra("age"));
        tv_hy.setText(getIntent().getStringExtra("hy"));
        et_tel.setText(getIntent().getStringExtra("tel"));
        tv_city.setText(getIntent().getStringExtra("city"));
        Glide.with(PeopleDetailsActivity.this).load(getIntent().getStringExtra("img")).into(iv_image);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = {"男", "女"};
                builder = new AlertDialog.Builder(PeopleDetailsActivity.this).setIcon(R.drawable.set_icon_name)
                        .setTitle("请选择性别")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_sex.setText(items[i]);
                            }
                        });
                builder.create().show();
            }
        });
        tv_hy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"已婚", "未婚", "离异"};
                builder = new AlertDialog.Builder(PeopleDetailsActivity.this).setIcon(R.drawable.set_icon_name)
                        .setTitle("请选择婚姻")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_hy.setText(items[i]);
                            }
                        });
                builder.create().show();
            }
        });
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"市内", "市外", "其他"};
                builder = new AlertDialog.Builder(PeopleDetailsActivity.this).setIcon(R.drawable.set_icon_name)
                        .setTitle("请选择婚姻")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_city.setText(items[i]);
                            }
                        });
                builder.create().show();
            }
        });
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(PeopleDetailsActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (getIntent().getIntExtra("id", 0) == list.get(i).getId()) {
                        list.get(i).setId(getIntent().getIntExtra("id", 0));
                        list.get(i).setHz(et_hz.getText().toString().trim());
                        list.get(i).setName(et_name.getText().toString().trim());
                        list.get(i).setSex(tv_sex.getText().toString().trim());
                        list.get(i).setAge(et_age.getText().toString().trim());
                        list.get(i).setHy(tv_hy.getText().toString().trim());
                        list.get(i).setTel(et_tel.getText().toString().trim());
                        list.get(i).setCity(tv_city.getText().toString().trim());
                        list.get(i).setImg(imgUrl);
                    }
                }
                jsonArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("id", list.get(i).getId());
                        object.put("hz", list.get(i).getHz());
                        object.put("name", list.get(i).getName());
                        object.put("sex", list.get(i).getSex());
                        object.put("age", list.get(i).getAge());
                        object.put("hy", list.get(i).getHy());
                        object.put("tel", list.get(i).getTel());
                        object.put("city", list.get(i).getCity());
                        object.put("img", list.get(i).getImg());
                        jsonArray.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                aCache.put("peopleList", jsonArray);
                finish();
            }
        });
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(PeopleDetailsActivity.this).setIcon(R.drawable.rescue_icon_warning).setTitle("清除缓存")
                        .setMessage("是否确定删除该数据，删除无法恢复！").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (int j = 0; j < list.size();j++) {
                                    People result = list.get(j);
                                    if (result.getId() == getIntent().getIntExtra("id", 0)) {
                                        list.remove(result);
                                    }
                                }
                                jsonArray = new JSONArray();
                                for (int k = 0; k < list.size(); k++) {
                                    JSONObject object = new JSONObject();
                                    try {
                                        object.put("id", list.get(k).getId());
                                        object.put("hz", list.get(k).getHz());
                                        object.put("name", list.get(k).getName());
                                        object.put("sex", list.get(k).getSex());
                                        object.put("age", list.get(k).getAge());
                                        object.put("hy", list.get(k).getHy());
                                        object.put("tel", list.get(k).getTel());
                                        object.put("city", list.get(k).getCity());
                                        object.put("img", list.get(k).getImg());
                                        jsonArray.put(object);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                aCache.put("peopleList", jsonArray);
                                Toast.makeText(PeopleDetailsActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PeopleDetailsActivity.this, "取消", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Log.d("uuuuuuuuuuuu", "onActivityResult: " + selectList.get(0).getPath().toString());
                    imgUrl = selectList.get(0).getPath().toString();
                    Glide.with(PeopleDetailsActivity.this).load(selectList.get(0).getPath().toString()).into(iv_image);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
    }
}
