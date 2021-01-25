package AuctionSystem;

public class ClosedState implements AuctionState {
	public ClosedState() {}
	@Override
	public String toString() {
		return "Closed";
	}
	@Override
	public boolean isOpenForBidding() {
		return false;
	}
	@Override
	public boolean isOpenForPayment() {
		return false;
	}
}
