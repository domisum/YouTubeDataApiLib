package io.domisum.lib.youtubeapilib.data.video.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.YdaVideoMetadata;

import java.io.IOException;

public interface VideoMetadataFetcher
{
	
	YdaVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
