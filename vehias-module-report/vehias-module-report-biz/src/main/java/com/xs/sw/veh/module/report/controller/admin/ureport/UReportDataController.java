package com.xs.sw.veh.module.report.controller.admin.ureport;

import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.util.object.BeanUtils;
import com.xs.sw.veh.framework.excel.core.util.ExcelUtils;
import com.xs.sw.veh.framework.operatelog.core.annotations.OperateLog;
import com.xs.sw.veh.module.report.controller.admin.ureport.vo.UReportDataPageReqVO;
import com.xs.sw.veh.module.report.controller.admin.ureport.vo.UReportDataRespVO;
import com.xs.sw.veh.module.report.controller.admin.ureport.vo.UReportDataSaveReqVO;
import com.xs.sw.veh.module.report.dal.dataobject.ureport.UReportDataDO;
import com.xs.sw.veh.module.report.service.ureport.UReportDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;
import static com.xs.sw.veh.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - Ureport2 报表")
@RestController
@RequestMapping("/report/ureport-data")
@Validated
public class UReportDataController {

    @Resource
    private UReportDataService uReportDataService;

    @PostMapping("/create")
    @Operation(summary = "创建 Ureport2 报表")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:create')")
    public CommonResult<Long> createUReportData(@Valid @RequestBody UReportDataSaveReqVO createReqVO) {
        return success(uReportDataService.createUReportData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新 Ureport2 报表")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:update')")
    public CommonResult<Boolean> updateUReportData(@Valid @RequestBody UReportDataSaveReqVO updateReqVO) {
        uReportDataService.updateUReportData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除 Ureport2 报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('report:ureport-data:delete')")
    public CommonResult<Boolean> deleteUReportData(@RequestParam("id") Long id) {
        uReportDataService.deleteUReportData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Ureport2报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:query')")
    public CommonResult<UReportDataRespVO> getUReportData(@RequestParam("id") Long id) {
        UReportDataDO uReportData = uReportDataService.getUReportData(id);
        return success(BeanUtils.toBean(uReportData, UReportDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Ureport2报表分页")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:query')")
    public CommonResult<PageResult<UReportDataRespVO>> getUReportDataPage(@Valid UReportDataPageReqVO pageReqVO) {
        PageResult<UReportDataDO> pageResult = uReportDataService.getUReportDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UReportDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出 Ureport2 报表 Excel")
    @PreAuthorize("@ss.hasPermission('report:ureport-data:export')")
    @OperateLog(type = EXPORT)
    public void exportUReportDataExcel(@Valid UReportDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UReportDataDO> list = uReportDataService.getUReportDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "Ureport2 报表.xls", "数据", UReportDataRespVO.class,
                        BeanUtils.toBean(list, UReportDataRespVO.class));
    }

}
