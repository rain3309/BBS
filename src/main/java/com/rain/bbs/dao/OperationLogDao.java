package com.rain.bbs.dao;

import com.rain.bbs.domain.OperationLog;
import org.springframework.data.repository.Repository;

public interface OperationLogDao extends Repository<OperationLog,Integer>{

    public OperationLog findOne(Integer id);

    public OperationLog save(OperationLog bean);

    public OperationLog delete(OperationLog bean);

}
