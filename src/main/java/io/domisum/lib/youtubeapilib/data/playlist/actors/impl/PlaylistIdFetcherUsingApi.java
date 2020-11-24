package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistIdFetcher;
import io.domisum.lib.youtubeapilib.data.playlist.actors.impl.iterator.ChannelPlaylistsIteratorFactory;
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
	
	private Optional<YouTubePlaylistId> fetchUncaught(YouTubeApiCredentials credentials, String searchedPlaylistTitle)
	{
		var iterator = channelPlaylistsIteratorFactory.create(credentials);
		while(iterator.hasNext())
		{
			var p = iterator.next();
			if(searchedPlaylistTitle.equals(p.getSnippet().getTitle()))
			{
				var youTubePlaylistId = YouTubePlaylistId.of(p.getId());
				return Optional.of(youTubePlaylistId);
			}
		}
		
		return Optional.empty();
	}
	
}
