package AuctionSystem;

public class CreditCard {
	private int cardNum;
	private double balance;
	private static final double limit=10000000;
	
	public CreditCard(int aCardNum, double aBalance){
		cardNum=aCardNum;
		balance=aBalance;
	}
	public int decreaseBal(double amount) {
		double temp=balance-amount;
		if(temp<0)
			return 0;//Invalid transaction
		balance=temp;
		return 1;//Successful transaction
	}
	public int increaseBal(double amount) {
		double temp=balance+amount;
		if(temp>limit)
			return 0;//Invalid transaction
		balance=temp;
		return 1;//Successful transaction
	}
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		String temp="";
		temp+="Card number: "+cardNum+"    Balance: "+String.format("%.2f", balance);
		return temp;
	}
}
