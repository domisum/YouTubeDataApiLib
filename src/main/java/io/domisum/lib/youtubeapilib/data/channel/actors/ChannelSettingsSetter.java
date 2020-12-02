package io.domisum.lib.youtubeapilib.data.channel.actors;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.channel.YouTubeChannelSettings;

import java.io.IOException;

public interface ChannelSettingsSetter
{
	
	void set(YouTubeApiCredentials credentials, String channelId, YouTubeChannelSettings settings)
		throws IOException;
	
}
