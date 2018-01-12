package com.rain.bbs.service;

import com.rain.bbs.domain.OperationLog;

public interface OperationLogService {

    public OperationLog save(OperationLog bean);

    public OperationLog get(Integer id);

    public OperationLog delete(OperationLog bean);

    public OperationLog loginSuccess(String ip,Integer userId);

    public OperationLog loginFail(String ip,Integer userId,String errorPwd);
}
