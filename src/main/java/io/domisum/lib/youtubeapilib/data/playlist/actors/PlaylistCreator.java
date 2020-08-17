package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistSpecification;

import java.io.IOException;

public interface PlaylistCreator
{
	
	String create(YouTubeApiCredentials credentials, PlaylistSpecification playlistSpecification)
			throws IOException;
	
}
