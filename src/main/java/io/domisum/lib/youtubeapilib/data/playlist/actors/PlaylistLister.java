package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;

import java.io.IOException;
import java.util.Set;

public interface PlaylistLister
{
	
	Set<YouTubePlaylistId> list(YouTubeApiCredentials credentials)
		throws IOException;
	
}
