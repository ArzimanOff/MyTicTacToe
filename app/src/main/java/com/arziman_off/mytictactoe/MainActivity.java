package com.arziman_off.mytictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    SharedGameViewModel sharedGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(savedInstanceState);
            }
        });
    }

    private void startGame(Bundle savedInstanceState){
        ImageView nextStepMark = findViewById(R.id.next_step_mark);
        nextStepMark.setImageResource(R.drawable.cross);

        sharedGameViewModel = new ViewModelProvider(this).get(SharedGameViewModel.class);

        RelativeLayout nextStepInfoBox = findViewById(R.id.next_step_info_box);
        nextStepInfoBox.setVisibility(View.VISIBLE);

        FrameLayout fragmentContainer = findViewById(R.id.fragment_container);
        fragmentContainer.setVisibility(View.VISIBLE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new GameBoxFragment())
                    .commit();
        }
    }
}