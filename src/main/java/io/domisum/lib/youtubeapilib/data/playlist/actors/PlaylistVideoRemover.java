package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistVideoRemover
{
	
	void remove(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException;
	
}
