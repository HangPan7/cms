package com.xs.sw.veh.module.crm.controller.admin.product;

import cn.hutool.core.collection.CollUtil;
import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.util.collection.SetUtils;
import com.xs.sw.veh.framework.excel.core.util.ExcelUtils;
import com.xs.sw.veh.framework.operatelog.core.annotations.OperateLog;
import com.xs.sw.veh.module.crm.controller.admin.product.vo.product.CrmProductPageReqVO;
import com.xs.sw.veh.module.crm.controller.admin.product.vo.product.CrmProductRespVO;
import com.xs.sw.veh.module.crm.controller.admin.product.vo.product.CrmProductSaveReqVO;
import com.xs.sw.veh.module.crm.convert.product.CrmProductConvert;
import com.xs.sw.veh.module.crm.dal.dataobject.product.CrmProductCategoryDO;
import com.xs.sw.veh.module.crm.dal.dataobject.product.CrmProductDO;
import com.xs.sw.veh.module.crm.service.product.CrmProductCategoryService;
import com.xs.sw.veh.module.crm.service.product.CrmProductService;
import com.xs.sw.veh.module.system.api.user.AdminUserApi;
import com.xs.sw.veh.module.system.api.user.dto.AdminUserRespDTO;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;
import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertSet;
import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertSetByFlatMap;
import static com.xs.sw.veh.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - CRM 产品")
@RestController
@RequestMapping("/crm/product")
@Validated
public class CrmProductController {

    @Resource
    private CrmProductService productService;
    @Resource
    private CrmProductCategoryService productCategoryService;

    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建产品")
    @PreAuthorize("@ss.hasPermission('crm:product:create')")
    public CommonResult<Long> createProduct(@Valid @RequestBody CrmProductSaveReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品")
    @PreAuthorize("@ss.hasPermission('crm:product:update')")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody CrmProductSaveReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('crm:product:delete')")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('crm:product:query')")
    public CommonResult<CrmProductRespVO> getProduct(@RequestParam("id") Long id) {
        CrmProductDO product = productService.getProduct(id);
        if (product == null) {
            return success(null);
        }
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(
                SetUtils.asSet( Long.valueOf(product.getCreator()), product.getOwnerUserId()));
        CrmProductCategoryDO category = productCategoryService.getProductCategory(product.getCategoryId());
        return success(CrmProductConvert.INSTANCE.convert(product, userMap, category));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品分页")
    @PreAuthorize("@ss.hasPermission('crm:product:query')")
    public CommonResult<PageResult<CrmProductRespVO>> getProductPage(@Valid CrmProductPageReqVO pageVO) {
        PageResult<CrmProductDO> pageResult = productService.getProductPage(pageVO);
        return success(new PageResult<>(getProductDetailList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品 Excel")
    @PreAuthorize("@ss.hasPermission('crm:product:export')")
    @OperateLog(type = EXPORT)
    public void exportProductExcel(@Valid CrmProductPageReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CrmProductDO> list = productService.getProductPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "产品.xls", "数据", CrmProductRespVO.class,
                getProductDetailList(list));
    }

    private List<CrmProductRespVO> getProductDetailList(List<CrmProductDO> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(
                convertSetByFlatMap(list, user -> Stream.of(Long.valueOf(user.getCreator()), user.getOwnerUserId())));
        List<CrmProductCategoryDO> productCategoryList = productCategoryService.getProductCategoryList(
                convertSet(list, CrmProductDO::getCategoryId));
        return CrmProductConvert.INSTANCE.convertList(list, userMap, productCategoryList);
    }

}
