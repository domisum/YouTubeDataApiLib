package io.domisum.lib.youtubeapilib.data.playlist.actors.impl.iterator;

import com.google.api.services.youtube.model.Playlist;
import com.google.inject.Inject;
import io.domisum.lib.auxiliumlib.datacontainers.tuple.Pair;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelPlaylistsIteratorFactory
{
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 30L;
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FACTORY
	public ChannelPlaylistsIterator create(YouTubeApiCredentials youTubeApiCredentials)
	{
		return new ChannelPlaylistsIterator(youTubeApiCredentials);
	}
	
	
	// ITERATOR
	@RequiredArgsConstructor
	public class ChannelPlaylistsIterator
		extends PagedItemsIterator<Playlist>
	{
		
		// INPUT
		private final YouTubeApiCredentials credentials;
		
		
		// FETCH
		@Override
		protected Pair<Set<Playlist>, String> fetchNextPage(String pageToken)
			throws IOException
		{
			var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
			var listRequest = youTubeDataApiClient.playlists().list("snippet,contentDetails");
			listRequest.setMine(true);
			listRequest.setMaxResults(MAX_RESULTS_LIMIT);
			if(pageToken != null)
				listRequest.setPageToken(pageToken);
			
			var response = listRequest.execute();
			
			var items = new HashSet<>(response.getItems());
			String nextPageToken = response.getNextPageToken();
			return new Pair<>(items, nextPageToken);
		}
		
	}
	
}
