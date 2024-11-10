package com.service;


public class MailService {

	public  int getOtp() {
		int otp = (int) (Math.random() * 10000);
		String s = String.valueOf(otp);
		while (s.length() < 4) {
			otp = (int) (Math.random() * 10000);
		}
		return otp;
	}

	public String getUsername(String name, long mob) {
		// TODO Auto-generated method stub

		String u = name.substring(0, name.length() / 2 + 1);
		String mo = String.valueOf(mob).substring(6);
		String username = u + mo;
		return username;
	}

}
