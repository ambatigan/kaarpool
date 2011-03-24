package com.saventech.karpool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/** Class Must extends with Dialog */
/** Implement onClickListener to dismiss dialog when OK Button is pressed */
public class RatingDialog extends Dialog implements RatingBar.OnRatingBarChangeListener {
	
	RatingBar mIndicatorRatingBar;
    TextView mRatingText;
    EditText commenttext;

	  public interface ReadyListener {
	        public void ready(String name);
	    }

	    private ReadyListener readyListener;
	    
	 
	    public RatingDialog(Context context, String name, ReadyListener readyListener) {
	        super(context);
	        this.readyListener = readyListener;
	    }

	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.rating_dialog);
	    	setTitle("Ride Rating");
	    	mRatingText = (TextView) findViewById(R.id.rating);

	        // We copy the most recently changed rating on to these indicator-only
	        // rating bars
	        mIndicatorRatingBar = (RatingBar) findViewById(R.id.indicator_ratingbar);
	        // The different rating bars in the layout. Assign the listener to us.
	        ((RatingBar)findViewById(R.id.ratingbar2)).setOnRatingBarChangeListener(this);
	        commenttext = (EditText)findViewById(R.id.comment);
	        Button buttonOK = (Button) findViewById(R.id.Button01);
	        buttonOK.setOnClickListener(new OKListener());
	        
	        Button buttonCancel = (Button) findViewById(R.id.Button02);
	        buttonCancel.setOnClickListener(new OKListener());
	    }

	    private class OKListener implements android.view.View.OnClickListener {
	        public void onClick(View view) {
	        	if(view==findViewById(R.id.Button01))
	        	{
	        		readyListener.ready(String.valueOf(mRatingText.getText()));
	        		readyListener.ready(String.valueOf(commenttext.getText()));
	        		RatingDialog.this.dismiss();
	        	}
	        	if(view==findViewById(R.id.Button02))
	        	{
	        		RatingDialog.this.dismiss();
	        	}
	        }
	    }
	    
	    
		public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {
	        final int numStars = ratingBar.getNumStars();
	        mRatingText.setText("Rating:  " + rating + "/" + numStars + "\n");

	        // Since this rating bar is updated to reflect any of the other rating
	        // bars, we should update it to the current values.
	        if (mIndicatorRatingBar.getNumStars() != numStars) {
	            mIndicatorRatingBar.setNumStars(numStars);
	        }
	        if (mIndicatorRatingBar.getRating() != rating) {
	            mIndicatorRatingBar.setRating(rating);
	        }
	        final float ratingBarStepSize = ratingBar.getStepSize();
	        if (mIndicatorRatingBar.getStepSize() != ratingBarStepSize) {
	            mIndicatorRatingBar.setStepSize(ratingBarStepSize);
	        }
	    }
}
