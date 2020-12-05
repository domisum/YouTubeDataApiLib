package io.domisum.lib.youtubeapilib.data.video.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.YdaVideoMetadata;

import java.io.IOException;

public interface VideoMetadataSetter
{
	
	void setMetadata(YouTubeApiCredentials credentials, String videoId, YdaVideoMetadata metadata)
		throws IOException;
	
}
