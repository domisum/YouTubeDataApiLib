package io.domisum.lib.youtubeapilib.data.video.model;

import io.domisum.lib.auxiliumlib.PHR;

import java.io.IOException;

public class VideoNotYetProcessedException
	extends IOException
{
	
	// INIT
	public static VideoNotYetProcessedException ofVideoId(String videoId)
	{
		return new VideoNotYetProcessedException(videoId);
	}
	
	protected VideoNotYetProcessedException(String videoId)
	{
		super(PHR.r("The video with id '{}' is not yet processed", videoId));
	}
	
}
