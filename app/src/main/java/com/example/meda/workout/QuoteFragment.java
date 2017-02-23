package com.example.meda.workout;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class QuoteFragment extends Fragment {

    private static final int DELAY_IN_MILLISECONDS = 5000;
    private String [] quoteArray;
    private TextView quoteView;
    private int quoteId = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            quoteId = savedInstanceState.getInt("quoteId");
            Log.d("QuoteFragment", "in on Create after saving quote id: " + String.valueOf(quoteId) );
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if (wasRunning) {
                running = true;
            }
        }
        running = true;
        wasRunning = true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_quote, container, false);
        quoteView = (TextView) layout.findViewById(R.id.quote_view);
        quoteArray = getActivity().getResources().getStringArray(R.array.quotes);
        displayQuote(layout);
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
        Log.d("QuoteFragment", "in pause");
        Log.d("QuoteFragment", "in pause" + String.valueOf(quoteId));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("QuoteFragment", "in resume");
        Log.d("QuoteFragment", "in resume" + String.valueOf(quoteId));
        if (wasRunning) {
            running = true;
        }
    }

    private void displayQuote(View view) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (quoteId != quoteArray.length) {
                    Log.d("QuoteFragment", "before setText");
                    Log.d("QuoteFragment", "before setText value: " + String.valueOf(quoteId));
                            quoteView.setText(quoteArray[quoteId]);
                    Log.d("QuoteFragment", "after setText");
                    if(running) {
                        quoteId++;
                    }
                    Log.d("QuoteFragment", String.valueOf(quoteId));
                } else {
                    quoteId = 0;
                }
                if(running) {
                    handler.postDelayed(this, DELAY_IN_MILLISECONDS);
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("quoteId", quoteId);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        Log.d("QuoteFragment", "In saved instance");
    }
}
