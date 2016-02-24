package com.cs407_android.rockpaperscissors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFragment extends Fragment {

    private static final String ARG_PLAYER_ONE = "player1";
    private static final String ARG_PLAYER_TWO = "player2";

    private String player1Choice;
    private String player2Choice;

    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private TextView headerTextView;

    /**
     * DONT CHANGE THIS METHOD
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param player1Choice Player 1's move (null if not specified yet).
     * @param player2Choice Player 2's move (null if not specified yet).
     * @return A new instance of fragment PlayFragment.
     */
    public static PlayFragment newInstance(String player1Choice, String player2Choice) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLAYER_ONE, player1Choice);
        args.putString(ARG_PLAYER_TWO, player2Choice);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            player1Choice = getArguments().getString(ARG_PLAYER_ONE);
            player2Choice = getArguments().getString(ARG_PLAYER_TWO);
        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TA Implementation DON'T CHANGE
        View view = null;
        view = inflater.inflate(R.layout.fragment_play, container, false);

        //instantiate widgets
        rockButton = (Button) view.findViewById(R.id.rock);
        paperButton = (Button) view.findViewById(R.id.paper);
        scissorsButton = (Button) view.findViewById(R.id.scissors);
        headerTextView = (TextView) view.findViewById(R.id.header);

        //set header
        if(player1Choice == null) {
            headerTextView.setText(getString(R.string.player_1_header));
        }
        else{
            headerTextView.setText(getString(R.string.player_2_header));
        }

        return view;

        // End TA Implementation
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TA Implementation

        //different way of implementing click interaction.
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(player1Choice == null) {
                   //TODO player 1 chose Rock
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_fragment_container, PlayFragment.newInstance(getString(R.string.rock), null))
                            .commit();
                }
                else{
                    //TODO player 2 chose Rock
                    player2Choice = getString(R.string.rock);
                    gameLogic();
                }

            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(player1Choice == null) {
                    //TODO
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_fragment_container, PlayFragment.newInstance(getString(R.string.paper), null))
                            .commit();
                }
                else{
                    //TODO
                    player2Choice = getString(R.string.paper);
                    gameLogic();
                }
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(player1Choice == null) {
                    //TODO
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_fragment_container, PlayFragment.newInstance(getString(R.string.scissors), null))
                            .commit();
                }
                else{
                    //TODO
                    player2Choice = getString(R.string.scissors);
                    gameLogic();
                }
            }
        });
    }

    private void gameLogic() {

        if(player1Choice.equals(player2Choice)) {
            displayWinner("Woah it's a Draw!");
        }
        else if(player1Choice.equals(getString(R.string.rock)) && player2Choice.equals(getString(R.string.paper))){
            displayWinner(getString(R.string.player_2_header));
        }
        else if(player1Choice.equals(getString(R.string.paper)) && player2Choice.equals(getString(R.string.scissors))){
            displayWinner(getString(R.string.player_2_header));
        }
        else if(player1Choice.equals(getString(R.string.scissors)) && player2Choice.equals(getString(R.string.rock))){
            displayWinner(getString(R.string.player_2_header));
        }
        else {
            displayWinner(getString(R.string.player_1_header));
        }
        }


    private void displayWinner(String winner){

        //TODO finish implementing this AlertDialog

        //do a prompt about the winner
        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle("Winner is:")
                .setMessage(winner)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO start a rematch!
                        getFragmentManager().popBackStack();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO back out the the start screen
                        getActivity().finish();
                    }
                })
                .show();

    }


}
