package katachi.spring.exercise.model;

import java.util.Date;

import lombok.Data;

@Data
public class MItem {

	private Integer id;
	private String itemName;
	private String itemImage;
	private Integer itemPrice;
	private String itemDescription;
	private Integer isDeleted;
	private Date createDateTime;
	private Date updateDateTime;
	private Integer inventory;
	private Integer categoryId;
}
