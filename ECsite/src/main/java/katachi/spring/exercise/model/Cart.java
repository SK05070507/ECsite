package katachi.spring.exercise.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import katachi.spring.exercise.service.ShopService;
import katachi.spring.exercise.service.UserService;
import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<CartItem> itemList = new ArrayList<CartItem>();

	@Autowired
	UserService userService;

	@Autowired
	ShopService shopService;

	@Autowired
	Errors errors;

	public void add(Integer id, Integer inventory) {
		//在庫数よりも多い注文だとアラート表示
		if (inventory > shopService.inventoryOne(id)) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			return;
		}
		//
		for (CartItem item : itemList) {
			if (item.getItemid() == id) {
				item.add(inventory);
				return;
			}
		}
		MItem item = shopService.itemOne(id);
		itemList.add(new CartItem(item, inventory));

	}

	//カートリセット
	public void clear() {
		itemList.clear();
	}

	//カート内の商品個数
	public Integer count() {
		return itemList.size();
	}

	//合計金額
	public Integer money() {
		int total = 0;
		for (CartItem mItem : itemList) {
			total += mItem.getItemPrice() * mItem.getCartItemInventory();
		}
		return total;
	}

	public boolean change(Integer id, Integer inventory) {

		//在庫数よりも多い注文だとアラート表示
		if (inventory > shopService.inventoryOne(id)) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			return false;
		}

		int i = 0;
		for (CartItem item : itemList) {

			if (id == item.getItemid()) {
				// 今の個数を取得
				CartItem itemNew = itemList.get(i);
				itemNew.setCartItemInventory(inventory);

				itemList.set(i, itemNew);

			}
			i++;
		}
		return true;
	}

	public void deleted(Integer id) {
		//拡張for文中に削除を行うとエラーが出るので、イテレーターを使う
		Iterator<CartItem> iterator = itemList.iterator();
		while (iterator.hasNext()) {
			CartItem item = iterator.next();
			if (id == item.getItemid()) {
				iterator.remove();
			}
		}

	}

}
