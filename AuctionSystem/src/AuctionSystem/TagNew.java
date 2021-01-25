package AuctionSystem;

public class TagNew implements Tag{
	private String name;
	private String desc;
	private static TagNew instance =new TagNew();
	public static TagNew getInstance() {return instance;}
	private TagNew() {
		name="New";
		desc="";
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getDesc() {
		return desc;
	}
}
