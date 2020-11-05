package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.ResourceId;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoInserter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistVideoInserterUsingApi
	implements PlaylistVideoInserter
{
	
	// REFERENCES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// INSERT
	@Override
	public void insertAsFirst(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException
	{
		insert(credentials, youTubePlaylistId, videoId, true);
	}
	
	@Override
	public void insert(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException
	{
		insert(credentials, youTubePlaylistId, videoId, false);
	}
	
	private void insert(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId, boolean first)
		throws IOException
	{
		var playlistItem = createPlaylistItem(youTubePlaylistId, videoId, first);
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		var insertRequest = youTubeDataApiClient.playlistItems().insert("snippet", playlistItem);
		
		insertRequest.execute();
	}
	
	private PlaylistItem createPlaylistItem(YouTubePlaylistId youTubePlaylistId, String videoId, boolean first)
	{
		var resourceId = new ResourceId();
		resourceId.set("kind", "youtube#video");
		resourceId.set("videoId", videoId);
		
		var snippet = new PlaylistItemSnippet();
		snippet.setPlaylistId(youTubePlaylistId.toString());
		snippet.setResourceId(resourceId);
		if(first)
			snippet.setPosition(0L);
		
		var playlistItem = new PlaylistItem();
		playlistItem.setSnippet(snippet);
		return playlistItem;
	}
	
}
