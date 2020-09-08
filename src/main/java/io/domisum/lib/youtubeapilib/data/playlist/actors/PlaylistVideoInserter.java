package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.auxiliumlib.annotations.API;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistVideoInserter
{
	
	void insert(YouTubeApiCredentials credentials,
		YouTubePlaylistId youTubePlaylistId, String videoId, InsertionPosition insertionPosition)
		throws IOException;
	
	
	enum InsertionPosition
	{
		
		@API
		FIRST,
		@API
		LAST
		
	}
	
}
