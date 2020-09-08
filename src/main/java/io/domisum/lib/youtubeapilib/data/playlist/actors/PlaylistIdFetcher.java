package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistSpecification;

import java.io.IOException;
import java.util.Optional;

public interface PlaylistIdFetcher
{
	
	Optional<YouTubePlaylistId> fetch(YouTubeApiCredentials credentials, YouTubePlaylistSpecification youTubePlaylistSpec)
		throws IOException;
	
}
