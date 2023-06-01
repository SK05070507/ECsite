package katachi.spring.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import katachi.spring.exercise.model.MUser;
import katachi.spring.exercise.repository.UserMapper;
import katachi.spring.exercise.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	/** ユーザー登録 住所登録 */
	@Transactional
	@Override
	public void signup(MUser user) {
		String pass = user.getPassword();
		user.setPassword(encoder.encode(pass));
		mapper.insertOne(user);
		mapper.insertAddressOne(user);
	}

	//住所登録
	@Override
	public void insertAddress(MUser user) {
		mapper.insertAddressOne(user);
	}

	//ユーザーID重複チェック
	@Override
	public boolean userIdOne(String userId) {
		if (mapper.userIdOne(userId) == null) {
			return true;
		} else {
			return false;
		}
	}
}
