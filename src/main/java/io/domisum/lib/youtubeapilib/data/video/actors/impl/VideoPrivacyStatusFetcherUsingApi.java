package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import io.domisum.lib.youtubeapilib.data.video.VideoDoesNotExistException;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoPrivacyStatusFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoPrivacyStatusFetcherUsingApi
	implements VideoPrivacyStatusFetcher
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public PrivacyStatus fetchPrivacyStatus(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		var fetch = youTubeDataApiClient.videos().list("status");
		fetch.setId(videoId);
		
		var listResponse = fetch.execute();
		
		var responseItems = listResponse.getItems();
		if(responseItems.isEmpty())
			throw new VideoDoesNotExistException(videoId);
		var video = responseItems.get(0);
		
		String privacyStatusString = video.getStatus().getPrivacyStatus();
		return PrivacyStatus.parse(privacyStatusString);
	}
	
}
