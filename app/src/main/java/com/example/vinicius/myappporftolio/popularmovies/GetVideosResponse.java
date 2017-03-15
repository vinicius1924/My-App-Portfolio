package com.example.vinicius.myappporftolio.popularmovies;

import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieDTO;

import java.util.List;

/**
 * Created by vinicius on 13/03/17.
 */

public class GetVideosResponse
{
	private List<MovieDTO> results;

	public List<MovieDTO> getData()
	{
		return results;
	}
}
