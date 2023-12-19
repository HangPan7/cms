package com.xs.sw.veh.module.promotion.controller.admin.diy;

import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.promotion.controller.admin.diy.vo.template.*;
import com.xs.sw.veh.module.promotion.convert.diy.DiyTemplateConvert;
import com.xs.sw.veh.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.xs.sw.veh.module.promotion.dal.dataobject.diy.DiyTemplateDO;
import com.xs.sw.veh.module.promotion.service.diy.DiyPageService;
import com.xs.sw.veh.module.promotion.service.diy.DiyTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 装修模板")
@RestController
@RequestMapping("/promotion/diy-template")
@Validated
public class DiyTemplateController {

    @Resource
    private DiyTemplateService diyTemplateService;
    @Resource
    private DiyPageService diyPageService;

    @PostMapping("/create")
    @Operation(summary = "创建装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:create')")
    public CommonResult<Long> createDiyTemplate(@Valid @RequestBody DiyTemplateCreateReqVO createReqVO) {
        return success(diyTemplateService.createDiyTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplate(@Valid @RequestBody DiyTemplateUpdateReqVO updateReqVO) {
        diyTemplateService.updateDiyTemplate(updateReqVO);
        return success(true);
    }

    @PutMapping("/use")
    @Operation(summary = "使用装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:use')")
    public CommonResult<Boolean> useDiyTemplate(@RequestParam("id") Long id) {
        diyTemplateService.useDiyTemplate(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除装修模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:delete')")
    public CommonResult<Boolean> deleteDiyTemplate(@RequestParam("id") Long id) {
        diyTemplateService.deleteDiyTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得装修模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplateRespVO> getDiyTemplate(@RequestParam("id") Long id) {
        DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
        return success(DiyTemplateConvert.INSTANCE.convert(diyTemplate));
    }

    @GetMapping("/page")
    @Operation(summary = "获得装修模板分页")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<PageResult<DiyTemplateRespVO>> getDiyTemplatePage(@Valid DiyTemplatePageReqVO pageVO) {
        PageResult<DiyTemplateDO> pageResult = diyTemplateService.getDiyTemplatePage(pageVO);
        return success(DiyTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/get-property")
    @Operation(summary = "获得装修模板属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplatePropertyRespVO> getDiyTemplateProperty(@RequestParam("id") Long id) {
        DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
        List<DiyPageDO> pages = diyPageService.getDiyPageByTemplateId(id);
        return success(DiyTemplateConvert.INSTANCE.convertPropertyVo(diyTemplate, pages));
    }

    @PutMapping("/update-property")
    @Operation(summary = "更新装修模板属性")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplateProperty(@Valid @RequestBody DiyTemplatePropertyUpdateRequestVO updateReqVO) {
        diyTemplateService.updateDiyTemplateProperty(updateReqVO);
        return success(true);
    }

}
