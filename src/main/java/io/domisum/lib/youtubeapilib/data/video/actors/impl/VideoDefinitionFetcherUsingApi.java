package io.domisum.lib.youtubeapilib.data.video.actors.impl;

import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.VideoDefinition;
import io.domisum.lib.youtubeapilib.data.video.VideoDoesNotExistException;
import io.domisum.lib.youtubeapilib.data.video.actors.VideoDefinitionFetcher;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VideoDefinitionFetcherUsingApi
	implements VideoDefinitionFetcher
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// FETCH
	@Override
	public VideoDefinition fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var youTubeDataApiClient = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var videosListByIdRequest = youTubeDataApiClient.videos().list("contentDetails");
		videosListByIdRequest.setId(videoId);
		var response = videosListByIdRequest.execute();
		
		var responseItems = response.getItems();
		if(responseItems.isEmpty())
			throw new VideoDoesNotExistException(videoId);
		var video = responseItems.get(0);
		
		String definitionString = video.getContentDetails().getDefinition();
		try
		{
			return VideoDefinition.valueOf(definitionString.toUpperCase());
		}
		catch(IllegalArgumentException ignored)
		{
			throw new IOException("Unexpected video definition: "+definitionString);
		}
	}
	
}
