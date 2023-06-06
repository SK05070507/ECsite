package katachi.spring.exercise.model;

import lombok.Data;

@Data
public class Inventory {
	//商品ID
	private Integer itemId;
	//在庫数
	private Integer inventory;
}
