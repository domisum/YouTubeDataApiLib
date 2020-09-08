package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistDeleter
{
	
	void delete(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId)
		throws IOException;
	
}
