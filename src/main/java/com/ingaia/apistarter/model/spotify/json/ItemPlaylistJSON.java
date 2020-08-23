package com.ingaia.apistarter.model.spotify.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPlaylistJSON {

	private String id;
	private String description;
	private String href;
	private ExternalUrlsJSON external_urls;
}
