package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoPrivacyStatusFetcher;

import java.io.IOException;

public class VideoPrivacyStatusFetcherUsingApi
	extends VideoInfoFetcherUsingApi
	implements VideoPrivacyStatusFetcher
{
	
	// INIT
	@Inject
	public VideoPrivacyStatusFetcherUsingApi(AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource)
	{
		super(authorizedYouTubeDataApiClientSource);
	}
	
	
	// FETCH
	@Override
	public PrivacyStatus fetchPrivacyStatus(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var video = fetchVideo("status", credentials, videoId);
		String privacyStatusString = video.getStatus().getPrivacyStatus();
		return PrivacyStatus.parse(privacyStatusString);
	}
	
}
