package com.example.meda.workout;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class WorkoutDetailFragment extends Fragment {

    private long workoutId;
    private static final String WORKOUT_ID = "workoutId";
    private String currentQuote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong(WORKOUT_ID);
        }else {
            FragmentTransaction ftStopwatch = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            ftStopwatch.replace(R.id.stopwatch_container, stopwatchFragment);
            ftStopwatch.addToBackStack(null);
            ftStopwatch.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ftStopwatch.commit();

            FragmentTransaction ftQuote = getChildFragmentManager().beginTransaction();
            QuoteFragment quoteFragment = new QuoteFragment();
            ftQuote.replace(R.id.quote_container, quoteFragment);
            ftQuote.addToBackStack(null);
            ftQuote.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ftQuote.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    public void setWorkout(long id){
        this.workoutId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null){
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(WORKOUT_ID, workoutId);
    }
}
