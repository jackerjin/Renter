package com.example.tarena.renter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarena.renter.R;
import com.example.tarena.renter.adapter.HomeAdapter;
import com.example.tarena.renter.bean.NewHouse;
import com.example.tarena.renter.util.HttpUtil;
import com.example.tarena.renter.view.MylistView;

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
MylistView listView;

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

                Elements elements = document.select("ul[class=house-list-wrap] div[class]");//div class="list-info"
                Log.i("TAG", "class=house-list-wrap"+elements);
                for (Element element : elements) {
                    NewHouse    newHouse = new NewHouse();
                    Elements titleElement=element.select("h2.title");
                    Log.i("TAG", "titleElement "+titleElement.text());
                    newHouse.setTitle(titleElement.text());

                    Elements addressElement=element.select("p.baseinfo");
                     newHouse.setAddress(addressElement.text());
                    Elements picElement =element.select("p.sum");
                    newHouse.setPrice(picElement.text());

                    Elements imgElement = element.select("div[class=pic] img");

                    newHouse.setAvatar(imgElement.attr("data-src"));
                  newHouses.add(newHouse);

                }
//                Elements priceElements=document.select(" div[class=price] ");
//                for (Element priceElement : priceElements) {
////                    newHouse = new NewHouse();
//                    Elements picElement =priceElement.select("p.sum");
//                    Log.i("mytag", "priceElement:" + priceElement.text());
//                    newHouse.setPrice(picElement.text());
////                    Elements perElemnt=priceElements.select("p.unit");
////                    Log.i("mytag", "perElemnt:" + perElemnt.text());
////                    newHouse.setPerPrice(perElemnt.text());
//                    newHouses.add(newHouse);
//                    Log.i("mytag", "newHouses1:"+newHouses);
//
//                }
//
//                Elements imgElements = document.select("div[class=pic] img");//class="pic"
//                for (Element imgElement : imgElements) {
//                    newHouse = new NewHouse();
//                    newHouse.setAvatar(imgElement.attr("data-src")+"");
//                    newHouses.add(newHouse);
//                    Log.i("mytag", "newHouses2:"+newHouses);
//                }
                //Log.i("TAG", "newhouse"+newHouses);
             adapter.addAll(newHouses,true);
                Log.i("TAG", "newHouses"+newHouses);
            }
        });
    }
}
