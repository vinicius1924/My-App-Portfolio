package com.example.vinicius.myappporftolio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	private Button popularMoviesButton;
	private Button stockHawkButton;
	private Button buildItBiggerButton;
	private Button makeYourAppMaterialButton;
	private Button goUbiquitousButton;
	private Button capstoneButton;
	private Toast toast;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		popularMoviesButton = (Button) findViewById(R.id.popularMoviesButton);
		stockHawkButton = (Button) findViewById(R.id.stockHawkButton);
		buildItBiggerButton = (Button) findViewById(R.id.buildItBiggerButton);
		makeYourAppMaterialButton = (Button) findViewById(R.id.makeYourAppMaterialButton);
		goUbiquitousButton = (Button) findViewById(R.id.goUbiquitousButton);
		capstoneButton = (Button) findViewById(R.id.capstoneButton);
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		popularMoviesButton.setOnClickListener(this);
		stockHawkButton.setOnClickListener(this);
		buildItBiggerButton.setOnClickListener(this);
		makeYourAppMaterialButton.setOnClickListener(this);
		goUbiquitousButton.setOnClickListener(this);
		capstoneButton.setOnClickListener(this);

		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.popularMoviesButton:
				showToast(getResources().getString(R.string.popular_movies_button_toast));
				break;
			case R.id.stockHawkButton:
				showToast(getResources().getString(R.string.stock_hawk_button_toast));
				break;
			case R.id.buildItBiggerButton:
				showToast(getResources().getString(R.string.buildit_bigger_button_toast));
				break;
			case R.id.makeYourAppMaterialButton:
				showToast(getResources().getString(R.string.make_your_app_material_button_toast));
				break;
			case R.id.goUbiquitousButton:
				showToast(getResources().getString(R.string.go_ubiquitous_button_toast));
				break;
			case R.id.capstoneButton:
				showToast(getResources().getString(R.string.capstone_button_toast));
				break;
			default:
				break;
		}
	}

	public void showToast(String message)
	{
		if (toast != null)
		{
			toast.cancel();
		}

		toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
