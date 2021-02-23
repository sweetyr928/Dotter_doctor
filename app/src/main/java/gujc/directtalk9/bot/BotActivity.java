package gujc.directtalk9.bot;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.fragment.BotFragment;
import gujc.directtalk9.fragment.WriteFragment;
import gujc.directtalk9.model.Chatbot;

public class BotActivity extends AppCompatActivity {
    private BotFragment botFragment;
    private FragmentTransaction tran;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        botFragment = new BotFragment();
        fm = getSupportFragmentManager();

        tran = fm.beginTransaction();
        tran.replace(R.id.mainFragment, botFragment).commit();

    }


}