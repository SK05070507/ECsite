package katachi.spring.exercise.form;

import lombok.Data;

@Data
public class ItemAddForm {

	private Integer id; //商品ID
	private Integer cartItemInventory; //注文数
}
