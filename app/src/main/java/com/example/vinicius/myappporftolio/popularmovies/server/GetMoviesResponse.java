package com.example.vinicius.myappporftolio.popularmovies.server;

import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieDTO;

import java.util.List;

/**
 * Created by vinicius on 12/03/17.
 */

public class GetMoviesResponse
{
	private List<MovieDTO> results;

	public List<MovieDTO> getData()
	{
		return results;
	}
}
