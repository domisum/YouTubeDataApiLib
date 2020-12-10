package io.domisum.lib.youtubeapilib.data.video;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoDeleterImpl
	implements VideoDeleter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// DELETE
	@Override
	public void delete(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		var videosUpdateRequest = youTubeDataApiClient.videos().delete(videoId);
		videosUpdateRequest.execute();
	}
	
}
