package io.domisum.lib.youtubeapilib.data.video.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.VideoDefinition;

import java.io.IOException;

public interface VideoDefinitionFetcher
{
	
	VideoDefinition fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
