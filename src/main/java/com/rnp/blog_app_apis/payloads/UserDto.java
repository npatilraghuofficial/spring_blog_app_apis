package com.rnp.blog_app_apis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	public int id;
	public String name;
	public String password;
	public String about;
	public String email;
	
	
}
