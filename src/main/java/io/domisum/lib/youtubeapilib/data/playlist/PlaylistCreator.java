package io.domisum.lib.youtubeapilib.data.playlist;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.model.YdaPlaylistSpecification;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistCreator
{
	
	YouTubePlaylistId create(YouTubeApiCredentials credentials, YdaPlaylistSpecification ydaPlaylistSpecification)
		throws IOException;
	
}
