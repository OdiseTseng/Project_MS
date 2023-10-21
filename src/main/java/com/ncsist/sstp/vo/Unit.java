package com.ncsist.sstp.vo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "unit")
public class Unit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long courseId;

	private Long unitId;

	private String unitName;

	private String unitSchedule;

	private String unitSubject;

	private Long unitOrder;

	private String descTitle1;

	private String descContent1;

	private String descTitle2;

	private String descContent2;

	private String descTitle3;

	private String descContent3;

	private String videoUrl;

	private String videoFormat;

	private String videoContents;

	private String dfcsFilename;

	private Long creditUnits;

	private String pictureUrl1;

	private String pictureUrl2;

	private String pictureUrl3;

	private String pictureUrl4;

	private String contentList;

	private Long state;

	private Long longDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, updatable = true)
	@CreationTimestamp
	private Date updateTime;

}
