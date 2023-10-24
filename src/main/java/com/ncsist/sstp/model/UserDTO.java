package com.ncsist.sstp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {


	private String username;

	private String password;

	private String ctxId;
}
