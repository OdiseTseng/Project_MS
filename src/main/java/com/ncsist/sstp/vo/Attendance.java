package com.ncsist.sstp.vo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long courseId;

	@Column
	private Long unitId;

	@Column
	private Long contentId;

	@Column
	private Long quizId;

	@Column
	private String username;

	@Column
	private Date attendanceDate;

	@Column
	private Long team;

	@Column
	private Long role;

	@Column
	private Long recordScore;

	@Column
	private String recordShot;

	@Column
	private Long score;

	@Column
	private Long state;

	@Column
	private Long longDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private java.util.Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, updatable = true)
	@CreationTimestamp
	private java.util.Date updateTime;

}
