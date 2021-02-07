package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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

    private void setWords(ArrayList<Words> phrases){
        WordAdapter adapter = new WordAdapter(this,phrases , R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView==null){
            Log.v("PhrasesActivity","List not found :(");
        }
        listView.setAdapter(adapter);
    }

    private ArrayList<Words> createWords(){
        ArrayList<Words> phrasesList = new ArrayList<Words>();
        phrasesList.add(new Words("Welcome","પધારો",R.raw.welcome));
        phrasesList.add(new Words("Hello","નમસ્તે",R.raw.hello));
        phrasesList.add(new Words("How are you ?","તમે કેમ છો ?",R.raw.how_are_you));
        phrasesList.add(new Words("What is your name ?","તમારું નામ શું છે ?",R.raw.whats_your_name));
        phrasesList.add(new Words("My name is 'Aayush'","મારું નામ 'આયુષ' છે",R.raw.my_name_is));
        phrasesList.add(new Words("Good morning","સુપ્રભાત",R.raw.good_morning));
        phrasesList.add(new Words("Good night","શુભરાત્રિ",R.raw.good_night));
        phrasesList.add(new Words("Bye","આવજો",R.raw.bye));
        phrasesList.add(new Words("Help","મદદ કરો",R.raw.help));
        phrasesList.add(new Words("Stop","થોભો",R.raw.stop));
        phrasesList.add(new Words("Congratulations","અભિનંદન!",R.raw.congrats));
        phrasesList.add(new Words("Happy Birthday","જન્મ દિન મુબારક",R.raw.happy_birthday));
        phrasesList.add(new Words("Where is toilet ?","સૌચાલય ક્યાં છે ?",R.raw.where_is_toilet));
        phrasesList.add(new Words("Sorry","મને માફ કરો ",R.raw.sorry));

        return phrasesList;
    }
}