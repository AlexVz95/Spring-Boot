package com.udemy.backendninja.services.servicesimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
