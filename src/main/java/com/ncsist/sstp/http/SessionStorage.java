package com.ncsist.sstp.http;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSession;

public class SessionStorage {

    @Getter
    @Setter
    static String sessionId;
}
