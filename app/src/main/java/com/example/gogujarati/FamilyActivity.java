package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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

    private void setWords(ArrayList<Words> family){
        WordAdapter adapter = new WordAdapter(this,family, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView==null){
            Log.v("FamilyActivity","List not found :(");
        }
        listView.setAdapter(adapter);
    }

    private ArrayList<Words> createWords(){
        ArrayList<Words> familyList = new ArrayList<Words>();
        familyList.add(new Words("Mother","પિતા",R.raw.familydad,R.raw.father));
        familyList.add(new Words("Father","માતા",R.raw.familymom,R.raw.mother));
        familyList.add(new Words("Son","દીકરો",R.raw.familykidboy,R.raw.son));
        familyList.add(new Words("Daughter","દીકરી",R.raw.familygirl,R.raw.daughter));
        familyList.add(new Words("Brother","ભાઈ",R.raw.familybrother,R.raw.brother));
        familyList.add(new Words("Sister","બહેન",R.raw.familygirl,R.raw.sister));
        familyList.add(new Words("Uncle","કાકા",R.raw.familyuncle,R.raw.uncle));
        familyList.add(new Words("Aunt","કાકી",R.raw.familyaunt,R.raw.aunt));
        familyList.add(new Words("Maternal Uncle","મામા",R.raw.familyuncle2,R.raw.maternal_uncle));
        familyList.add(new Words("Maternal Aunt","મામી",R.raw.familyaunt2,R.raw.maternal_aunt));
        familyList.add(new Words("Wife","પત્ની",R.raw.familyaunt,R.raw.wife));
        familyList.add(new Words("Husband","પતિ",R.raw.familyuncle,R.raw.husband));
        familyList.add(new Words("Daughter-in-law","વહુ",R.raw.familyaunt2,R.raw.daughterinlaw));
        familyList.add(new Words("Son-in-law","જમાઈ",R.raw.familyuncle2,R.raw.soninlaw));
        familyList.add(new Words("Mother-in-law","સાસુ",R.raw.familyaunt,R.raw.mother_in_law));
        familyList.add(new Words("Father-in-law","સસરો",R.raw.familygrandpa,R.raw.father_in_law));
        return familyList;
    }
}