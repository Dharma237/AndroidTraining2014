package com.pcs.helper;

public class NavigationDrawerItem{

	private String title;

	private int icon;
	
	public NavigationDrawerItem()
	{
		
	}

	public NavigationDrawerItem(String title, int icon) {
		
		this.title = title;

		this.icon = icon;
	}


	public String getTitle() {

		return this.title;

	}

	public int getIcon() {

		return this.icon;

	}

	public void setTitle(String title) {

		this.title = title;

	}

}
