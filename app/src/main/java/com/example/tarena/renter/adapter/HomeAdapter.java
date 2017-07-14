package com.example.tarena.renter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarena.renter.R;
import com.example.tarena.renter.bean.NewHouse;
import com.example.tarena.renter.util.HttpUtil;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

//Created by tarena on 2017/6/20.


public class HomeAdapter extends MyBaseAdapter<NewHouse> {

    public HomeAdapter(Context context, List<NewHouse> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_item_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        NewHouse deal = getItem(position);
        //呈现图片
        Picasso.with(context).load(deal.getAvatar()).into(vh.ivAvatar);
        vh.tvTitle.setText(deal.getTitle());
        vh.tvDetail.setText(deal.getDetail());
        vh.tvPrice.setText(deal.getPrice() + "");
        vh.tvAddress.setText(deal.getAddress());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_home_avatar)
        ImageView ivAvatar;
        @BindView(R.id.item_home_title)
        TextView tvTitle;
        @BindView(R.id.item_home_detail)
        TextView tvDetail;
        @BindView(R.id.item_home_address)
        TextView tvAddress;
        @BindView(R.id.item_home_price)
        TextView tvPrice;

       public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
