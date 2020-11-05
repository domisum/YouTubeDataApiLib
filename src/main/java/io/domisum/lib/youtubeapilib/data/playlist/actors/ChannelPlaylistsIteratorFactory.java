package io.domisum.lib.youtubeapilib.data.playlist.actors;

import com.google.api.services.youtube.model.Playlist;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelPlaylistsIteratorFactory
{
	
	// CONSTANTS
	private static final long MAX_RESULTS_LIMIT = 50L;
	
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
		implements Iterator<Playlist>
	{
		
		// INPUT
		private final YouTubeApiCredentials credentials;
		
		// STATUS
		private String nextPageToken = null;
		private boolean done = false;
		private final Queue<Playlist> playlists = new LinkedList<>();
		
		
		// ITERATOR
		@Override
		public boolean hasNext()
			throws UncheckedIOException
		{
			if(done)
				return false;
			if(playlists.size() > 0)
				return true;
			
			fetchNextPage();
			return playlists.size() > 0;
		}
		
		@Override
		public Playlist next()
			throws UncheckedIOException
		{
			if(playlists.isEmpty() && !done)
				fetchNextPage();
			
			return playlists.remove();
		}
		
		private void fetchNextPage()
		{
			try
			{
				fetchNextPageUncaught();
			}
			catch(IOException e)
			{
				throw new UncheckedIOException(e);
			}
		}
		
		private void fetchNextPageUncaught()
			throws IOException
		{
			var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
			var playlistsListRequest = youTubeDataApiClient.playlists().list("snippet,contentDetails");
			playlistsListRequest.setMine(true);
			playlistsListRequest.setMaxResults(MAX_RESULTS_LIMIT);
			if(nextPageToken != null)
				playlistsListRequest.setPageToken(nextPageToken);
			
			var response = playlistsListRequest.execute();
			playlists.addAll(response.getItems());
			
			nextPageToken = response.getNextPageToken();
			if(nextPageToken == null)
				done = true;
		}
		
	}
	
}
