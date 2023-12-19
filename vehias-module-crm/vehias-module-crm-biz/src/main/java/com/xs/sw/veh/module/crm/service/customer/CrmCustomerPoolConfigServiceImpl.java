package com.xs.sw.veh.module.crm.service.customer;

import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.poolconfig.CrmCustomerPoolConfigSaveReqVO;
import com.xs.sw.veh.module.crm.convert.customer.CrmCustomerConvert;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerPoolConfigDO;
import com.xs.sw.veh.module.crm.dal.mysql.customer.CrmCustomerPoolConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 客户公海配置 Service 实现类
 *
 * @author Wanwan
 */
@Service
@Validated
public class CrmCustomerPoolConfigServiceImpl implements CrmCustomerPoolConfigService {
    @Resource
    private CrmCustomerPoolConfigMapper customerPoolConfigMapper;

    /**
     * 获得客户公海配置
     *
     * @return 客户公海配置
     */
    @Override
    public CrmCustomerPoolConfigDO getCustomerPoolConfig() {
        return customerPoolConfigMapper.selectOne(new LambdaQueryWrapperX<CrmCustomerPoolConfigDO>().last("LIMIT 1"));
    }

    /**
     * 保存客户公海配置
     *
     * @param saveReqVO 更新信息
     */
    @Override
    public void saveCustomerPoolConfig(CrmCustomerPoolConfigSaveReqVO saveReqVO) {
        // 存在，则进行更新
        CrmCustomerPoolConfigDO dbConfig = getCustomerPoolConfig();
        if (Objects.nonNull(dbConfig)) {
            customerPoolConfigMapper.updateById(CrmCustomerConvert.INSTANCE.convert(saveReqVO).setId(dbConfig.getId()));
            return;
        }
        // 不存在，则进行插入
        customerPoolConfigMapper.insert(CrmCustomerConvert.INSTANCE.convert(saveReqVO));
    }

}
