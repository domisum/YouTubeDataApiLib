package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.VideoCategory;
import io.domisum.lib.youtubeapilib.data.video.YdaVideoMetadata;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoMetadataFetcher;

import java.io.IOException;

public class VideoMetadataFetcherUsingApi
	extends VideoInfoFetcherUsingApi
	implements VideoMetadataFetcher
{
	
	// INIT
	@Inject
	public VideoMetadataFetcherUsingApi(AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource)
	{
		super(authorizedYouTubeDataApiClientSource);
	}
	
	
	// FETCH
	@Override
	public YdaVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var video = fetchVideo("snippet", credentials, videoId);
		
		String title = video.getSnippet().getTitle();
		String description = video.getSnippet().getDescription();
		var tags = video.getSnippet().getTags();
		String categoryIdString = video.getSnippet().getCategoryId();
		var videoCategory = VideoCategory.fromCategoryId(Integer.parseInt(categoryIdString));
		
		return new YdaVideoMetadata(title, description, tags, videoCategory);
	}
	
}
