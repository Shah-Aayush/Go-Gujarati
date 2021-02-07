package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_activity);
        setWords(createWords());

        //for adding back button [snippet part 1]
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //for adding back button [snippet part 2]
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setWords(ArrayList<Words> colors){
        WordAdapter adapter = new WordAdapter(this,colors, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView==null){
            Log.v("ColorsActivity","List not found :(");
        }
        listView.setAdapter(adapter);
    }

    private ArrayList<Words> createWords(){
        ArrayList<Words> colorsList = new ArrayList<Words>();
        colorsList.add(new Words("Red","લાલ",R.raw.colorred,R.raw.red));
        colorsList.add(new Words("Yellow","પીળો",R.raw.coloryellow,R.raw.yellow));
        colorsList.add(new Words("Blue","ભૂરો",R.raw.colorblue,R.raw.blue));
        colorsList.add(new Words("Green","લીલો",R.raw.colorgreen,R.raw.green));
        colorsList.add(new Words("Brown","કથ્થાઈ",R.raw.colorbrown,R.raw.brown));
        colorsList.add(new Words("Gray","ભૂખરો",R.raw.colorgray,R.raw.gray));
        colorsList.add(new Words("Black","કાળો",R.raw.colorblack,R.raw.black));
        colorsList.add(new Words("White","સફેદ",R.raw.colorwhite,R.raw.white));
        colorsList.add(new Words("Orange","નારંગી",R.raw.colororange,R.raw.orange));
        return colorsList;
    }
}