package com.tianao.peopledata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tianao.peopledata.R;
import com.tianao.peopledata.activity.AddPeopleActivity;
import com.tianao.peopledata.activity.PeopleDetailsActivity;
import com.tianao.peopledata.model.Medicines;
import com.tianao.peopledata.model.People;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Context context;
    private List<People> data;
    public RecordAdapter(Context context, List<People> list) {
        this.context = context;
        this.data = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(data.get(i).getName());
        myViewHolder.tv_sex.setText(data.get(i).getSex() + "  " + data.get(i).getSex() + "  " + data.get(i).getHy());
        myViewHolder.tv_tel.setText(data.get(i).getTel());
        myViewHolder.tv_address.setText(data.get(i).getCity());
        Glide.with(context).load(data.get(i).getImg()).into(myViewHolder.iv_header);
        myViewHolder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PeopleDetailsActivity.class);
                intent.putExtra("id", data.get(i).getId());
                intent.putExtra("hz", data.get(i).getHz());
                intent.putExtra("name", data.get(i).getName());
                intent.putExtra("sex", data.get(i).getSex());
                intent.putExtra("age", data.get(i).getAge());
                intent.putExtra("hy", data.get(i).getHy());
                intent.putExtra("tel", data.get(i).getTel());
                intent.putExtra("city", data.get(i).getCity());
                intent.putExtra("img", data.get(i).getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_sex;
        TextView tv_tel;
        TextView tv_address;
        ImageView iv_header;
        LinearLayout ll_root;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_sex = (TextView) itemView.findViewById(R.id.tv_sex);
            tv_tel = (TextView) itemView.findViewById(R.id.tv_tel);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            iv_header = itemView.findViewById(R.id.iv_header);
            ll_root = itemView.findViewById(R.id.ll_root);
        }
    }
}