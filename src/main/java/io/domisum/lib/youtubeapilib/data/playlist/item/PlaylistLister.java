package io.domisum.lib.youtubeapilib.data.playlist.item;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;

import java.io.IOException;
import java.util.Set;

public interface PlaylistLister
{
	
	Set<YouTubePlaylistId> list(YouTubeApiCredentials credentials)
		throws IOException;
	
}
