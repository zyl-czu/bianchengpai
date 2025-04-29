package com.github.zylczu.service.article.service;


import com.github.zylczu.api.model.vo.PageListVo;
import com.github.zylczu.api.model.vo.PageParam;
import com.github.zylczu.api.model.vo.article.dto.ArticleDTO;

/**
 * @author YiHui
 * @date 2022/9/26
 */
public interface ArticleRecommendService {
    /**
     * 文章关联推荐
     *
     * @param article
     * @param pageParam
     * @return
     */
    PageListVo<ArticleDTO> relatedRecommend(Long article, PageParam pageParam);
}
