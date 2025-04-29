package com.github.zylczu.web.front.article.vo;

import com.github.zylczu.api.model.vo.PageListVo;
import com.github.zylczu.api.model.vo.article.dto.ColumnDTO;
import com.github.zylczu.api.model.vo.recommend.SideBarDTO;
import lombok.Data;

import java.util.List;

/**
 * @author YiHui
 * @date 2022/9/26
 */
@Data
public class ColumnVo {
    /**
     * 专栏列表
     */
    private PageListVo<ColumnDTO> columns;

    /**
     * 侧边栏信息
     */
    private List<SideBarDTO> sideBarItems;

}
