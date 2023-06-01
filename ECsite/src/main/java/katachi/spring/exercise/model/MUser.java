package katachi.spring.exercise.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MUser implements UserDetails {

	private String userId;

	private String familyName;

	private String firstName;

	private Date birthday;

	private Integer gender;

	private String phoneNumber;

	private String postalCoder;
	private String prefectures;
	private String municipalities;
	private String number;

	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	private Collection<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return familyName + firstName;
	}

}