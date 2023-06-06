package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class AddressForm {
	//送り先宛名
	private String name; 
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
