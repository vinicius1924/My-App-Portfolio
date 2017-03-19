package com.example.vinicius.myappporftolio.popularmovies;

import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieDTO;
import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieVideoDTO;

import java.util.List;

/**
 * Created by vinicius on 13/03/17.
 */

public class GetVideosResponse
{
	private List<MovieVideoDTO> results;

	public List<MovieVideoDTO> getData()
	{
		return results;
	}
}
