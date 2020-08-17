package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylist;

import java.io.IOException;

public interface PlaylistDeleter
{
	
	void delete(YouTubeApiCredentials credentials, String playlistId)
			throws IOException;
	
	default void delete(YouTubeApiCredentials credentials, YouTubePlaylist youTubePlaylist)
			throws IOException
	{
		delete(credentials, youTubePlaylist.getPlaylistId());
	}
	
}
