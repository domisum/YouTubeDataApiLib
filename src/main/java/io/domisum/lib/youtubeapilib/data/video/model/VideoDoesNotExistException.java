package io.domisum.lib.youtubeapilib.data.video.model;

import io.domisum.lib.auxiliumlib.PHR;

import java.io.IOException;

public class VideoDoesNotExistException
	extends IOException
{
	
	// INIT
	public static VideoDoesNotExistException ofVideoId(String videoId)
	{
		return new VideoDoesNotExistException(videoId);
	}
	
	protected VideoDoesNotExistException(String videoId)
	{
		super(PHR.r("There is no video with id '{}' (visible with current permissions)", videoId));
	}
	
}
