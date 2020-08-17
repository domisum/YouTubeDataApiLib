package io.domisum.lib.youtubeapilib.data;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import io.domisum.lib.youtubeapilib.AuthorizedYouTubeApiClientSource;

public class AuthorizedYouTubeDataApiClientSource
		extends AuthorizedYouTubeApiClientSource<YouTube>
{
	
	@Override
	protected YouTube build(HttpRequestInitializer requestInitializer)
	{
		var builder = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), requestInitializer);
		builder.setApplicationName("YouTubeDataApiLib");
		
		return builder.build();
	}
	
}
