package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import com.google.api.services.youtube.model.Video;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.video.model.VideoDefinition;

import java.io.IOException;

public class VideoDefinitionFetcherImpl
	extends VideoInfoFetcher
	implements VideoDefinitionFetcher
{
	
	// INIT
	@Inject
	public VideoDefinitionFetcherImpl(AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource)
	{
		super(authorizedYouTubeDataApiClientSource);
	}
	
	
	// FETCH
	@Override
	public VideoDefinition fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException
	{
		var video = fetchVideo("contentDetails", credentials, videoId);
		return parseVideoDefinition(video);
	}
	
	private VideoDefinition parseVideoDefinition(Video video)
		throws IOException
	{
		String videoDefinitionString = video.getContentDetails().getDefinition();
		try
		{
			return VideoDefinition.valueOf(videoDefinitionString.toUpperCase());
		}
		catch(IllegalArgumentException ignored)
		{
			throw new IOException("Unexpected video definition: "+videoDefinitionString);
		}
	}
	
}
