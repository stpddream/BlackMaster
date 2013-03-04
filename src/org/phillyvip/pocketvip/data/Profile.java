package org.phillyvip.pocketvip.data;

import org.phillyvip.pocketvip.R;

import android.view.View;
import android.widget.Button;

public class Profile {
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String barNumber;
	private String autoMessage;
	
	//constructor 
	public Profile(String firstName, String lastName,String email,String telephone,String barNumber,String autoMessage){
		this. firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.barNumber = barNumber;
		this.autoMessage = autoMessage;
	}

	//Preference preference
	public boolean setFirstName(String firstName) {
		this.firstName = firstName;
		return true;
	}

	public boolean setLastName(String lastName) {
		this.lastName = lastName;
		return true;
	}

	public boolean setEmail(String email) {
		this.email = email;
		return true;
	}

	public boolean setTelephone(String telephone) {
		this.telephone = telephone;
		return true;
	}

	public boolean setBarNumber(String barNumber) {
		this.barNumber = barNumber;
		return true;
	}

	public boolean setAutoMessage(String autoMessage){
		this.autoMessage = autoMessage;
		return true;
	}

	public String toString(){
		String format = "|%1$-10s|%2$-10s|\n";
		String a = String.format(format,"First name: ",firstName);
		String b = String.format(format, "Last name: ",lastName);
		String c = String.format(format, "Email : ",email);
		String d = String.format(format, "Telephone : ",telephone);
		String e = String.format(format, "Bar number : ",barNumber);
		String f = String.format(format, "Auto Message : ",autoMessage);
		String profile = a+b+c+d+e+f;
		return profile;
	}

	
}
