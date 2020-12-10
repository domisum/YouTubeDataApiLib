package io.domisum.lib.youtubeapilib.data.playlist.iterator;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.model.YouTubePlaylistId;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelUploadsPlaylistItemIteratorFactory
{
	
	// REFERENCES
	private final PlaylistItemIteratorFactory playlistItemIteratorFactory;
	
	
	// FACTORY
	public Iterator<PlaylistItem> create(YouTubeApiCredentials youTubeApiCredentials, String channelId, String part)
	{
		return playlistItemIteratorFactory.create(youTubeApiCredentials, YouTubePlaylistId.uploadsOfChannel(channelId), part);
	}
	
}
