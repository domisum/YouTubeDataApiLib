package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.api.services.youtube.YouTube.Videos.Update;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.YouTubeVideoMetadata;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoMetadataSetter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoMetadataSetterUsingApi
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
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var video = new Video();
		video.setId(videoId);
		video.setSnippet(createVideoSnippet(metadata));
		var update = youTubeDataApiClient.videos().update("snippet", video);
		
		return update;
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
