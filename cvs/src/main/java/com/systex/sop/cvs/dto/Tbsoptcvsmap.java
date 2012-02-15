package com.systex.sop.cvs.dto;

// Generated Jan 11, 2012 3:55:55 PM by Hibernate Tools 3.3.0.GA

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tbsoptcvsmap generated by hbm2java
 */
@Entity
@Table(name = "TBSOPTCVSMAP", uniqueConstraints = @UniqueConstraint(columnNames = "RCSFILE"))
public class Tbsoptcvsmap implements java.io.Serializable {

	private Long MSid;
	private String rcsfile;
	private String filename;
	private String programid;
	private String module;
	private Character clientserver;
	private String versionhead;
	private Set<Tbsoptcvsver> tbsoptcvsvers = new HashSet<Tbsoptcvsver>(0);

	public Tbsoptcvsmap() {
	}

	public Tbsoptcvsmap(Long MSid, String rcsfile, String filename,
			String versionhead) {
		this.MSid = MSid;
		this.rcsfile = rcsfile;
		this.filename = filename;
		this.versionhead = versionhead;
	}

	@Id
	@Column(name = "M_SID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMSid() {
		return this.MSid;
	}

	public void setMSid(Long MSid) {
		this.MSid = MSid;
	}

	@Column(name = "RCSFILE", unique = true, nullable = false, length = 500)
	public String getRcsfile() {
		return this.rcsfile;
	}

	public void setRcsfile(String rcsfile) {
		this.rcsfile = rcsfile;
	}

	@Column(name = "FILENAME", nullable = false, length = 100)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "PROGRAMID", length = 50)
	public String getProgramid() {
		return this.programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	@Column(name = "MODULE", length = 10)
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Column(name = "CLIENTSERVER", length = 1)
	public Character getClientserver() {
		return this.clientserver;
	}

	public void setClientserver(Character clientserver) {
		this.clientserver = clientserver;
	}

	@Column(name = "VERSIONHEAD", nullable = false, length = 20)
	public String getVersionhead() {
		return this.versionhead;
	}

	public void setVersionhead(String versionhead) {
		this.versionhead = versionhead;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbsoptcvsmap")
	public Set<Tbsoptcvsver> getTbsoptcvsvers() {
		return this.tbsoptcvsvers;
	}

	public void setTbsoptcvsvers(Set<Tbsoptcvsver> tbsoptcvsvers) {
		this.tbsoptcvsvers = tbsoptcvsvers;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
