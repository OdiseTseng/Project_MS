package com.ncsist.sstp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NettyDTO implements Serializable {


	private String username;

	private String name;

	private Long level;

	private String ip;

	private Long studentId;

	private Long studentBatch;
}
