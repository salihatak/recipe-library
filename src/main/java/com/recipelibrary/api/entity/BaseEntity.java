package com.recipelibrary.api.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name = "date_created")
	private Instant dateCreated;

	@Column(name = "date_modified")
	private Instant dateModified;

	@PrePersist
	protected void prePersist() {
		Instant now = Instant.now();
		this.dateCreated = now;
		this.dateModified = now;
	}

	@PreUpdate
	protected void preUpdate() {
		Instant now = Instant.now();
		if (this.dateCreated == null) {
			this.dateCreated = now;
		}
		this.dateModified = now;
	}
}
