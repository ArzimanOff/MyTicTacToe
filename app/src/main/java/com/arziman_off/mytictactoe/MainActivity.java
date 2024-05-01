package com.arziman_off.mytictactoe;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    SharedGameViewModel sharedGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarTheme();
        Button startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(v -> startGame(savedInstanceState));
    }

    private void setStatusBarTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            if (isDarkTheme()) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
            } else {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.setStatusBarContrastEnforced(true);
            }
        }
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

    private boolean isDarkTheme() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

}