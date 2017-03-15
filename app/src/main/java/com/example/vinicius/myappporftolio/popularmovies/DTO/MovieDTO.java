package com.example.vinicius.myappporftolio.popularmovies.DTO;

/**
 * Created by vinicius on 12/03/17.
 */

public class MovieDTO
{
	private String poster_path;
	private String overview;
	private String release_date;
	private long id;
	private String original_title;
	private int runtime;
	private double vote_average;
	private String key;


	public MovieDTO()
	{
	}

	public String getPoster()
	{
		return "http://image.tmdb.org/t/p/w185/" + poster_path;
	}

	public void setPoster_path(String poster_path)
	{
		this.poster_path = poster_path;
	}

	public int getRuntime()
	{
		return runtime;
	}

	public long getId()
	{
		return id;
	}

	public String getKey()
	{
		return key;
	}
}
