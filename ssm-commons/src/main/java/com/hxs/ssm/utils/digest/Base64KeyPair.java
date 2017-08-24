package com.hxs.ssm.utils.digest;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Base64KeyPair {
	
	private KeyPair keyPair;

	public Base64KeyPair(KeyPair keyPair){
		this.keyPair = keyPair;
	}
	
	public String getPublic() {
		PublicKey key =  keyPair.getPublic();
		return Base64.encode(key.getEncoded());
	}

	public String getPrivate() {
		PrivateKey key =  keyPair.getPrivate();
		return Base64.encode(key.getEncoded());
	} 
}
