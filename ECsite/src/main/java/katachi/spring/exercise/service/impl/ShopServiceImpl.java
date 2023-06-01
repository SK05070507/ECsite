package katachi.spring.exercise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.exercise.model.CartItem;
import katachi.spring.exercise.model.MCategory;
import katachi.spring.exercise.model.MItem;
import katachi.spring.exercise.repository.ShopMapper;
import katachi.spring.exercise.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper mapper;

	//	商品一覧取得
	@Override
	public List<MItem> findMany(String itemName, Integer categoryId) {
		return mapper.findMany(itemName, categoryId);
	}

	//商品取得(1件)
	@Override
	public MItem itemOne(Integer id) {
		return mapper.itemOne(id);
	}

	//在庫数更新
	@Override
	public void updateOne(Integer itemInventory, Integer id) {
		mapper.updateOne(itemInventory, id);
	}

	//在庫数取得(1件)
	@Override
	public Integer inventoryOne(Integer id) {
		return mapper.inventoryOne(id);
	}

	//カートの中の商品取得(1件)
	@Override
	public CartItem cartItemOne(Integer id) {
		return mapper.cartItemOne(id);
	}

	//カテゴリ一覧取得
	@Override
	public List<MCategory> findCategory() {
		return mapper.findAllCategory();
	}
}
