package com.arziman_off.mytictactoe;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    SharedGameViewModel sharedGameViewModel;
    FrameLayout fragmentContainer;
    Button startGameButton;
    Button restartGameButton;
    Animation btnToUp;
    Animation btnToDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarTheme();
        startGameButton = findViewById(R.id.start_game_button);
        restartGameButton = findViewById(R.id.restart_game_button);
        startGameButton.setOnClickListener(v -> {
            hideBtn(startGameButton);
            displayBtn(restartGameButton);
            startGame(savedInstanceState);
        });
        restartGameButton.setOnClickListener(v -> startGame(savedInstanceState));
    }

    private void hideBtn(Button btn) {
        btnToDown = AnimationUtils.loadAnimation(this, R.anim.move_to_down);
        btn.startAnimation(btnToDown);
        btn.setVisibility(View.GONE);
    }

    private void displayBtn(Button btn) {
        btnToUp = AnimationUtils.loadAnimation(this, R.anim.move_to_up);
        btn.startAnimation(btnToUp);
        btn.setVisibility(View.VISIBLE);
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

    private void startGame(Bundle savedInstanceState) {
        sharedGameViewModel = new ViewModelProvider(this).get(SharedGameViewModel.class);
        makeWinnerInfoBoxDefault();
        displayNextStepInfoBox();
        displayGameField();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new GameBoxFragment())
                    .commit();
        }
    }


    private void displayNextStepInfoBox() {
        ImageView nextStepMark = findViewById(R.id.next_step_mark);
        nextStepMark.setImageResource(R.drawable.cross);
        RelativeLayout nextStepInfoBox = findViewById(R.id.next_step_info_box);
        nextStepInfoBox.setVisibility(View.VISIBLE);
    }

    public void startWinAnimation(){
        Animation winAnimation = AnimationUtils.loadAnimation(this, R.anim.win_animation);
        fragmentContainer.startAnimation(winAnimation);
    }


    private boolean isDarkTheme() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private void makeWinnerInfoBoxDefault(){
        RelativeLayout winnerInfoBox = findViewById(R.id.winner_info_box);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) winnerInfoBox.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        params.setMargins(params.leftMargin, ViewGroup.LayoutParams.WRAP_CONTENT, params.rightMargin, params.bottomMargin);
        winnerInfoBox.setLayoutParams(params);
        winnerInfoBox.setVisibility(View.GONE);
    }

    private void displayGameField(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        fragmentContainer = findViewById(R.id.fragment_container);
        fragmentContainer.startAnimation(anim);
        fragmentContainer.setVisibility(View.VISIBLE);
    }
}