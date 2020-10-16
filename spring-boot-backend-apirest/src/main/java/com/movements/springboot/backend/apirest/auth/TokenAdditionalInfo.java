package com.movements.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.movements.springboot.backend.apirest.models.entity.AppUser;
import com.movements.springboot.backend.apirest.models.services.IUserService;

@Component
public class TokenAdditionalInfo implements TokenEnhancer {
	
	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		AppUser user = userService.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal ".concat(authentication.getName()));
		info.put("datos usuario", user.getId() + ": " + user.getUsername() + " mail: "+ user.getEmail());
		info.put("nom", user.getName());
		info.put("cognom", user.getLastName());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
	

}
