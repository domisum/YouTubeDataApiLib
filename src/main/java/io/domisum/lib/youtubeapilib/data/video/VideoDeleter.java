package io.domisum.lib.youtubeapilib.data.video;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.io.IOException;

public interface VideoDeleter
{
	
	void delete(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
