package io.domisum.lib.youtubeapilib.data.playlist;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.iterator.ChannelPlaylistsIteratorFactory;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistIdFetcherImpl
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
