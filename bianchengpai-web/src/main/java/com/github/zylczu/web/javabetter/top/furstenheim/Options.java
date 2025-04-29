package com.github.zylczu.web.javabetter.top.furstenheim;

public class Options {
    final String br;
    final String hr;
    final String emDelimiter;
    final String strongDelimiter;
    final com.github.zylczu.web.javabetter.top.furstenheim.HeadingStyle headingStyle;
    final String bulletListMaker;
    final com.github.zylczu.web.javabetter.top.furstenheim.CodeBlockStyle codeBlockStyle;
    final com.github.zylczu.web.javabetter.top.furstenheim.LinkStyle linkStyle;
    final com.github.zylczu.web.javabetter.top.furstenheim.LinkReferenceStyle linkReferenceStyle;
    final String fence;

    public Options(String br, String hr, String emDelimiter, String strongDelimiter,
                   com.github.zylczu.web.javabetter.top.furstenheim.HeadingStyle headingStyle, String bulletListMaker, com.github.zylczu.web.javabetter.top.furstenheim.CodeBlockStyle codeBlockStyle,
                   com.github.zylczu.web.javabetter.top.furstenheim.LinkStyle linkStyle, com.github.zylczu.web.javabetter.top.furstenheim.LinkReferenceStyle linkReferenceStyle, String fence) {
        this.br = br;
        this.hr = hr;
        this.emDelimiter = emDelimiter;
        this.strongDelimiter = strongDelimiter;
        this.headingStyle = headingStyle;
        this.bulletListMaker = bulletListMaker;
        this.codeBlockStyle = codeBlockStyle;
        this.linkStyle = linkStyle;
        this.linkReferenceStyle = linkReferenceStyle;
        this.fence = fence;
    }
}
