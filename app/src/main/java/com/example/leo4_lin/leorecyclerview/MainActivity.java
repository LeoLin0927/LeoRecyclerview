package com.example.leo4_lin.leorecyclerview;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public ArrayList <HashMap<String,String>> items=new ArrayList<HashMap<String,String>>();
    private Item_Content item_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得所有app
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for(int i=0;i<packages.size();i++) {
            PackageInfo pinfo = packages.get(i);
            //將app依據icon appName packageName放入map
            //再將map放入 List 一個List內容代表一組
            HashMap map = new HashMap();
            map.put("icon", pinfo.applicationInfo.loadIcon(pm));
            map.put("appName", pinfo.applicationInfo.loadLabel(pm));
            map.put("packageName", pinfo.packageName);
            items.add(map);
        }

        item_Content = new Item_Content(items);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //分割線
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(item_Content);

    }

}
