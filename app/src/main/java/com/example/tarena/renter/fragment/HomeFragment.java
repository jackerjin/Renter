package com.example.tarena.renter.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tarena.renter.R;
import com.example.tarena.renter.adapter.HomeAdapter;
import com.example.tarena.renter.bean.NewHouse;
import com.example.tarena.renter.util.HttpUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
@BindView(R.id.listview_homeFragment)
ListView listView;
    List<NewHouse> datas;
    HomeAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        datas = new ArrayList<>();
        adapter = new HomeAdapter(getActivity(), datas);
        View listHeader = inflater.inflate(R.layout.inflate_home_item01, listView, false);
        listView.addHeaderView(listHeader);
        listView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        String url="http://bj.58.com/ershoufang/?PGTID=0d200001-0000-1940-c4eb-54f3f05a2f93&ClickID=1";
        HttpUtil.getNewHouse(url, new HttpUtil.OnResponselistener<Document>() {
            @Override
            public void onResponse(Document document) {
                List<NewHouse> newHouses = new ArrayList<NewHouse>();
                Elements elements = document.select("div[class=list-info]"  );//div class="list-info"
                Log.i("TAG","elements"+elements);
                NewHouse newHouse = new NewHouse();
                for (Element element : elements) {
//                    Element imgElement=






                    Elements titleElement=element.select("h2.title");
                    Log.i("mytag", "titleElement:" + titleElement.text());
                    newHouse.setTitle(titleElement.text());
                    Elements addressElement=element.select("p.baseinfo");
                     newHouse.setAddress(addressElement.text());
                    Log.i("TAG", "newhouse"+elements.attr("div.title").toString());

                }
                Elements priceElements=document.select(" div[class=price]");
                Log.i("TAG", "pricElemnts" + priceElements);
                for (Element priceElement : priceElements) {
                    Elements picElement =priceElement.select("p.sum");
                    Log.i("mytag", "priceElement:" + priceElement.text());
                    newHouse.setPrice(picElement.text());
//                    Elements perElemnt=priceElements.select("p.unit");
//                    Log.i("mytag", "perElemnt:" + perElemnt.text());
//                    newHouse.setPerPrice(perElemnt.text());
                }

                Elements imgElements = document.select("div[class=pic]");//class="pic"
                for (Element imgElement : imgElements) {

                    Log.i("mytag", "imgElement1:" + imgElement.attr("data-src"));
                    newHouse.setAvatar(imgElement.attr("data-src"));
                }
             adapter.addAll(newHouses,true);
            }
        });
    }
}
