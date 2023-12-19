package com.xs.sw.veh.module.system.api.errorcode;

import com.xs.sw.veh.module.system.api.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import com.xs.sw.veh.module.system.api.errorcode.dto.ErrorCodeRespDTO;
import com.xs.sw.veh.module.system.service.errorcode.ErrorCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 错误码 Api 实现类
 *
 * @author 芋道源码
 */
@Service
public class ErrorCodeApiImpl implements ErrorCodeApi {

    @Resource
    private ErrorCodeService errorCodeService;

    @Override
    public void autoGenerateErrorCodeList(List<ErrorCodeAutoGenerateReqDTO> autoGenerateDTOs) {
        errorCodeService.autoGenerateErrorCodes(autoGenerateDTOs);
    }

    @Override
    public List<ErrorCodeRespDTO> getErrorCodeList(String applicationName, LocalDateTime minUpdateTime) {
        return errorCodeService.getErrorCodeList(applicationName, minUpdateTime);
    }

}
