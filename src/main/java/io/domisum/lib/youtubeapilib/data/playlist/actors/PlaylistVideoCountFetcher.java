package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.io.IOException;

public interface PlaylistVideoCountFetcher
{
	
	int fetchVideoCount(YouTubeApiCredentials credentials, String playlistId)
			throws IOException;
	
}
