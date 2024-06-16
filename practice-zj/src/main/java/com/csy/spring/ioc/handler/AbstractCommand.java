package com.csy.spring.ioc.handler;

import lombok.Getter;
import lombok.Setter;

/**
 * @author orange
 * @date 2024/5/31
 */

@Getter
@Setter
public class AbstractCommand {

    private String cid;

    private long curTime;
}
