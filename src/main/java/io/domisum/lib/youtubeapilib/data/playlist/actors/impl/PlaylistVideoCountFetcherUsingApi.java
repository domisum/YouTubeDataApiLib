package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.auxiliumlib.annotations.API;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.PlaylistDoesNotExistException;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoCountFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@API
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistVideoCountFetcherUsingApi
		implements PlaylistVideoCountFetcher
{
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public int fetchVideoCount(YouTubeApiCredentials credentials, String playlistId)
			throws IOException
	{
		
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var request = youTubeDataApiClient.playlists().list("contentDetails");
		request.setId(playlistId);
		var response = request.execute();
		
		if(response.getItems().isEmpty())
			throw new PlaylistDoesNotExistException();
		var contentDetails = response.getItems().get(0).getContentDetails();
		int videoCount = contentDetails.getItemCount().intValue();
		
		return videoCount;
	}
	
}
