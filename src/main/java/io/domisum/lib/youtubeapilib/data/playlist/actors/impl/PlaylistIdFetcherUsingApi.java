package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistIdFetcher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistIdFetcherUsingApi
	implements PlaylistIdFetcher
{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 50L;
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public Optional<YouTubePlaylistId> fetch(YouTubeApiCredentials credentials, String playlistTitle)
		throws IOException
	{
		String nextPageToken = null;
		do
		{
			var response = fetchPlaylistPage(credentials, nextPageToken);
			
			var playlistIdOptional = extractPlaylist(response, playlistTitle);
			if(playlistIdOptional.isPresent())
				return playlistIdOptional;
			
			nextPageToken = response.getNextPageToken();
			logger.debug("Playlist wasn't contained in returned playlists, next page token: {}", nextPageToken);
		}
		while(nextPageToken != null);
		
		logger.debug("No next page token known, playlist doesn't exist");
		return Optional.empty();
	}
	
	private PlaylistListResponse fetchPlaylistPage(YouTubeApiCredentials credentials, String pageToken)
		throws IOException
	{
		logger.debug("Fetching own playlists with page token '{}'", pageToken);
		
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		var playlistsListRequest = youTubeDataApiClient.playlists().list("snippet,contentDetails");
		playlistsListRequest.setMine(true);
		playlistsListRequest.setMaxResults(MAX_RESULTS_LIMIT);
		if(pageToken != null)
			playlistsListRequest.setPageToken(pageToken);
		
		return playlistsListRequest.execute();
	}
	
	private Optional<YouTubePlaylistId> extractPlaylist(PlaylistListResponse response, String playlistTitle)
	{
		for(var playlist : response.getItems())
			if(playlist.getSnippet().getTitle().equals(playlistTitle))
			{
				String playlistIdString = playlist.getId();
				var youTubePlaylistId = YouTubePlaylistId.of(playlistIdString);
				
				logger.debug("Found playlist, id: {}", youTubePlaylistId);
				return Optional.of(youTubePlaylistId);
			}
		
		return Optional.empty();
	}
	
}
