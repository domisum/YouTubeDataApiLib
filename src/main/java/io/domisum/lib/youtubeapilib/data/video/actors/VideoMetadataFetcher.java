package io.domisum.lib.youtubeapilib.data.video.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.YouTubeVideoMetadata;

import java.io.IOException;

public interface VideoMetadataFetcher
{
	
	YouTubeVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
