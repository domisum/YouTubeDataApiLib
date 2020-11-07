package io.domisum.lib.youtubeapilib.data.playlist.actors.impl.iterator;

import com.google.api.services.youtube.model.Video;
import com.google.inject.Inject;
import io.domisum.lib.auxiliumlib.datacontainers.tuple.Pair;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelVideosIteratorFactory
{
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 50L;
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FACTORY
	public ChannelVideosIterator create(YouTubeApiCredentials youTubeApiCredentials, String part)
	{
		return new ChannelVideosIterator(youTubeApiCredentials, part);
	}
	
	
	// ITERATOR
	@RequiredArgsConstructor
	public class ChannelVideosIterator
		extends PagedItemsIterator<Video>
	{
		
		// INPUT
		private final YouTubeApiCredentials credentials;
		private final String part;
		
		
		// FETCH
		@Override
		protected Pair<Set<Video>, String> fetchNextPage(String pageToken)
			throws IOException
		{
			var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
			var listRequest = youTubeDataApiClient.videos().list(part);
			listRequest.setMaxResults(MAX_RESULTS_LIMIT);
			if(pageToken != null)
				listRequest.setPageToken(pageToken);
			
			var response = listRequest.execute();
			
			var items = new HashSet<>(response.getItems());
			return new Pair<>(items, response.getNextPageToken());
		}
		
	}
	
}
