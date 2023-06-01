package katachi.spring.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import katachi.spring.exercise.application.service.UserApplicationService;
import katachi.spring.exercise.form.AddressForm;
import katachi.spring.exercise.form.ItemAddForm;
import katachi.spring.exercise.model.Cart;
import katachi.spring.exercise.model.CartItem;
import katachi.spring.exercise.model.MItem;
import katachi.spring.exercise.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	UserApplicationService applicationService;

	@Autowired
	ShopService shopService;

	@Autowired
	ModelMapper mapper;

	@Autowired
	Cart cart;

	//カート作成
	/**
	 * 
	 * @return
	 */
	@ModelAttribute("cart")
	public Cart getCart() {
		return new Cart();
	}

	//商品一覧画面へ遷移
	/**
	 * @param item 商品情報
	 * @param model
	 * @param pn ページ番号
	 * @param pageable　ページネーションインスタンス
	 * @return　商品一覧画面
	 */
	@GetMapping("/list")
	public String getitemlist(
			@ModelAttribute MItem item,
			Model model,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn
			) {
		//全件表示のためnull
		String itemName = null; 
		//全件表示のためnull
		Integer categoryId = null; 
		//ページネーション設定値(現在番号,表示件数)
		PageHelper.startPage(pn, 6); 	
		//商品リスト呼び出し
		List<MItem> itemList = shopService.findMany(itemName, categoryId); 
		//商品リストをページ情報に格納
		PageInfo<MItem> pageInfo = new PageInfo<>(itemList); 
		//カートの商品個数取得
		Integer count = cart.count(); 
		model.addAttribute("page", pageInfo);
		model.addAttribute("category", shopService.findCategory());
		model.addAttribute("count", count);
		model.addAttribute("cart", cart);
		return "/shop/list";
	}

	//商品詳細画面へ遷移
	/**
	 * 
	 * @param id 商品ID
	 * @param model
	 * @return 商品詳細画面
	 */
	@GetMapping("/list/details/{id}")
	public String getItemDetails(
			@PathVariable("id") Integer id,
			Model model) {
		//商品詳細情報取得
		MItem item = shopService.itemOne(id); 
		model.addAttribute("cart", cart);
		model.addAttribute("item", item);
		return "/shop/details";
	}

	//カートに商品を入れる処理
	/**
	 * 
	 * @param form 商品情報フォーム
	 * @param bindingResult
	 * @param model
	 * @return　商品一覧へ
	 */
	@PostMapping("/list/add")
	public String postItemAdd(
			@ModelAttribute ItemAddForm form,
			BindingResult bindingResult,
			Model model) {
		//商品情報フォームから商品IDと在庫数を取得して、カートに入れる処理
		cart.add(form.getId(), form.getCartItemInventory());
		return "redirect:/list";
	}

	//カートの中を確認する画面へ遷移
	/**
	 * 
	 * @param form 商品情報フォーム
	 * @param model
	 * @return カートの中
	 */
	@GetMapping("list/cart")
	public String getItemCart(
			@ModelAttribute ItemAddForm form,
			Model model) {
		//合計金額
		int money = cart.money();
		model.addAttribute("money", money);
		model.addAttribute("cart", cart);
		return "/shop/cart";
	}

	//住所入力画面へ遷移
	/**
	 * 
	 * @param addressForm 送付先住所フォーム
	 * @param model
	 * @return　住所入力画面
	 */
	@GetMapping("/list/addressForm")
	public String getItemBuy(
			@ModelAttribute AddressForm addressForm,
			Model model) {
		model.addAttribute("addressForm", addressForm);
		//都道府県リストを呼び出してmodelに格納
		model.addAttribute("list", applicationService.getPrefectureList());
		return "/shop/addressForm";
	}

	//注文内容確認画面へ遷移
	/**
	 * 
	 * @param addressForm 送付先住所フォーム
	 * @param model
	 * @return 注文内容確認
	 */
	@PostMapping("/list/addressForm")
	public String postItemBuy(
			@ModelAttribute AddressForm addressForm,
			Model model) {
		//合計金額
		int money = 0;
		money = cart.money();
		model.addAttribute("money", money);
		model.addAttribute("addressForm", addressForm);
		model.addAttribute("cart", cart);
		return "/shop/voucher";
	}

	//注文受付完了
	/**
	 * 
	 * @param addressForm 送付先住所フォーム
	 * @return 注文受付完了
	 */
	@PostMapping(value = "/list/voucher", params = "order")
	public String postItemOrder(
			@ModelAttribute AddressForm addressForm) {
		//カートの中の商品に応じて在庫数を減らす
		ArrayList<CartItem> itemList;
		itemList = cart.getItemList();
		//カートの商品の注文数を在庫数から引いた数を在庫数として更新する
		for (CartItem item : itemList) {
			int itemInventory = shopService.inventoryOne(item.getItemid());
			itemInventory -= item.getCartItemInventory();
			shopService.updateOne(itemInventory, item.getItemid());
		}
		//カートのリセット
		cart.clear();
		return "/shop/orderCompleted";
	}

	//注文内容修正
	/**
	 * 
	 * @param addressForm 送付先住所フォーム
	 * @param model
	 * @return 注文内容修正
	 */
	@PostMapping(value = "/list/voucher", params = "revision")
	public String postItemOrderRevision(
			@ModelAttribute AddressForm addressForm, Model model) {
		//途中まで書いていた内容を保持
		model.addAttribute("addressForm", addressForm);
		//都道府県リストを呼び出してmodelに格納
		model.addAttribute("list", applicationService.getPrefectureList());
		return "/shop/addressForm";
	}

	//カートの中の商品数量変更
	/**
	 * 
	 * @param form 商品情報フォーム
	 * @param bindingResult
	 * @param model
	 * @param errors
	 * @return カートの中へ
	 */
	@PostMapping(value = "/list/cartItem", params = "change")
	public String postChangeCartItem(
			@ModelAttribute ItemAddForm form,
			BindingResult bindingResult,
			Model model,
			Errors errors) {
		//在庫数より多い注文数だった場合エラー表示
		if (cart.change(form.getId(), form.getCartItemInventory()) == false) {
			errors.rejectValue("cartItemInventory", "cartItemInventory.over");
			bindingResult.hasErrors();
			return getItemCart(form, model);
		}
		return "redirect:/list/cart";
	}

	//カートの中の商品を削除
	/**
	 * 
	 * @param form 商品情報フォーム
	 * @param model
	 * @return カートの中へ
	 */
	@PostMapping(value = "/list/cartItem", params = "deleted")
	public String postDeletedCartItem(
			@ModelAttribute ItemAddForm form, Model model) {
		//指定したカートの中の商品を削除する
		cart.deleted(form.getId());
		return "redirect:/list/cart";
	}

	//商品検索結果を表示
	/**
	 * 
	 * @param itemName 商品名
	 * @param categoryId　商品カテゴリー
	 * @param pn 現在ページ
	 * @param model
	 * @return　検索結果したページへ
	 */
	@GetMapping(value = "/list/search")
	public String searchItem(
			@RequestParam(required = false, value = "itemName", defaultValue = "") String itemName,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		//ページネーション設定値(現在番号,表示件数)
		PageHelper.startPage(pn, 6);
		//商品リスト呼び出し
		List<MItem> itemList = shopService.findMany(itemName, categoryId);
		//商品リストをページ情報に格納
		PageInfo<MItem> pageInfo = new PageInfo<>(itemList);
		//カートの商品個数取得
		Integer count = cart.count();
		model.addAttribute("page", pageInfo);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("itemName", itemName);
		//カテゴリー取得
		model.addAttribute("category", shopService.findCategory());
		model.addAttribute("count", count);
		model.addAttribute("cart", cart);
		return "/shop/list";
	}

}
