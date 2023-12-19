package com.xs.sw.veh.module.promotion.controller.app.article;

import com.xs.sw.veh.framework.common.enums.CommonStatusEnum;
import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.module.promotion.controller.app.article.vo.category.AppArticleCategoryRespVO;
import com.xs.sw.veh.module.promotion.convert.article.ArticleCategoryConvert;
import com.xs.sw.veh.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import com.xs.sw.veh.module.promotion.service.article.ArticleCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 文章分类")
@RestController
@RequestMapping("/promotion/article-category")
@Validated
public class AppArticleCategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;

    @RequestMapping("/list")
    @Operation(summary = "获得文章分类列表")
    public CommonResult<List<AppArticleCategoryRespVO>> getArticleCategoryList() {
        List<ArticleCategoryDO> categoryList = articleCategoryService.getArticleCategoryListByStatus(
                CommonStatusEnum.ENABLE.getStatus());
        categoryList.sort(Comparator.comparing(ArticleCategoryDO::getSort)); // 按 sort 降序排列
        return success(ArticleCategoryConvert.INSTANCE.convertList04(categoryList));
    }

}
