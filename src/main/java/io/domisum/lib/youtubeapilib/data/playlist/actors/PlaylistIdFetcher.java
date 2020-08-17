package io.domisum.lib.youtubeapilib.data.playlist.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistSpecification;

import java.io.IOException;
import java.util.Optional;

public interface PlaylistIdFetcher
{
	
	Optional<String> fetch(YouTubeApiCredentials credentials, PlaylistSpecification youTubePlaylistSpec)
			throws IOException;
	
}
