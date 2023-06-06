package katachi.spring.exercise.model;

import java.util.Date;

import lombok.Data;

@Data
public class MItem {
	//商品ID
	private Integer id;
	//商品名
	private String itemName;
	//商品画像
	private String itemImage;
	//商品値段
	private Integer itemPrice;
	//商品説明
	private String itemDescription;
	//削除フラグ
	private Integer isDeleted;
	//DB登録日
	private Date createDateTime;
	//DB更新日
	private Date updateDateTime;
	//在庫数
	private Integer inventory;
	//カテゴリーID
	private Integer categoryId;
}
