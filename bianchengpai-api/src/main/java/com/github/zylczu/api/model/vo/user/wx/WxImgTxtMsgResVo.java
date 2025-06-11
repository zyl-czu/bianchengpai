package com.github.zylczu.api.model.vo.user.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 返回的数据结构体
 * <p>
 *
 * @author yihui
 * @link <a href="https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Passive_user_reply_message.html"/>
 * @date 2022/6/20
 */
@Data
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class WxImgTxtMsgResVo extends BaseWxMsgResVo {
    @JacksonXmlProperty(localName = "ArticleCount")
    private Integer articleCount;  // 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "item")
    private List<WxImgTxtItemVo> articles;  // 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数

    public WxImgTxtMsgResVo() {
        setMsgType("news");
    }
}
