package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_activity);
//        ArrayList<Words> words = createWords();
//        setWords(words);
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

    private void setWords(ArrayList<Words> words){
        //Method 1 [Not recommended]
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.root_view);
//        for(int i=0;i<words.size();i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        //Method 2 ListView with Adapter
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,words);   //when only english word was displayed
        WordAdapter adapter = new WordAdapter(this, words , R.color.category_numbers);     //when both english and gujarati word should be displayed
        //for showing items in List view
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //for showing items in Grid view :
//        GridView gridView = (GridView) findViewById(R.id.list);
//        gridView.setAdapter(itemsAdapter);
//        just change listView to gridView in .xml file add this line : android:numColumns="2"
    }

    private ArrayList<Words> createWords(){
        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("Zero" , "શૂન્ય",R.raw.number0,R.raw.zero));
        words.add(new Words("One" , "એક",R.raw.number1,R.raw.one));
        words.add(new Words("Two" , "બે",R.raw.number2,R.raw.two));
        words.add(new Words("Three" , "ત્રણ",R.raw.number3,R.raw.three));
        words.add(new Words("Four" , "ચાર",R.raw.number4,R.raw.four));
        words.add(new Words("Five" , "પાંચ",R.raw.number5,R.raw.five));
        words.add(new Words("Six" , "છ",R.raw.number6,R.raw.six));
        words.add(new Words("Seven" , "સાત",R.raw.number7,R.raw.seven));
        words.add(new Words("Eight" , "આઠ",R.raw.number8,R.raw.eight));
        words.add(new Words("Nine" , "નવ",R.raw.number9,R.raw.nine));
//        words.add(new Words("Ten" , "દશ",R.raw.number10));
        return words;
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseInstance();      //we can also release media player resources here if we used any.
    }
}
