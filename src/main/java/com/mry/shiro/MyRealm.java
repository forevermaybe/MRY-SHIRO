package com.mry.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mry.entity.User;
import com.mry.service.UserService;

@Component
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken) arg0;
		String username = utoken.getUsername();
		User user = userService.findByName(username);
		if (user != null) {
			ByteSource salt = new SimpleByteSource("mry");
			String password = new Md5Hash(user.getPassword(),salt,1024).toString();
			return new SimpleAuthenticationInfo(user.getName(), password,salt, getName());
		}
		return null;
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher hashcreden = new HashedCredentialsMatcher();
		hashcreden.setHashAlgorithmName("MD5");
		hashcreden.setHashIterations(1024);
		super.setCredentialsMatcher(hashcreden);
	}

}
