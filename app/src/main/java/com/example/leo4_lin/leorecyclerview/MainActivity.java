package com.example.leo4_lin.leorecyclerview;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.math.BigDecimal;
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
            map.put("version",pinfo.versionName);
            String dir = pinfo.applicationInfo.publicSourceDir;
            int size = Integer.valueOf((int) new File(dir).length());
            String changeSzie = bytes2kb(size);
            map.put("appSize", changeSzie);
            items.add(map);
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        item_Content = new Item_Content(items,dialog);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //分割線
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(item_Content);

    }

    public String bytes2kb(int bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }
}
