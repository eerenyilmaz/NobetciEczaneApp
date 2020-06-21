package com.example.nobetcieczaneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class Main2Activity extends AppCompatActivity {

    TextView textR1, textR2, textR3,headerText;
    TextView textR5, textR6, textR7;
    Elements element2,element3,element1;
    Button mapButton1,mapButton2;

    static String url = "https://apps.istanbulsaglik.gov.tr/NobetciEczane/Home/GetEczaneler";

    private class DownloadData extends AsyncTask<String,String,String> {
        Document doc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textR1 = (TextView) findViewById(R.id.textR1);
            textR2 = (TextView) findViewById(R.id.textR2);
            textR3 = (TextView) findViewById(R.id.textR3);

            textR5 = (TextView) findViewById(R.id.textR5);
            textR6 = (TextView) findViewById(R.id.textR6);
            textR7 = (TextView) findViewById(R.id.textR7);

            headerText = (TextView) findViewById(R.id.headerText);

            mapButton1 = (Button) findViewById(R.id.mapButton1);
            mapButton2 = (Button) findViewById(R.id.mapButton2);

        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                doc = Jsoup.connect(strings[0]).userAgent("Mozilla").data("ilce", strings[1]).post();

            } catch (IOException e) {

                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Main2Activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    element3 = doc.select("tr>td>label"); // Tarih, Eczane adı, semt
                    element2 = doc.select("b"); // Tarih, Eczane adı, semt
                    element1 = doc.select("tr>td>label>a"); //telefon no, faks no
                    String string1 = "\\?";
                    String string2 = "&";
                    String string3 = "=";

                    String map1 = ((Element)doc.select("a[class=btn btn-primary btn-block]").get(0)).attr("href");
                    String map2 = ((Element)doc.select("a[class=btn btn-primary btn-block]").get(1)).attr("href");

                    final double parse1 = Double.parseDouble(map1.split(string1)[1].split(string2)[0].split(string3)[1]);
                    final double parse2 = Double.parseDouble(map1.split(string1)[1].split(string2)[1].split(string3)[1]);
                    final double parse3 = Double.parseDouble(map2.split(string1)[1].split(string2)[0].split(string3)[1]);
                    final double parse4 = Double.parseDouble(map2.split(string1)[1].split(string2)[1].split(string3)[1]);



                    final int a = 2, b = 7;
                    textR1.setText(element2.get(a).text());
                    textR2.setText(element1.get(0).text());
                    textR3.setText(element3.get(b).text());

                    textR5.setText(element2.get(a + 3).text());
                    textR6.setText(element1.get(2).text());
                    textR7.setText(element3.get(b + 10).text());
                    headerText.setText(element2.get(0).text() +" "+ element2.get(1).text());

                    mapButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Main2Activity.this,MapsActivity.class);
                            intent.putExtra("id",element2.get(a).text());
                            intent.putExtra("d1",parse1);
                            intent.putExtra("d2",parse2);
                            Main2Activity.this.startActivity(intent);
                        }
                    });

                    mapButton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Main2Activity.this,MapsActivity.class);
                            intent.putExtra("id",element2.get(a+3).text());
                            intent.putExtra("d1",parse3);
                            intent.putExtra("d2",parse4);
                            Main2Activity.this.startActivity(intent);
                        }
                    });

                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        String stringExtra = getIntent().getStringExtra("ilce");
        System.out.println(stringExtra);
        String ilceismi = stringExtra;

        new DownloadData().execute(new String[]{url,ilceismi});


    }
}
