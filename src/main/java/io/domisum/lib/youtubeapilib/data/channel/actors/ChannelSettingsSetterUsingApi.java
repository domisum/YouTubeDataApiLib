package io.domisum.lib.youtubeapilib.data.channel.actors;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelBrandingSettings;
import com.google.inject.Inject;
import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;
import io.domisum.lib.youtubeapilib.data.AuthorizedYouTubeDataApiClientSource;
import io.domisum.lib.youtubeapilib.data.channel.YouTubeChannelSettings;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChannelSettingsSetterUsingApi
	implements ChannelSettingsSetter
{
	
	// DEPENDENCIES
	private final AuthorizedYouTubeDataApiClientSource authorizedYouTubeDataApiClientSource;
	
	
	// SET
	@Override
	public void set(YouTubeApiCredentials credentials, String channelId, YouTubeChannelSettings newSettings)
		throws IOException
	{
		var youTube = authorizedYouTubeDataApiClientSource.getFor(credentials);
		
		var brandingSettings = fetchCurrentBrandingSettings(youTube, channelId);
		applyNewSettings(newSettings, brandingSettings);
		setBrandingSettings(youTube, channelId, brandingSettings);
	}
	
	private ChannelBrandingSettings fetchCurrentBrandingSettings(YouTube youTube, String channelId)
		throws IOException
	{
		var list = youTube.channels().list("brandingSettings");
		list.setId(channelId);
		var listResponse = list.execute();
		
		var channels = listResponse.getItems();
		if(channels.isEmpty())
			throw new IOException("Channel not found: "+channelId);
		var channel = channels.get(0);
		
		return channel.getBrandingSettings();
	}
	
	private void applyNewSettings(YouTubeChannelSettings newSettings, ChannelBrandingSettings brandingSettings)
	{
		var channel = brandingSettings.getChannel();
		if(newSettings.getChannelDescription() != null)
			channel.setDescription(newSettings.getChannelDescription());
		if(newSettings.getUnsubscribedTrailerVideoId() != null)
			channel.setUnsubscribedTrailer(newSettings.getUnsubscribedTrailerVideoId());
		if(newSettings.getFeaturedChannels() != null)
		{
			var featuredChannels = newSettings.getFeaturedChannels();
			channel.setFeaturedChannelsTitle(featuredChannels.getTitle());
			channel.setFeaturedChannelsUrls(featuredChannels.getChannelIds());
		}
	}
	
	private void setBrandingSettings(YouTube youTube, String channelId, ChannelBrandingSettings brandingSettings)
		throws IOException
	{
		var channel = new Channel();
		channel.setId(channelId);
		channel.setBrandingSettings(brandingSettings);
		
		var update = youTube.channels().update("brandingSettings", channel);
		update.execute();
	}
	
}
