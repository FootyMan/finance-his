package com.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erp.utils.Query;
import com.erp.domain.LogDO;
import com.erp.domain.PageDO;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
