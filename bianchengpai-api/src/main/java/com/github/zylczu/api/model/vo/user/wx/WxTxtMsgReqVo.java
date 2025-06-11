package com.github.zylczu.api.model.vo.user.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 简单文本请求
 *
 * @author yihui
 * @link <a href="https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html"/>
 * @date 2022/6/20
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WxTxtMsgReqVo {
    @JacksonXmlProperty(localName = "ToUserName")  // 开发者微信号
    private String toUserName;
    @JacksonXmlProperty(localName = "FromUserName")  // 发送方账号（一个OpenID）
    private String fromUserName;
    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;
    @JacksonXmlProperty(localName = "MsgType")  // 消息类型，文本为text event
    private String msgType;
    @JacksonXmlProperty(localName = "Event")  // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    private String event;
    @JacksonXmlProperty(localName = "EventKey")  // 事件KEY值，qrscene_为前缀，后面为二维码的场景值ID
    private String eventKey;
    @JacksonXmlProperty(localName = "Ticket")  // 二维码的ticket，可用来换取二维码图片
    private String ticket;
    @JacksonXmlProperty(localName = "Content")  // 文本消息内容
    private String content;
    @JacksonXmlProperty(localName = "MsgId")  // 消息id，64位整型
    private String msgId;
    @JacksonXmlProperty(localName = "MsgDataId")  // 消息的数据ID（消息如果来自文章时才有）
    private String msgDataId;
    @JacksonXmlProperty(localName = "Idx")  // 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
    private String idx;
}
