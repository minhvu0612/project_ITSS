package check;

public class getPayment {
	
	public double getPaymentEcoBikes(long times, double preprice, double price) {
		if (times/60000 > 10) {
			if (times/60000 >= 30) {
				return preprice + (times/60000 - 30)*price/15;
			}
			else {
				return preprice * (times/60000)/30;
			}
		}
		else {
			return price * (times/60000)/15;
		}
	}
}
