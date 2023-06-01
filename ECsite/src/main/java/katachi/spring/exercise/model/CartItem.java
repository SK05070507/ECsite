package katachi.spring.exercise.model;

import lombok.Data;

@Data
public class CartItem {

	private Integer itemid;
	private String itemName;
	private String itemImage;
	private Integer itemPrice;
	private Integer itemInventory;
	private Integer cartItemInventory;

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
