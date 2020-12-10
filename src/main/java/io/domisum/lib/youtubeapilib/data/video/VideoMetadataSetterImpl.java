package io.domisum.lib.youtubeapilib.data.video;

import com.google.api.services.youtube.YouTube.Videos.Update;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.model.YouTubeVideoMetadata;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoMetadataSetterImpl
	implements VideoMetadataSetter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// SET
	@Override
	public void setMetadata(YouTubeApiCredentials credentials, String videoId, YouTubeVideoMetadata metadata)
		throws IOException
	{
		var videosUpdateRequest = createRequest(credentials, videoId, metadata);
		videosUpdateRequest.execute();
	}
	
	private Update createRequest(YouTubeApiCredentials credentials, String videoId, YouTubeVideoMetadata metadata)
		throws IOException
	{
		var video = new Video();
		video.setId(videoId);
		video.setSnippet(createVideoSnippet(metadata));
		
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		return youTubeDataApiClient.videos().update("snippet", video);
	}
	
	private VideoSnippet createVideoSnippet(YouTubeVideoMetadata metadata)
	{
		var snippet = new VideoSnippet();
		snippet.setTitle(metadata.getTitle());
		snippet.setDescription(metadata.getDescription());
		snippet.setTags(metadata.getTags());
		snippet.setCategoryId(metadata.getCategory().categoryId+"");
		
		return snippet;
	}
	
}
