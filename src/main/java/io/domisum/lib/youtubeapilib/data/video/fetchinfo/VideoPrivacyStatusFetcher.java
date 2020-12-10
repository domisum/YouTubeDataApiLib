package io.domisum.lib.youtubeapilib.data.video.fetchinfo;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;

import java.io.IOException;

public interface VideoPrivacyStatusFetcher
{
	
	PrivacyStatus fetchPrivacyStatus(YouTubeApiCredentials credentials, String videoId)
		throws IOException;
	
}
