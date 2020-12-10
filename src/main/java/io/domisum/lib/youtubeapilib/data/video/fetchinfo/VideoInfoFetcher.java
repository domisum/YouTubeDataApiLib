package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import com.google.api.services.youtube.model.Video;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.VideoDoesNotExistException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public abstract class VideoInfoFetcher
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	protected Video fetchVideo(String part, YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var videosListByIdRequest = youTubeDataApiClient.videos().list(part);
		videosListByIdRequest.setId(videoId);
		var response = videosListByIdRequest.execute();
		
		var responseItems = response.getItems();
		if(responseItems.isEmpty())
			throw new VideoDoesNotExistException(videoId);
		return responseItems.get(0);
	}
	
}
