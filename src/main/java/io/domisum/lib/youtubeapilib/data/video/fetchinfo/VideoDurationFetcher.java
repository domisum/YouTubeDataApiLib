package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.io.IOException;
import java.time.Duration;

public interface VideoDurationFetcher
{
	
	Duration fetch(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
