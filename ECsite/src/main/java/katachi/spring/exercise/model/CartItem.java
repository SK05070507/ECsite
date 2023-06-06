package katachi.spring.exercise.model;

import lombok.Data;

@Data
public class CartItem {
	//商品ID
	private Integer itemid;
	//商品名
	private String itemName;
	//商品画像
	private String itemImage;
	//商品値段
	private Integer itemPrice;
	//商品在庫数
	private Integer itemInventory;
	//商品注文数
	private Integer cartItemInventory;

	//カートの中に既に同じ商品があった場合、元々の注文数と追加の注文数を足す処理。
	public void add(Integer inventory) {
		cartItemInventory += inventory;
	}

	public CartItem(MItem item, Integer inventory) {
		itemid = item.getId();
		itemName = item.getItemName();
		itemImage = item.getItemImage();
		itemPrice = item.getItemPrice();
		itemInventory = item.getInventory();
		cartItemInventory = inventory;
	}

}
