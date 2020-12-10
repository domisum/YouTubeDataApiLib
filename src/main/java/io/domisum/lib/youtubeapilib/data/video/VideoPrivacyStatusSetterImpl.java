package io.domisum.lib.youtubeapilib.data.video;

import com.google.api.services.youtube.YouTube.Videos.Update;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoPrivacyStatusSetterImpl
	implements VideoPrivacyStatusSetter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// SET
	@Override
	public void setPrivacyStatus(YouTubeApiCredentials credentials, String videoId, PrivacyStatus privacyStatus)
		throws IOException
	{
		var videosUpdateRequest = createRequest(credentials, videoId, privacyStatus);
		videosUpdateRequest.execute();
	}
	
	private Update createRequest(YouTubeApiCredentials credentials, String videoId, PrivacyStatus privacyStatus)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var video = new Video();
		video.setId(videoId);
		
		var status = new VideoStatus();
		status.setPrivacyStatus(privacyStatus.name());
		video.setStatus(status);
		
		return youTubeDataApiClient.videos().update("status", video);
	}
	
}
