package com.tianao.peopledata.fragment;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianao.peopledata.R;
import com.tianao.peopledata.activity.AddPeopleActivity;
import com.tianao.peopledata.adapter.RecordAdapter;
import com.tianao.peopledata.model.Medicines;
import com.tianao.peopledata.model.People;
import com.tianao.peopledata.util.ACache;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordFragment extends Fragment {
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.iv_null)
    ImageView iv_null;
    private RecordAdapter adapter;
    private List<People> list = new ArrayList<>();
    private ACache aCache;
    private JSONArray peopleJsonArray;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_record, container, false);
        ButterKnife.bind(this, view);
        aCache = ACache.get(getActivity());
        initView();
        return view;
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
        adapter.notifyDataSetChanged();
        if (list.size() == 0) {
            iv_null.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            iv_null.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new RecyclerViewLineUtil(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.colorPrimary)));
        adapter = new RecordAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPeopleActivity.class);
                intent.putExtra("type","add");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
