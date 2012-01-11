package com.systex.sop.cvs.dto;

// Generated Jan 10, 2012 8:16:51 PM by Hibernate Tools 3.3.0.GA

import java.math.BigDecimal;

/**
 * TbsoptcvsverId generated by hbm2java
 */
public class TbsoptcvsverId implements java.io.Serializable {

	private Tbsoptcvsmap tbsoptcvsmap;
	private BigDecimal version;

	public TbsoptcvsverId() {
	}

	public TbsoptcvsverId(Tbsoptcvsmap tbsoptcvsmap, BigDecimal version) {
		this.tbsoptcvsmap = tbsoptcvsmap;
		this.version = version;
	}

	public Tbsoptcvsmap getTbsoptcvsmap() {
		return this.tbsoptcvsmap;
	}

	public void setTbsoptcvsmap(Tbsoptcvsmap tbsoptcvsmap) {
		this.tbsoptcvsmap = tbsoptcvsmap;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
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

		return ((this.getTbsoptcvsmap() == castOther.getTbsoptcvsmap()) || (this
				.getTbsoptcvsmap() != null
				&& castOther.getTbsoptcvsmap() != null && this
				.getTbsoptcvsmap().equals(castOther.getTbsoptcvsmap())))
				&& ((this.getVersion() == castOther.getVersion()) || (this
						.getVersion() != null
						&& castOther.getVersion() != null && this.getVersion()
						.equals(castOther.getVersion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTbsoptcvsmap() == null ? 0 : this.getTbsoptcvsmap()
						.hashCode());
		result = 37 * result
				+ (getVersion() == null ? 0 : this.getVersion().hashCode());
		return result;
	}

}