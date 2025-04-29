package com.github.zylczu.service.article.service.impl;


import com.github.zylczu.api.model.vo.PageListVo;
import com.github.zylczu.api.model.vo.PageParam;
import com.github.zylczu.api.model.vo.article.dto.ArticleDTO;
import com.github.zylczu.service.article.repository.dao.ArticleDao;
import com.github.zylczu.service.article.repository.dao.ArticleTagDao;
import com.github.zylczu.service.article.repository.entity.ArticleDO;
import com.github.zylczu.service.article.service.ArticleReadService;
import com.github.zylczu.service.article.service.ArticleRecommendService;
import com.github.zylczu.service.sidebar.service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.github.zylczu.service.article.repository.entity.ArticleTagDO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YiHui
 * @date 2022/9/26
 */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTagDao articleTagDao;
    @Autowired
    private ArticleReadService articleReadService;
    @Autowired
    private SidebarService sidebarService;

    /**
     * 查询文章关联推荐列表
     *
     * @param articleId
     * @param page
     * @return
     */
    @Override
    public PageListVo<ArticleDTO> relatedRecommend(Long articleId, PageParam page) {
        ArticleDO article = articleDao.getById(articleId);
        if (article == null) {
            return PageListVo.emptyVo();
        }
        List<Long> tagIds = articleTagDao.listArticleTags(articleId).stream()
                .map(ArticleTagDO::getTagId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tagIds)) {
            return PageListVo.emptyVo();
        }

        List<ArticleDO> recommendArticles = articleDao.listRelatedArticlesOrderByReadCount(article.getCategoryId(), tagIds, page);
        if (recommendArticles.removeIf(s -> s.getId().equals(articleId))) {
            // 移除推荐列表中的当前文章
            page.setPageSize(page.getPageSize() - 1);
        }
        return articleReadService.buildArticleListVo(recommendArticles, page.getPageSize());
    }
}
