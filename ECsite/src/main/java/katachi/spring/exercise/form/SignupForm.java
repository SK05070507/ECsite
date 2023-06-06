package katachi.spring.exercise.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data

public class SignupForm {
	@NotBlank
	@Email
	//ユーザーID
	private String userId;
	@NotBlank
	@Length(min = 4, max = 100)
	//パスワード
	private String password;
	@NotBlank
	//苗字
	private String familyName;
	@NotBlank
	//名前
	private String firstName;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	//誕生日
	private Date birthday;
	@NotNull
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
}
