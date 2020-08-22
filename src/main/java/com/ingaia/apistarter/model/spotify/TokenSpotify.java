package com.ingaia.apistarter.model.spotify;

import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenSpotify {

	private String access_token;
	private String token_type;
	private String expires_in;
	private String scope;
	
	@Resource(name = "applicationScopedBean")
	public static TokenSpotify scopedToken;
	
	public TokenSpotify() {}
	
	public void clone(TokenSpotify tokenSpotify) {
		this.access_token = tokenSpotify.getAccess_token();
		this.token_type = tokenSpotify.getToken_type();
		this.expires_in = tokenSpotify.getExpires_in();
		this.scope = tokenSpotify.getScope();
	}
	
}
