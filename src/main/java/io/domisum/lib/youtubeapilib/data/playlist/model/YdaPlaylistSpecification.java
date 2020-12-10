package io.domisum.lib.youtubeapilib.data.playlist.model;

import io.domisum.lib.youtubeapilib.data.PrivacyStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class YdaPlaylistSpecification
{
	
	@Getter
	private final String title;
	@Getter
	private final String description;
	@Getter
	private final PrivacyStatus privacyStatus;
	
}
