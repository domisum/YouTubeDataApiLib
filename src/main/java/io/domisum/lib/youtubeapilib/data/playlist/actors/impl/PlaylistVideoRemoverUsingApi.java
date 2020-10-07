package io.domisum.lib.youtubeapilib.data.playlist.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.playlist.YouTubePlaylistId;
import io.domisum.lib.youtubeapilib.data.playlist.actors.PlaylistVideoRemover;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlaylistVideoRemoverUsingApi
	implements PlaylistVideoRemover
{
	
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	@Override
	public void remove(YouTubeApiCredentials credentials, YouTubePlaylistId youTubePlaylistId, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		youTubeDataApiClient.playlistItems().delete(videoId).execute();
	}
	
}
