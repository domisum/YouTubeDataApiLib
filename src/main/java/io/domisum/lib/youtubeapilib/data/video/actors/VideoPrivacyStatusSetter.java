package io.domisum.lib.youtubeapilib.data.video.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.PrivacyStatus;

import java.io.IOException;

public interface VideoPrivacyStatusSetter
{
	
	void setPrivacyStatus(YouTubeApiCredentials credentials, String videoId, PrivacyStatus privacyStatus)
		throws IOException;
	
}
