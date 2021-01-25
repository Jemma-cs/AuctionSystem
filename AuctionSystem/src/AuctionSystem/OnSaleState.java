package AuctionSystem;

public class OnSaleState implements AuctionState {
	public OnSaleState() {}
	@Override
	public String toString() {
		return "On Sale";
	}
	@Override
	public boolean isOpenForBidding() {
		return true;
	}
	@Override
	public boolean isOpenForPayment() {
		return false;
	}
}
