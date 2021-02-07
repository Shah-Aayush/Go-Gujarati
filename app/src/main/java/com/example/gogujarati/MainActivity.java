package com.example.gogujarati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //UI-changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

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