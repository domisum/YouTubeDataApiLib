package io.domisum.lib.youtubeapilib.data.video.upload;

import io.domisum.lib.youtubeapilib.YouTubeApiCredentials;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface VideoThumbnailUploader
{
	
	void uploadThumbnail(YouTubeApiCredentials credentials, String videoId, BufferedImage thumbnail)
		throws IOException;
	
}
