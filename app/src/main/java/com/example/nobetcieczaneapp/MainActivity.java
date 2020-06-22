package com.example.nobetcieczaneapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {

    ListView listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listview);

        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Adalar");
        arrayList.add("Arnavutköy");
        arrayList.add("Ataşehir");
        arrayList.add("Bağcılar");
        arrayList.add("Bahçelievler");
        arrayList.add("Bakırköy");
        arrayList.add("Başakşehir");
        arrayList.add("Bayrampaşa");
        arrayList.add("Beşiktaş");
        arrayList.add("Beykoz");
        arrayList.add("Beylikdüzü");
        arrayList.add("Beyoğlu");
        arrayList.add("Büyükçekmece");
        arrayList.add("Çatalca");
        arrayList.add("Çekmeköy");
        arrayList.add("Esenler");
        arrayList.add("Esenyurt");
        arrayList.add("Eyüp");
        arrayList.add("Fatih");
        arrayList.add("Gaziosmanpaşa");
        arrayList.add("Güngören");
        arrayList.add("Kadıköy");
        arrayList.add("Kağıthane");
        arrayList.add("Kartal");
        arrayList.add("Küçükçekmece");
        arrayList.add("Maltepe");
        arrayList.add("Pendik");
        arrayList.add("Sancaktepe");
        arrayList.add("Sarıyer");
        arrayList.add("Silivri");
        arrayList.add("Sultanbeyli");
        arrayList.add("Şile");
        arrayList.add("Şişli");
        arrayList.add("Tuzla");
        arrayList.add("Ümraniye");
        arrayList.add("Üsküdar");
        arrayList.add("Zeytinburnu");

        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ilce=arrayList.get(i).toString();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("ilce", ilce);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
