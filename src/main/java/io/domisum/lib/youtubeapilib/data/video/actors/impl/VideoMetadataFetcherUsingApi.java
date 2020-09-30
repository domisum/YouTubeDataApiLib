package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.VideoCategory;
import io.domisum.lib.youtubeapilib.data.video.VideoDoesNotExistException;
import io.domisum.lib.youtubeapilib.data.video.YouTubeVideoMetadata;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoMetadataFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoMetadataFetcherUsingApi
	implements VideoMetadataFetcher
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public YouTubeVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var videosListByIdRequest = youTubeDataApiClient.videos().list("snippet");
		videosListByIdRequest.setId(videoId);
		var response = videosListByIdRequest.execute();
		
		var responseItems = response.getItems();
		if(responseItems.isEmpty())
			throw new VideoDoesNotExistException(videoId);
		var video = responseItems.get(0);
		
		String title = video.getSnippet().getTitle();
		String description = video.getSnippet().getDescription();
		var tags = video.getSnippet().getTags();
		String categoryIdString = video.getSnippet().getCategoryId();
		var videoCategory = VideoCategory.fromCategoryId(Integer.parseInt(categoryIdString));
		
		var youTubeVideoMetadata = new YouTubeVideoMetadata(title, description, tags, videoCategory);
		
		return youTubeVideoMetadata;
	}
	
}
