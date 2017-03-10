package com.example.vinicius.myappporftolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

		popularMoviesButton.setOnClickListener(this);
		stockHawkButton.setOnClickListener(this);
		buildItBiggerButton.setOnClickListener(this);
		makeYourAppMaterialButton.setOnClickListener(this);
		goUbiquitousButton.setOnClickListener(this);
		capstoneButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.popularMoviesButton:
				showToast("This button will launch Popular Movies App!");
				break;
			case R.id.stockHawkButton:
				showToast("This button will launch Stock Hawk App!");
				break;
			case R.id.buildItBiggerButton:
				showToast("This button will launch Build It Bigger App!");
				break;
			case R.id.makeYourAppMaterialButton:
				showToast("This button will launch Make Your App Material App!");
				break;
			case R.id.goUbiquitousButton:
				showToast("This button will launch Go Ubiquitous App!");
				break;
			case R.id.capstoneButton:
				showToast("This button will launch Capstone App!");
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
