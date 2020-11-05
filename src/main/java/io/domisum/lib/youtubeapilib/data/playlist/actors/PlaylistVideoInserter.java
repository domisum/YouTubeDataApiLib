package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistVideoInserter
{
	
	void insertAsFirst(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException;
	
	void insert(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException;
	
}
