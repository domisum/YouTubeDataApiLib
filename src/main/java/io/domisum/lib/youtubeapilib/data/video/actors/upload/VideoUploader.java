package io.domisum.lib.youtubeapilib.data.video.actors.upload;

import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.video.YouTubeVideo;

import java.io.IOException;

public interface VideoUploader
{
	
	String upload(YouTubeApiCredentials credentials, YouTubeVideo youTubeVideo, PrivacyStatus privacyStatus)
			throws IOException;
	
}
