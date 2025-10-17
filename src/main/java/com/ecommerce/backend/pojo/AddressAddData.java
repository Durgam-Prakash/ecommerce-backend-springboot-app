package com.ecommerce.backend.pojo;

import com.ecommerce.backend.enums.AddressType;

import lombok.Data;

@Data
public class AddressAddData {
	
	private int userId;
	private String addressLine1; 
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	private String country;
	private Double longitude;
	private Double  latitude;
	private Boolean isDefault;
	private AddressType addressType;

}
