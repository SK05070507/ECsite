package katachi.spring.exercise.service;

import katachi.spring.exercise.model.MUser;

public interface UserService {

	/** ユーザー登録 */
	public void signup(MUser user);

	//住所登録
	public void insertAddress(MUser user);

	//ユーザーID重複チェック
	public boolean userIdOne(String userId);
}
