package io.domisum.lib.youtubeapilib.data.playlist;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;

import java.io.IOException;
import java.util.Optional;

public interface PlaylistIdFetcher
{
	
	Optional<YouTubePlaylistId> fetch(YouTubeApiCredentials credentials, String playlistTitle)
		throws IOException;
	
}
