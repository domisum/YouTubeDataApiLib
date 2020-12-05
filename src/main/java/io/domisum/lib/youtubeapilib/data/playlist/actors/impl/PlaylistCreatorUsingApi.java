package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.api.services.youtube.YouTube.Playlists.Insert;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.playlist.YdaPlaylistSpecification;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistCreator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistCreatorUsingApi
	implements PlaylistCreator
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// UPLOAD
	@Override
	public YouTubePlaylistId create(YouTubeApiCredentials credentials, YdaPlaylistSpecification ydaPlaylistSpecification)
		throws IOException
	{
		var playlist = createRequestPlaylist(ydaPlaylistSpecification);
		
		var playlistsInsertRequest = createInsertRequest(credentials, playlist);
		var response = playlistsInsertRequest.execute();
		String playlistIdString = response.getId();
		var youTubePlaylistId = YouTubePlaylistId.of(playlistIdString);
		
		return youTubePlaylistId;
	}
	
	private Playlist createRequestPlaylist(YdaPlaylistSpecification youTubePlaylistSpec)
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
		return youTubeDataApiClient.playlists().insert("snippet,status", playlist);
	}
	
}
