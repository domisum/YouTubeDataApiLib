package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.YouTube.Playlists.Insert;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistSpecification;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistCreator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class PlaylistCreatorUsingApi
		implements PlaylistCreator
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// UPLOAD
	@Override
	public String create(YouTubeApiCredentials credentials, YouTubePlaylistSpecification youTubePlaylistSpecification)
			throws IOException
	{
		var playlist = createRequestPlaylist(youTubePlaylistSpecification);
		
		var playlistsInsertRequest = createInsertRequest(credentials, playlist);
		var response = playlistsInsertRequest.execute();
		String playlistId = response.getId();
		
		return playlistId;
	}
	
	private Playlist createRequestPlaylist(YouTubePlaylistSpecification youTubePlaylistSpec)
	{
		var playlist = new Playlist();
		
		var snippet = new PlaylistSnippet();
		snippet.setTitle(youTubePlaylistSpec.getTitle());
		snippet.setDescription(youTubePlaylistSpec.getDescription());
		playlist.setSnippet(snippet);
		
		var status = new PlaylistStatus();
		status.setPrivacyStatus(youTubePlaylistSpec.getPrivacyStatus().name());
		playlist.setStatus(status);
		return playlist;
	}
	
	private Insert createInsertRequest(YouTubeApiCredentials credentials, Playlist playlist)
			throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var insert = youTubeDataApiClient.playlists().insert("snippet,status", playlist);
		return insert;
	}
	
}
