package com.example.vinicius.myappporftolio.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.vinicius.myappporftolio.R;
import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieDTO;
import com.example.vinicius.myappporftolio.popularmovies.server.ApiServices;
import com.example.vinicius.myappporftolio.popularmovies.utils.VolleyUtils;

import java.util.ArrayList;
import java.util.List;

public class PopularMoviesActivity extends AppCompatActivity implements MoviesPostersRecyclerAdapter.ListItemClickListener, SettingsFragment.PreferenceChangedEventListener
{
	private android.support.v7.widget.RecyclerView moviesPostersRecyclerView;
	private MoviesPostersRecyclerAdapter moviesPostersRecyclerAdapter;
	private List<MovieDTO> moviesList = new ArrayList<MovieDTO>();
	private final String POPULARMOVIESACTIVITYTAG = getClass().getSimpleName();
	private boolean requestsCanceled = false;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popular_movies);

		moviesPostersRecyclerView = (RecyclerView) findViewById(R.id.moviesPostersRecyclerView);
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayShowTitleEnabled(false);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		moviesPostersRecyclerView.setHasFixedSize(true);

		moviesPostersRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//		MovieDTO movie1 = new MovieDTO();
//		movie1.setPoster_path("http://image.tmdb.org/t/p/w185//9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg");
//		MovieDTO movie2 = new MovieDTO();
//		movie2.setPoster_path("http://image.tmdb.org/t/p/w185//xq1Ugd62d23K2knRUx6xxuALTZB.jpg");
//		MovieDTO movie3 = new MovieDTO();
//		movie3.setPoster_path("http://image.tmdb.org/t/p/w185//d4KNaTrltq6bpkFS01pYtyXa09m.jpg");
//		MovieDTO movie4 = new MovieDTO();
//		movie4.setPoster_path("http://image.tmdb.org/t/p/w185//lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg");
//		MovieDTO movie5 = new MovieDTO();
//		movie5.setPoster_path("http://image.tmdb.org/t/p/w185//48A2gbhkXJY5WIKFe4IvNUCl665.jpg");
//		MovieDTO movie6 = new MovieDTO();
//		movie6.setPoster_path("http://image.tmdb.org/t/p/w185//99EyyBq5dXEvY37jF7EyERhQcFe.jpg");
//		MovieDTO movie7 = new MovieDTO();
//		movie7.setPoster_path("http://image.tmdb.org/t/p/w185//jLRllZsubY8UWpeMyDLVXdRyEWi.jpg");
//		MovieDTO movie8 = new MovieDTO();
//		movie8.setPoster_path("http://image.tmdb.org/t/p/w185//yRXTVpDRBA3983C3HjoY0SO4dV6.jpg");
//
//		moviesList.add(movie1);
//		moviesList.add(movie2);
//		moviesList.add(movie3);
//		moviesList.add(movie4);
//		moviesList.add(movie5);
//		moviesList.add(movie6);
//		moviesList.add(movie7);
//		moviesList.add(movie8);

		moviesPostersRecyclerAdapter = new MoviesPostersRecyclerAdapter(this.getApplicationContext(), moviesList, this);

		moviesPostersRecyclerView.setAdapter(moviesPostersRecyclerAdapter);

		loadMoviesByPreference(getPreferenceValue(getResources().getString(R.string.movies_order_by_shared_preferences_key)));
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		Log.d(POPULARMOVIESACTIVITYTAG, POPULARMOVIESACTIVITYTAG + " onStart()");

		if(requestsCanceled)
		{
			loadMoviesByPreference(getPreferenceValue(getResources().getString(R.string.movies_order_by_shared_preferences_key)));
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		Log.d(POPULARMOVIESACTIVITYTAG, POPULARMOVIESACTIVITYTAG + " onResume()");
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		//Log.d(POPULARMOVIESACTIVITYTAG, POPULARMOVIESACTIVITYTAG + " onStop()");

		try
		{
			int numberOfRequests = VolleyUtils.getNumberOfRequestsInQueue(getApplicationContext());

			if(numberOfRequests > 0)
			{
				RequestQueueSingleton.getInstance(getApplicationContext()).getRequestQueue().cancelAll(POPULARMOVIESACTIVITYTAG);

				requestsCanceled = true;
			}
			else
			{
				requestsCanceled = false;
			}
		}
		catch(NoSuchFieldException e)
		{
			Log.e(POPULARMOVIESACTIVITYTAG, e.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				return true;

			case R.id.action_settings:
				getFragmentManager().beginTransaction()
						  .replace(android.R.id.content, new SettingsFragment())
						  .addToBackStack("Settings")
						  .commit();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onListItemClick(int clickedItemIndex)
	{
		MovieDTO movieDTO = moviesList.get(clickedItemIndex).clone();

		Intent i = new Intent(this, MovieActivity.class);
		i.putExtra(getResources().getString(R.string.parcelable_movie_intent_extra), movieDTO);
		startActivity(i);
	}

	public void loadPopularMoviesFromApi()
	{
		final Response.Listener<GetMoviesResponse> successResponseRequestListener = new Response.Listener<GetMoviesResponse>()
		{
			@Override
			public void onResponse(GetMoviesResponse response)
			{
				moviesList.clear();
				moviesList.addAll(response.getData().subList(0, response.getData().size()));
				moviesPostersRecyclerAdapter.notifyDataSetChanged();
			}
		};

		final Response.ErrorListener errorResponseRequestListener = new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				Log.e(POPULARMOVIESACTIVITYTAG, error.getLocalizedMessage());
			}
		};

		ApiServices<GetMoviesResponse> apiServices = new ApiServices<>();
		apiServices.GetPopularMovies(successResponseRequestListener, errorResponseRequestListener,
				  GetMoviesResponse.class, getApplicationContext(), POPULARMOVIESACTIVITYTAG);
	}

	public void loadTopRatedMoviesFromApi()
	{
		final Response.Listener<GetMoviesResponse> successResponseRequestListener = new Response.Listener<GetMoviesResponse>()
		{
			@Override
			public void onResponse(GetMoviesResponse response)
			{
				moviesList.clear();
				moviesList.addAll(response.getData().subList(0, response.getData().size()));
				moviesPostersRecyclerAdapter.notifyDataSetChanged();
			}
		};

		final Response.ErrorListener errorResponseRequestListener = new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				Log.e(POPULARMOVIESACTIVITYTAG, error.getLocalizedMessage());
			}
		};

		ApiServices<GetMoviesResponse> apiServices = new ApiServices<>();
		apiServices.GetTopRatedMovies(successResponseRequestListener, errorResponseRequestListener,
				  GetMoviesResponse.class, getApplicationContext(), POPULARMOVIESACTIVITYTAG);
	}

//	public void loadMovieDetails(long id)
//	{
//		final Response.Listener<MovieDTO> successResponseRequestListener = new Response.Listener<MovieDTO>()
//		{
//			@Override
//			public void onResponse(MovieDTO response)
//			{
//				response.hashCode();
//			}
//		};
//
//		final Response.ErrorListener errorResponseRequestListener = new Response.ErrorListener()
//		{
//			@Override
//			public void onErrorResponse(VolleyError error)
//			{
//				Log.e(POPULARMOVIESACTIVITYTAG, error.getLocalizedMessage());
//			}
//		};
//
//		Type type = new TypeToken<MovieDTO>(){}.getType();
//
//		ApiServices<MovieDTO> apiServices = new ApiServices<>();
//		apiServices.GetMovieDetails(successResponseRequestListener, errorResponseRequestListener,
//				  type, getApplicationContext(), POPULARMOVIESACTIVITYTAG, id);
//	}

	@Override
	public void onPreferenceChangedEvent(String preference)
	{
		loadMoviesByPreference(preference);
	}

	public void loadMoviesByPreference(String preference)
	{
		if(preference.equals(getResources().getString(R.string.first_pref_list_entry)))
		{
			loadPopularMoviesFromApi();
		}
		else
		{
			loadTopRatedMoviesFromApi();
		}
	}

	public String getPreferenceValue(String preferenceKey)
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		String value = sharedPref.getString(preferenceKey, "");

		return value;
	}
}
