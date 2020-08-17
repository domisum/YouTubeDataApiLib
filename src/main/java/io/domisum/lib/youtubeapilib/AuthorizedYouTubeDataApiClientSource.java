package io.domisum.lib.youtubeapilib;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class AuthorizedYouTubeDataApiClientSource
		extends AuthorizedYouTubeApiClientSource<YouTube>
{
	
	@Override
	protected YouTube build(HttpRequestInitializer requestInitializer)
	{
		return new YouTube(new NetHttpTransport(), new JacksonFactory(), requestInitializer);
	}
	
}
