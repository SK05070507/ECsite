package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class AddressForm {

	private String name; //送り先宛名
	private String phoneNumber; //電話番号
	private String postalCoder; //郵便番号
	private String prefectures; //都道府県
	private String municipalities; //市区町村
	private String number; //番地

}
