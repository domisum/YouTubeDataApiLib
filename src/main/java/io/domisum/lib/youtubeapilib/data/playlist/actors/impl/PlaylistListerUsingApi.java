package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistLister;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistListerUsingApi
	implements PlaylistLister
{
	
	// REFERENCES
	private final ChannelPlaylistsIteratorFactory channelPlaylistsIteratorFactory;
	
	
	// FETCH
	@Override
	public Set<YouTubePlaylistId> list(YouTubeApiCredentials credentials)
		throws IOException
	{
		try
		{
			return fetchUncaught(credentials);
		}
		catch(UncheckedIOException e)
		{
			throw e.getCause();
		}
	}
	
	private Set<YouTubePlaylistId> fetchUncaught(YouTubeApiCredentials credentials)
	{
		var playlistIds = new HashSet<YouTubePlaylistId>();
		
		var iterator = channelPlaylistsIteratorFactory.create(credentials);
		while(iterator.hasNext())
		{
			var playlist = iterator.next();
			var playlistId = YouTubePlaylistId.of(playlist.getId());
			playlistIds.add(playlistId);
		}
		
		return playlistIds;
	}
	
}
