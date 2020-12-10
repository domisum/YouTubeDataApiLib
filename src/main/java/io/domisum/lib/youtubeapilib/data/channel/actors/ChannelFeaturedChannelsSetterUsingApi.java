package io.domisum.lib.youtubeapilib.data.channel.actors;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelBrandingSettings;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.channel.FeaturedChannels;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelFeaturedChannelsSetterUsingApi
	implements ChannelFeaturedChannelsSetter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource apiClientSource;
	
	
	// SET
	@Override
	public void set(YouTubeApiCredentials credentials, String channelId, FeaturedChannels featuredChannels)
		throws IOException
	{
		var youTube = apiClientSource.getFor(credentials);
		
		var channel = new Channel();
		channel.setId(channelId);
		channel.setBrandingSettings(buildBrandingSettings(featuredChannels));
		
		var update = youTube.channels().update("brandingSettings", channel);
		update.execute();
	}
	
	private ChannelBrandingSettings buildBrandingSettings(FeaturedChannels featuredChannels)
	{
		var brandingSettings = new ChannelBrandingSettings();
		
		var channel = brandingSettings.getChannel();
		channel.setFeaturedChannelsTitle(featuredChannels.getTitle());
		channel.setFeaturedChannelsUrls(featuredChannels.getChannelIds());
		
		return brandingSettings;
	}
	
}
