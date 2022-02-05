package check;

public class checkField {
	
	public int checkFieldUser(String username) {
		if (username.length() >= 6) {
			for (int i = 0; i < username.length(); i++) {
				if (Character.isAlphabetic(username.charAt(i))){
					return 1;
				}
			}
		}
		return 0;
	}
	
	public int checkFieldPass(String password) {
		if (password.length() >= 6) {
			for (int i = 0; i < password.length();  i++) {
				if (Character.isAlphabetic(password.charAt(i))){
					return 1;
				}
			}
		}
		return 0;
	}
	
	public int checkConfirmPass(String password,String confirm) {
		if (password.compareTo(confirm) == 0) {
			return 1;
		}
		return 0;
	}
	
	public int checkPhone(String phone) {
		if (phone.length() >= 10 && phone.length() <= 11) {
			for (int i = 0; i < phone.length(); i++) {
				if (Character.isAlphabetic(phone.charAt(i))) {
					return 0;
				}
			}
			return 1;
		}
		return 0;
	}
	
	public int checkMoney(String money) {
		for (int i = 0; i < money.length(); i++) {
			if (Character.isAlphabetic(money.charAt(i))) {
				return 0;
			}
		}
		return 1;
	}
}
