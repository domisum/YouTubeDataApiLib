package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistDeleter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistDeleterUsingApi
	implements PlaylistDeleter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// UPLOAD
	@Override
	public void delete(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var delete = youTubeDataApiClient.playlists().delete(youTubePlaylistId.toString());
		delete.execute();
	}
	
}
