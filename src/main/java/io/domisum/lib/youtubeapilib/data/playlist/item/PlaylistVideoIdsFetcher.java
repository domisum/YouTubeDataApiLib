package io.domisum.lib.youtubeapilib.data.playlist.item;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;

import java.io.IOException;
import java.util.List;

public interface PlaylistVideoIdsFetcher
{
	
	default List<String> fetch(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId)
		throws IOException
	{
		return fetch(credentials, youTubePlaylistId, null);
	}
	
	List<String> fetch(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, Integer maxNrOfVideos)
		throws IOException;
	
}
