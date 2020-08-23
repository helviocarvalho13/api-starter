package com.ingaia.apistarter.model.spotify.json;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistJSON {

	private List<ItemPlaylistJSON> items;
	
}
