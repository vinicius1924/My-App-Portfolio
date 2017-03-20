package com.example.vinicius.myappporftolio.popularmovies.server;

import com.example.vinicius.myappporftolio.popularmovies.DTO.MovieReviewDTO;

import java.util.List;

/**
 * Created by vinicius on 17/03/17.
 */

public class GetReviewsResponse
{
	private List<MovieReviewDTO> results;

	public List<MovieReviewDTO> getData()
	{
		return results;
	}
}
