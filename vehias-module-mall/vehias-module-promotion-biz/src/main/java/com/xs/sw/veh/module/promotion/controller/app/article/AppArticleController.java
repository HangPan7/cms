package com.xs.sw.veh.module.promotion.controller.app.article;

import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.promotion.controller.app.article.vo.article.AppArticlePageReqVO;
import com.xs.sw.veh.module.promotion.controller.app.article.vo.article.AppArticleRespVO;
import com.xs.sw.veh.module.promotion.convert.article.ArticleConvert;
import com.xs.sw.veh.module.promotion.service.article.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 文章")
@RestController
@RequestMapping("/promotion/article")
@Validated
public class AppArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("/list")
    @Operation(summary = "获得文章详情列表")
    @Parameters({
            @Parameter(name = "recommendHot", description = "是否热门", example = "false"), // 场景一：查看指定的文章
            @Parameter(name = "recommendBanner", description = "是否轮播图", example = "false") // 场景二：查看指定的文章
    })
    public CommonResult<List<AppArticleRespVO>> getArticleList(
            @RequestParam(value = "recommendHot", required = false) Boolean recommendHot,
            @RequestParam(value = "recommendBanner", required = false) Boolean recommendBanner) {
        return success(ArticleConvert.INSTANCE.convertList03(
                articleService.getArticleCategoryListByRecommend(recommendHot, recommendBanner)));
    }

    @RequestMapping("/page")
    @Operation(summary = "获得文章详情分页")
    public CommonResult<PageResult<AppArticleRespVO>> getArticlePage(AppArticlePageReqVO pageReqVO) {
        return success(ArticleConvert.INSTANCE.convertPage02(articleService.getArticlePage(pageReqVO)));
    }

    @RequestMapping("/get")
    @Operation(summary = "获得文章详情")
    @Parameter(name = "id", description = "文章编号", example = "1024")
    public CommonResult<AppArticleRespVO> getArticlePage(@RequestParam("id") Long id) {
        return success(ArticleConvert.INSTANCE.convert01(articleService.getArticle(id)));
    }

    @PutMapping("/add-browse-count")
    @Operation(summary = "增加文章浏览量")
    @Parameter(name = "id", description = "文章编号", example = "1024")
    public CommonResult<Boolean> addBrowseCount(@RequestParam("id") Long id) {
        articleService.addArticleBrowseCount(id);
        return success(true);
    }

}
