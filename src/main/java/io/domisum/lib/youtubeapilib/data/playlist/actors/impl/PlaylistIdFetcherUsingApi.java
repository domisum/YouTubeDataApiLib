package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.ChannelPlaylistsIteratorFactory;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistIdFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistIdFetcherUsingApi
	implements PlaylistIdFetcher
{
	
	// REFERENCES
	private final ChannelPlaylistsIteratorFactory channelPlaylistsIteratorFactory;
	
	
	// FETCH
	@Override
	public Optional<YouTubePlaylistId> fetch(YouTubeApiCredentials credentials, String playlistTitle)
		throws IOException
	{
		try
		{
			return fetchUncaught(credentials, playlistTitle);
		}
		catch(UncheckedIOException e)
		{
			throw e.getCause();
		}
	}
	
	private Optional<YouTubePlaylistId> fetchUncaught(YouTubeApiCredentials credentials, String playlistTitle)
	{
		var iterator = channelPlaylistsIteratorFactory.create(credentials);
		while(iterator.hasNext())
		{
			var playlist = iterator.next();
			if(playlist.getSnippet().getTitle().equals(playlistTitle))
			{
				var youTubePlaylistId = YouTubePlaylistId.of(playlist.getId());
				return Optional.of(youTubePlaylistId);
			}
		}
		
		return Optional.empty();
	}
	
}
