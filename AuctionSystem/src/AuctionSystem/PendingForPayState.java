package AuctionSystem;

public class PendingForPayState implements AuctionState {
	public PendingForPayState() {}
	
	@Override
	public String toString() {
		return "Pending for payment";
	}
	@Override
	public boolean isOpenForBidding() {
		return false;
	}
	@Override
	public boolean isOpenForPayment() {
		return true;
	}
}
