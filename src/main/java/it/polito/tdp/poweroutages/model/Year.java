package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class Year {
	Year year;
	List<PowerOutages> dissevizi = new ArrayList<PowerOutages>();
	
	public Year(Year year, List<PowerOutages> dissevizi) {
		this.year = year;
		this.dissevizi = dissevizi;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public List<PowerOutages> getDissevizi() {
		return dissevizi;
	}

	public void setDissevizi(List<PowerOutages> dissevizi) {
		this.dissevizi = dissevizi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Year other = (Year) obj;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Year [year=" + year + "]";
	}
	
	
	
}
