package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.model.VideoDoesNotExistException;
import io.domisum.lib.youtubeapilib.data.video.model.VideoNotYetProcessedException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoDurationFetcherImpl
	implements VideoDurationFetcher
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public Duration fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var videosListByIdRequest = youTubeDataApiClient.videos().list("contentDetails");
		videosListByIdRequest.setId(videoId);
		var response = videosListByIdRequest.execute();
		
		var responseItems = response.getItems();
		if(responseItems.isEmpty())
			throw VideoDoesNotExistException.ofVideoId(videoId);
		String durationString = responseItems.get(0).getContentDetails().getDuration();
		var duration = Duration.parse(durationString);
		
		if(duration.isZero())
			throw VideoNotYetProcessedException.ofVideoId(videoId);
		return duration;
	}
	
}
