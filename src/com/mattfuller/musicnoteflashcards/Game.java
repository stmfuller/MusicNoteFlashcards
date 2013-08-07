package com.mattfuller.musicnoteflashcards;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.DialogInterface;


public class Game extends Activity {

	
	
	
	int questions[]; 
	Clef clef;
	Notes note;
	Random rand;
	int count;
	int right;
	int wrong;
	TextView question, numRight, numWrong, test;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//wire up the TextViews
		question = (TextView)findViewById(R.id.question);
		numRight = (TextView)findViewById(R.id.numRight);
		numWrong = (TextView)findViewById(R.id.numWrong);
		test = (TextView)findViewById(R.id.test);
		
		
		//setup for testing purposes, final versions
		//will allow the user to set this up.
		questions = new int[10];
		clef = Clef.ALL;
		

		
		
		//seed the random.
		rand = new Random();
		
		gameStartUp();
	}

	private void gameStartUp()
	{	
		for (int i= 0; i<questions.length; i++)
		{
			//testing purposes will have this set with
			//all possibilities, Another thing that will
			//change is that the sharp/flat notes will
			//be removed so that starting students can
			//learn easier.
			questions[i] = rand.nextInt(12);
			
		}
		
		//initialize the score count.
		count = right = wrong = 0;
		updateNote();
	}
	
	private void updateNote()
	{
		int temp = count+1;
		//update the question number to the current one
		question.setText("Question: " + temp);
		
		//update the right/wrong textviews
		numRight.setText("Correct: " + right);
		numWrong.setText("Incorrect: " + wrong);
		test.setText(String.valueOf(Notes.getNote(questions[count])));
		
		
		//loads up the game using the desired
		//clef/clefs
		switch (clef) 
		{
			case TREBLE:
				break;
			case BASS:
				break;
			default:
				
				break;
		}
	}
	
	public void buttonHandler(View v)
	{
		Button b = (Button)v;
		String s = b.getTag().toString();
		if (String.valueOf(Notes.getNote(questions[count])).equals(s))
		{
			right++;
		}
		else
		{
			wrong++;
		}
		count++;
		if(count < questions.length)
			updateNote();
		else
			endGame();
	}
	
	
	private void endGame()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Again?").setTitle("Game Over");
		
		//setup another round. 
		builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int id)
				{
					gameStartUp();
				}
			});
		
		
		//set the quit button
		builder.setNegativeButton("Quit", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int id)
			{
				gameStartUp();
			}
		});
		
		
		//create the alertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
