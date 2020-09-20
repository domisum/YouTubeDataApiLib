package io.domisum.lib.youtubeapilib.data.video.actors.impl.upload;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import io.domisum.lib.auxiliumlib.exceptions.IncompleteCodeError;
import io.domisum.lib.auxiliumlib.util.math.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

abstract class YouTubeUploader
{
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	// CONSTANTS
	private static final int UPLOAD_CHUNK_SIZE_BYTES = 128*1024*1024;
	
	
	// MEDIA HTTP UPLOADER
	protected void configureMediaHttpUploader(MediaHttpUploader mediaHttpUploader)
	{
		mediaHttpUploader.setChunkSize(UPLOAD_CHUNK_SIZE_BYTES);
		mediaHttpUploader.setProgressListener(getUploadProgressListener());
	}
	
	private MediaHttpUploaderProgressListener getUploadProgressListener()
	{
		return this::logUploadProgress;
	}
	
	private void logUploadProgress(MediaHttpUploader mediaHttpUploader)
		throws IOException
	{
		switch(mediaHttpUploader.getUploadState())
		{
			case INITIATION_STARTED:
				logger.info("Upload initiation started");
				break;
			case INITIATION_COMPLETE:
				logger.info("Upload initiation complete");
				break;
			case MEDIA_IN_PROGRESS:
				logger.info("Upload in progress: {}%", MathUtil.round(mediaHttpUploader.getProgress()*100, 1));
				break;
			case MEDIA_COMPLETE:
				logger.info("Upload in progress: 100.0%");
				logger.info("Upload complete");
				break;
			case NOT_STARTED:
				logger.info("Upload not started");
				break;
			
			default: throw new IncompleteCodeError();
		}
	}
	
}
