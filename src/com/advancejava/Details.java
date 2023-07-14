package com.advancejava;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Details {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int ID;
	@Column
	public String Title;
	@Column
	public String Quantity;
	@Column
	public String Size;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	public byte[] Image;
	
	public Details(String title, String quantity, String size, byte[] image) {
		super();
		Title = title;
		Quantity = quantity;
		Size = size;
		Image = image;
	}
	
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public byte[] getImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		Image = image;
	}
	
	
	
	
}
