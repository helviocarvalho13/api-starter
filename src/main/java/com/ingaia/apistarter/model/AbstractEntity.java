package com.ingaia.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	private static final long serialVersionUID = -7160203250275297751L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CODE")
	private Long code;
	
	@Version
    @Column(name = "VERSION", nullable = false)
    protected int version;
	
	public void setCode(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AbstractEntity other = (AbstractEntity) obj;
		if (code != other.code && (code == null || !code.equals(other.code))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (code != null ? code.hashCode() : 0);
		return hash;
	}
}
