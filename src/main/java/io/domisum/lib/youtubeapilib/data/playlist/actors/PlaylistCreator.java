package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistSpecification;

import java.io.IOException;

public interface PlaylistCreator
{
	
	YouTubePlaylistId create(YouTubeApiCredentials credentials, YouTubePlaylistSpecification youTubePlaylistSpecification)
		throws IOException;
	
}
