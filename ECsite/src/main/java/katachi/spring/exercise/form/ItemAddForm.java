package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class ItemAddForm {
	//商品ID
	private Integer id;
	//注文数
	private Integer cartItemInventory;
}
