package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.auxiliumlib.annotations.API;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.io.IOException;

public interface PlaylistVideoInserter
{
	
	void insert(YouTubeApiCredentials credentials, String playlistId, String videoId, InsertionPosition insertionPosition)
			throws IOException;
	
	
	enum InsertionPosition
	{
		@API
		FIRST,
		@API
		LAST
		
	}
	
}
