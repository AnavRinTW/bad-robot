package com.systex.sop.cvs.dto;

// Generated Jan 11, 2012 3:55:55 PM by Hibernate Tools 3.3.0.GA

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbsoptcvsverId generated by hbm2java
 */
@Embeddable
public class TbsoptcvsverId implements java.io.Serializable {

	private long MSid;
	private String version;

	public TbsoptcvsverId() {
	}

	public TbsoptcvsverId(long MSid, String version) {
		this.MSid = MSid;
		this.version = version;
	}

	@Column(name = "M_SID", nullable = false, precision = 10, scale = 0)
	public long getMSid() {
		return this.MSid;
	}

	public void setMSid(long MSid) {
		this.MSid = MSid;
	}

	@Column(name = "VERSION", nullable = false, length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbsoptcvsverId))
			return false;
		TbsoptcvsverId castOther = (TbsoptcvsverId) other;

		return (this.getMSid() == castOther.getMSid())
				&& ((this.getVersion() == castOther.getVersion()) || (this
						.getVersion() != null
						&& castOther.getVersion() != null && this.getVersion()
						.equals(castOther.getVersion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getMSid();
		result = 37 * result
				+ (getVersion() == null ? 0 : this.getVersion().hashCode());
		return result;
	}

}