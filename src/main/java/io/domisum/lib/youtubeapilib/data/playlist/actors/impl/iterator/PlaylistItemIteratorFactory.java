package io.domisum.lib.youtubeapilib.data.playlist.actors.impl.iterator;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.inject.Inject;
import io.domisum.lib.auxiliumlib.datacontainers.tuple.Pair;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistItemIteratorFactory
{
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 50L;
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FACTORY
	public ChannelVideosIterator create(YouTubeApiCredentials youTubeApiCredentials, YouTubePlaylistId playlistId, String part)
	{
		return new ChannelVideosIterator(youTubeApiCredentials, playlistId, part);
	}
	
	
	// ITERATOR
	@RequiredArgsConstructor
	public class ChannelVideosIterator
		extends PagedItemsIterator<PlaylistItem>
	{
		
		// INPUT
		private final YouTubeApiCredentials credentials;
		private final YouTubePlaylistId playlistId;
		private final String part;
		
		
		// FETCH
		@Override
		protected Pair<Set<PlaylistItem>, String> fetchNextPage(String pageToken)
			throws IOException
		{
			var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
			var listRequest = youTubeDataApiClient.playlistItems().list(part);
			listRequest.setPlaylistId(playlistId.toString());
			listRequest.setMaxResults(MAX_RESULTS_LIMIT);
			if(pageToken != null)
				listRequest.setPageToken(pageToken);
			
			var response = listRequest.execute();
			
			var items = new HashSet<>(response.getItems());
			return new Pair<>(items, response.getNextPageToken());
		}
		
	}
	
}
