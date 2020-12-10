package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;

import java.io.IOException;

public class VideoPrivacyStatusFetcherImpl
	extends VideoInfoFetcher
	implements VideoPrivacyStatusFetcher
{
	
	// INIT
	@Inject
	public VideoPrivacyStatusFetcherImpl(AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource)
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
