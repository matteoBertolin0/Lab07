package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutages {

	private int id, customerAffected;
	private String nerc;
	private LocalDateTime dateEventBegan, dateEventFinished;
	
	public PowerOutages(int id, String nerc, int customerAffected, LocalDateTime dateEventBegan,
			LocalDateTime dateEventFinished) {
		this.id = id;
		this.nerc = nerc;
		this.customerAffected = customerAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNerc() {
		return nerc;
	}

	public void setNercId(String nerc) {
		this.nerc = nerc;
	}

	public int getCustomerAffected() {
		return customerAffected;
	}

	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", nerc=" + nerc + ", customerAffected=" + customerAffected
				+ ", dateEventBegan=" + dateEventBegan + ", dateEventFinished=" + dateEventFinished + "]";
	}
	
	
}
