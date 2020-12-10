package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.model.YouTubeVideoMetadata;

import java.io.IOException;

public interface VideoMetadataFetcher
{
	
	YouTubeVideoMetadata fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
