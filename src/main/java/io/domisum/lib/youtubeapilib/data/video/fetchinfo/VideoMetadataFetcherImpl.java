package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.model.VideoCategory;
import io.domisum.lib.youtubeapilib.data.video.model.YouTubeVideoMetadata;

import java.io.IOException;

public class VideoMetadataFetcherImpl
	extends VideoInfoFetcher
	implements VideoMetadataFetcher
{
	
	// INIT
	@Inject
	public VideoMetadataFetcherImpl(AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource)
	{
		super(authorizedYouTubeDataApiClientSource);
	}
	
	
	// FETCH
	@Override
	public YouTubeVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var video = fetchVideo("snippet", credentials, videoId);
		
		String title = video.getSnippet().getTitle();
		String description = video.getSnippet().getDescription();
		var tags = video.getSnippet().getTags();
		String categoryIdString = video.getSnippet().getCategoryId();
		var videoCategory = VideoCategory.fromCategoryId(Integer.parseInt(categoryIdString));
		
		return new YouTubeVideoMetadata(title, description, tags, videoCategory);
	}
	
}
