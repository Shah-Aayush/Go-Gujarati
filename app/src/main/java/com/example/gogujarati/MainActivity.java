package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setClickListeners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setClickListeners(){

        TextView numbersView = (TextView) findViewById(R.id.numbers);
        numbersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //METHOD 1
//                Context mainContext = v.getContext();
//                Intent numbersIntent = new Intent(mainContext,NumbersActivity.class);
//                mainContext.startActivity(numbersIntent);

                //METHOD 2
                Intent numbersIntent = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        TextView colorsView = (TextView) findViewById(R.id.colors);
        colorsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        TextView familyView = (TextView) findViewById(R.id.family);
        familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView phrasesView = (TextView) findViewById(R.id.phrases);
        phrasesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }

}

/*  HOW TO USE onClickListeners by making a whole new class [ideal when we have to use onClickListeners multiple times]
below is the code for NumbersClickListeners.java file :
package com.example.gogujarati;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class NumbersClickListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
//        Toast.makeText(v.getContext(),"Numbers Activity opened !",Toast.LENGTH_SHORT).show();
        Context mainContext = v.getContext();
        Intent intent = new Intent(mainContext,NumbersActivity.class);
        mainContext.startActivity(intent);
    }
}

*Then in main activity add this code :

        NumbersClickListener numbersClickListener = new NumbersClickListener();
        TextView NumbersView = (TextView) findViewById(R.id.numbers);
        NumbersView.setOnClickListener(numbersClickListener);

*/