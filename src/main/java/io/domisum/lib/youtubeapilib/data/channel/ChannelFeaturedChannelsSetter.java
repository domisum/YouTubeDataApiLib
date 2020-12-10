package io.domisum.lib.youtubeapilib.data.channel;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.channel.model.FeaturedChannels;

import java.io.IOException;

public interface ChannelFeaturedChannelsSetter
{
	
	void set(YouTubeApiCredentials credentials, String channelId, FeaturedChannels featuredChannels)
		throws IOException;
	
}
