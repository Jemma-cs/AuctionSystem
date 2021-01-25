package AuctionSystem;

import java.util.ArrayList;

public class Item {
	private String name;
	private User owner;
	private int localID;
	//private static int latestID=0;
	private String desc;
	private AuctionState state;
	private ArrayList<Tag>  tagList;
	
	///1. Basic methods (AuctionState & Tag)
	public Item(String aName, String aDesc, int aID, User aOwner){
		localID=aID;
		name=aName;
		desc=aDesc;
		owner=aOwner;
		state=new UnpublishedState();
		tagList=new ArrayList<Tag>();
	}
	
	public int getID() {
		return localID;
	}
	public User getOwner() {
		return owner;
	}
	public String getName() {
		return name;
	}
	public AuctionState getState() {
		return state;
	}
	public String toString() {//return detailed information of this item (except state)
		String temp="";
		//Content
		temp+="ItemName: "+this.name+"\n";
		temp+="Owner:\n"+this.owner+"\n";
		temp+="Description: \n"+this.desc+"\n";
		String strTags=this.getStrTags();
		temp+=strTags+"\n";
		return temp;
	}
	
	///2. Manage tags
	public String getStrTags(){
		String strTags="Tags:";
		for(Tag t:tagList) {
			strTags+=" "+t.getName();
		}
		return strTags;
	}
	public boolean searchTagNew() {
		return tagList.contains(TagNew.getInstance());
	}
	public boolean searchTagVirtual() {
		return tagList.contains(TagVirtual.getInstance());
	}
	public boolean findTagByName(String tagName) {
		switch(tagName) {
		case "New":
			return this.searchTagNew();
		case "Virtual":
			return this.searchTagVirtual();
		default:
			return false;	
		}
	}
	public int addTag(String tagName) {
		switch(tagName) {
		case "New":
			if(this.searchTagNew())
				return 0;//Tag already exists
			tagList.add(TagNew.getInstance());
			return 1;//Success
		case "Virtual":
			if(this.searchTagVirtual())
				return 0;//Tag already exists
			tagList.add(TagVirtual.getInstance());
			return 1;//Success
		default:
			return -1;//TagName not found
		}
	}
	public int deleteTag(String tagName) {
		switch(tagName) {
		case "New":
			if(!this.searchTagNew())
				return 0;//Tag not included
			tagList.remove(TagNew.getInstance());
			return 1;//Success
		case "Virtual":
			if(!this.searchTagVirtual())
				return 0;//Tag not included
			tagList.remove(TagVirtual.getInstance());
			return 1;//Success
		default:
			return -1;//TagName not found
		}
	}
	
	
	///Change state
	public AuctionState setState(AuctionState newState) {
		state=newState;
		return state;
	}
}

