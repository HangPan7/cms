package com.xs.sw.veh.module.ias.controller.admin.stationinfo;

import cn.hutool.json.JSONObject;
import com.xs.sw.veh.framework.excel.core.util.ExcelUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.framework.common.util.object.BeanUtils;
import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;



import com.xs.sw.veh.framework.operatelog.core.annotations.OperateLog;
import static com.xs.sw.veh.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo.*;
import com.xs.sw.veh.module.ias.dal.dataobject.stationinfo.StationInfoDO;
import com.xs.sw.veh.module.ias.service.stationinfo.StationInfoService;

@Tag(name = "管理后台 - 机构")
@RestController
@RequestMapping("/api/sysInspectionInstitution")
@Validated
public class StationInfoController {

    @Resource
    private StationInfoService stationInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建机构")
    @PreAuthorize("@ss.hasPermission('ias:station-info:create')")
    public CommonResult<Long> createStationInfo(@Valid @RequestBody StationInfoSaveReqVO createReqVO) {
        return success(stationInfoService.createStationInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新机构")
    @PreAuthorize("@ss.hasPermission('ias:station-info:update')")
    public CommonResult<Boolean> updateStationInfo(@Valid @RequestBody StationInfoSaveReqVO updateReqVO) {
        stationInfoService.updateStationInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除机构")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('ias:station-info:delete')")
    public CommonResult<Boolean> deleteStationInfo(@RequestParam("id") Long id) {
        stationInfoService.deleteStationInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得机构")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('ias:station-info:query')")
    public CommonResult<StationInfoRespVO> getStationInfo(@RequestParam("id") Long id) {
        StationInfoDO stationInfo = stationInfoService.getStationInfo(id);
        return success(BeanUtils.toBean(stationInfo, StationInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得机构分页")
    @PreAuthorize("@ss.hasPermission('ias:station-info:query')")
    public CommonResult<PageResult<StationInfoRespVO>> getStationInfoPage(@Valid StationInfoPageReqVO pageReqVO) {
        PageResult<StationInfoDO> pageResult = stationInfoService.getStationInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StationInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出机构 Excel")
    @PreAuthorize("@ss.hasPermission('ias:station-info:export')")
    @OperateLog(type = EXPORT)
    public void exportStationInfoExcel(@Valid StationInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StationInfoDO> list = stationInfoService.getStationInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "机构.xls", "数据", StationInfoRespVO.class,
                        BeanUtils.toBean(list, StationInfoRespVO.class));
    }

}