package com.rain.bbs.service.impl;

import com.rain.bbs.common.Constant;
import com.rain.bbs.dao.OperationLogDao;
import com.rain.bbs.domain.OperationLog;
import com.rain.bbs.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class OperationServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogDao dao;

    @Transactional
    public OperationLog save(OperationLog bean) {
        return dao.save(bean);
    }

    public OperationLog get(Integer id) {
        return dao.findOne(id);
    }

    @Transactional
    public OperationLog delete(OperationLog bean) {
        return dao.delete(bean);
    }

    @Transactional
    public OperationLog loginSuccess(String ip,Integer userId){
        OperationLog log = new OperationLog();
        log.setName("login.success");
        log.setIp(ip);
        log.setUserId(userId);
        log.setTime(new Date());
        log.setType(Constant.TYPE_LOGIN);

        return dao.save(log);
    }

    @Transactional
    public OperationLog loginFail(String ip,Integer userId,String errorPwd){
        OperationLog log = new OperationLog();
        log.setName("login.fail");
        log.setIp(ip);
        log.setUserId(userId);
        log.setType(Constant.TYPE_LOGIN_FAIL);
        log.setTime(new Date());
        log.setDescription("登陆失败，错误密码为： "+ errorPwd);

        return dao.save(log);
    }
}
