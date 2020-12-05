package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YdaPlaylistSpecification;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;

public interface PlaylistCreator
{
	
	YouTubePlaylistId create(YouTubeApiCredentials credentials, YdaPlaylistSpecification ydaPlaylistSpecification)
		throws IOException;
	
}
