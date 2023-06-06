package katachi.spring.exercise.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MUser implements UserDetails {
	//ユーザーID
	private String userId;
	//苗字
	private String familyName;
	//名前
	private String firstName;
	//誕生日
	private Date birthday;
	//性別
	private Integer gender;
	//電話番号
	private String phoneNumber;
	//郵便番号
	private String postalCoder;
	//都道府県
	private String prefectures;
	//市区町村
	private String municipalities;
	//番地
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