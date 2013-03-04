package org.phillyvip.pocketvip.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Case implements Parcelable {
		
		private String category;
		private String topic;
		private String caseNumber;
		private String description;
		private boolean urgentFlag;
		private boolean visitFlag;
		private boolean appliedFlag;
		
		
		/**
		 * Constructor
		 * @param category
		 * @param topic
		 * @param caseNumber
		 * @param description
		 * @param urgentFlag
		 */
		public Case(String category,  String topic, String caseNumber,
				String description, boolean urgentFlag) {
			this.category = category;
			this.topic = topic;
			this.caseNumber = caseNumber;
			this.description = description;
			this.urgentFlag = urgentFlag;
			
			this.appliedFlag = false;
			this.visitFlag = false;
		}
		
		public Case(String topic) {
			this.topic = topic;
		}
		
		//Getters
		public String getCategory() { return this.category; }
		public String getTopic() { return this.topic; }
		public String getCaseNumber() { return this.caseNumber; }
		public String getDescription() { return this.description; }
		public boolean isUrgent() { return this.urgentFlag; }
		//private String coreComp;

		public String toString() {
			
			return 
			"Case Number: " + this.caseNumber + "\n" +
			"Category: " + this.category + "\n" + 
			"Topic: " + this.topic + "\n" + 
			"Description: " + this.description + "\n" + 
			"Urgent Flag: " + this.urgentFlag; 
			
		}
		
		/* Parcelable Methods */
		public int describeContents() {
			return 0;
		}

		public void writeToParcel(Parcel out, int flags) {
			out.writeString(this.category);
			out.writeString(this.topic);
			out.writeString(this.caseNumber);
			out.writeString(this.description);
			out.writeByte((byte) ((this.urgentFlag) ? 1 : 0));
		}

		public static final Parcelable.Creator<Case> CREATOR
		= new Parcelable.Creator<Case>() {
			public Case createFromParcel(Parcel in) {
				String tCategory = in.readString();
				String tTopic = in.readString();
				String tCaseNumber = in.readString();
				String tDescription = in.readString();
				boolean tUrgent = (in.readByte() == 1) ? true : false;
				return new Case(tCategory, tTopic, tCaseNumber, 
						                          tDescription, tUrgent);
			}

			public Case[] newArray(int size) {
				return new Case[size];
			}
		};

}
