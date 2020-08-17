package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.io.IOException;
import java.util.List;

public interface PlaylistVideoIdsFetcher
{
	
	default List<String> fetch(YouTubeApiCredentials credentials, String playlistId)
			throws IOException
	{
		return fetch(credentials, playlistId, null);
	}
	
	List<String> fetch(YouTubeApiCredentials credentials, String playlistId, Integer maxNrOfVideos)
			throws IOException;
	
}
