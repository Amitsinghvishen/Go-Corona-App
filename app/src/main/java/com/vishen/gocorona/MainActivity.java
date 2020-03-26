package com.vishen.gocorona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Data mData=new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView)findViewById(R.id.textView);

        getData dataTask=new getData();
        try {
            //dataTask.execute("https://api.covid19api.com/dayone/country/india/status/confirmed/live").get(); other
            dataTask.execute("https://raw.githubusercontent.com/Amitsinghvishen/Go-Corona-App/master/corona.json").get();  //my github json
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dataTask.onPostExecute(mData);
        Log.i("MDATA",mData.getCountry()+"        "+mData.getCases());
        textView.setText("Cases in "+mData.getCountry()+":"+mData.getCases());

    }
}
