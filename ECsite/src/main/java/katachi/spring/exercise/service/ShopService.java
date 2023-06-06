package katachi.spring.exercise.service;

import java.util.List;

import katachi.spring.exercise.model.CartItem;
import katachi.spring.exercise.model.MCategory;
import katachi.spring.exercise.model.MItem;

public interface ShopService {

	//商品一覧取得
	public List<MItem> findMany(String itemName, Integer categoryId);

	//商品取得(1件)
	public MItem itemOne(Integer id);

	//在庫数更新
	public void updateOne(Integer itemInventory, Integer id);

	//在庫数取得(1件)
	public Integer inventoryOne(Integer id);

	//カートの中の商品取得(1件)
	public CartItem cartItemOne(Integer id);

	//カテゴリ一覧取得
	public List<MCategory> findCategory();
}
