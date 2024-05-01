package com.arziman_off.mytictactoe;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class GameBoxFragment extends Fragment {

    private final int leftPlayerMark = R.drawable.cross;
    private final int rightPlayerMark = R.drawable.circle;
    private final GameSettings settings = new GameSettings(leftPlayerMark, rightPlayerMark);
    private final Game game = new Game(settings);

    private final ImageView[][] cells = new ImageView[3][3];

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            SharedGameViewModel sharedGameViewModel = mainActivity.sharedGameViewModel;
            sharedGameViewModel.setGame(game);
        } else {
            throw new RuntimeException(context.toString()
                    + " должен реализовывать интерфейс MainActivity");
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_board, container, false);

        GridLayout gridLayout = view.findViewById(R.id.gridLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String cellId = "cell_" + i + j;
                int resId = getResources().getIdentifier(cellId, "id", getActivity().getPackageName());
                cells[i][j] = view.findViewById(resId);
            }
        }


        setCellClickListeners();

        return view;
    }

    private void setCellClickListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Player thisPlayer = game.getCurrentStep();
                        int[][] thisMarksArray = game.getMarksArray();
                        if (thisMarksArray[finalI][finalJ] == 0 && game.isGameIsValid()) {
                            cells[finalI][finalJ].setImageResource(thisPlayer.getMark());
                            game.setCurrentStep(
                                    (thisPlayer.equals(game.getPlayerLeft())) ?
                                            (game.getPlayerRight()) : (game.getPlayerLeft())
                            );
                            ImageView imageView = getActivity().findViewById(R.id.next_step_mark);
                            imageView.setImageResource(game.getCurrentStep().getMark());


                            thisMarksArray[finalI][finalJ] = thisPlayer.getId();
                            game.setMarksArray(thisMarksArray);

                            if (game.checkWinner() == Game.CROSS){
                                game.setWinner(game.getPlayerLeft());
                                endCurrentGame();
                            } else if (game.checkWinner() == Game.CIRCLE) {
                                game.setWinner(game.getPlayerRight());
                                endCurrentGame();
                            }
                        }
                    }
                });
            }
        }
    }

    private void endCurrentGame() {
        List<int[]> indexes = game.getIndexesOfWinningCells();

        for (int i = 0; i < 3 && i < indexes.size(); i++) {
            int[] pair = indexes.get(i);
            if (game.getWinner().getId() == 1){
                cells[pair[0]][pair[1]].setImageResource(R.drawable.win_cross);
            } else {
                cells[pair[0]][pair[1]].setImageResource(R.drawable.win_circle);
            }
        }
        System.out.println(game.getWinner().getId());
        game.setGameIsValid(false);
    }

}
