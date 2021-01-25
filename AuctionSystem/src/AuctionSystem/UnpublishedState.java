package AuctionSystem;

public class UnpublishedState implements AuctionState {
	public UnpublishedState() {}
	
	@Override
	public String toString() {
		return "Unpublished";
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
