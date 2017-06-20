package com.cirtual.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cirtual.entity.User;
import com.cirtual.restrepository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private HttpSession httpSession;
	
	public final String CURRENT_USER_KEY = "CURRENT_USER";
	
	@Value("${app.secret}")
    private String applicationSecret;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("in service");
		System.out.println("username: " + username);
		System.out.println("pwd: " + username);
		User user = userRepository.findOneByUserName(username);
		
		if(user == null) {
            throw new UsernameNotFoundException(username);
        }
		
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auth);
	}
	
	public void autoLogin(User user) {
        autoLogin(user.getUserName());
    }
	
	public void autoLogin(String username) {
        UserDetails userDetails = this.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (userDetails, null, userDetails.getAuthorities());
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        if(auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
	
	public User register(User user) {
        user.setPassword(encodeUserPassword(user.getPassword()));

        if (this.userRepository.findOneByUserName(user.getUserName()) == null && this.userRepository.findOneByEmailId(user.getEmailId()) == null) {
            this.userRepository.save(user);
            return user;
        }
        return null;
    }
	
	public String encodeUserPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
	
	public String createActivationToken(User user, Boolean save) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String activationToken = encoder.encodePassword(user.getUserName(), applicationSecret);
        if(save) {
            this.userRepository.save(user);
        }
        return activationToken;
    }
	
	public void updateUser(User user) {
        updateUser(user.getUserName(), user);
    }
	
	public void updateUser(String userName, User newData) {
        this.userRepository.updateUser(
                userName, 
                newData.getFirstName(), 
                newData.getLastName(), 
                newData.getAge(), 
                newData.getProfession());
    }
	
	public User getLoggedInUser() {
        return getLoggedInUser(false);
    }
    
    public User getLoggedInUser(Boolean forceFresh) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) httpSession.getAttribute(CURRENT_USER_KEY);
        if(forceFresh || httpSession.getAttribute(CURRENT_USER_KEY) == null) {
            user = this.userRepository.findOneByUserName(userName);
            httpSession.setAttribute(CURRENT_USER_KEY, user);
        }
        return user;
    }
}
