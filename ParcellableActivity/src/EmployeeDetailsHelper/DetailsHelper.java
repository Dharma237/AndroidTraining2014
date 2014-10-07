package EmployeeDetailsHelper;

import android.os.Parcel;
import android.os.Parcelable;

/***
 *	Setters getters method
 *	Whole code for parcelable
 * @author pcs02
 *
 */
public class DetailsHelper implements Parcelable{
	
	private String EmployeeId;
	private String EmployeeName;
	private String EmployeeDesignation;

	
	public DetailsHelper(Parcel source) {
		setEmployeeId(source.readString());	
		setEmployeeName(source.readString());
		setEmployeeDesignation(source.readString());
	}
	

	public DetailsHelper() {
		// TODO Auto-generated constructor stub
	}


	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeDesignation() {
		return EmployeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		EmployeeDesignation = employeeDesignation;
	}

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(getEmployeeId());
		dest.writeString(getEmployeeName());
		dest.writeString(getEmployeeDesignation());
	}
	
	public static final Creator<DetailsHelper> CREATOR = new Creator<DetailsHelper>() {
		@Override
		public DetailsHelper createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new DetailsHelper(source);
		}

		@Override
		public DetailsHelper[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DetailsHelper[size];
		}
	};
	public  String toString()
	{
		return "EmployeeId:"+getEmployeeId() +"\n"+
				"EmployeeName:" +getEmployeeName() + "\n"+
				"EmployeeDesignation:"+getEmployeeDesignation();
	}

}
