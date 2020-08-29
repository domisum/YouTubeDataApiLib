package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistSpecification;
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
	public Optional<String> fetch(YouTubeApiCredentials credentials, YouTubePlaylistSpecification youTubePlaylistSpecification)
			throws IOException
	{
		String nextPageToken = null;
		do
		{
			var response = fetchPlaylistPage(credentials, nextPageToken);
			
			var playlistIdOptional = extractPlaylist(response, youTubePlaylistSpecification);
			if(playlistIdOptional.isPresent())
				return playlistIdOptional;
			
			nextPageToken = response.getNextPageToken();
			logger.debug("playlist wasn't contained in returned playlists, next page token: {}", nextPageToken);
		}
		while(nextPageToken != null);
		
		logger.debug("no next page token known, playlist doesn't exist");
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
	
	private Optional<String> extractPlaylist(PlaylistListResponse response, YouTubePlaylistSpecification youTubePlaylistSpec)
	{
		for(var playlist : response.getItems())
			if(doesPlaylistMatch(youTubePlaylistSpec, playlist))
			{
				String playlistId = playlist.getId();
				logger.debug("found playlist, id: {}", playlistId);
				return Optional.of(playlistId);
			}
		
		return Optional.empty();
	}
	
	
	// CONDITION UTIL
	private boolean doesPlaylistMatch(YouTubePlaylistSpecification youTubePlaylistSpec, Playlist playlist)
	{
		var snippet = playlist.getSnippet();
		return snippet.getTitle().equalsIgnoreCase(youTubePlaylistSpec.getTitle());
	}
	
}
