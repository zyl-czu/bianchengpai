package com.github.zylczu.core.mdc;

import org.slf4j.MDC;

/**
 * @author YiHui
 * @date 2023/5/29
 */
public class MdcUtil {
    public static final String TRACE_ID_KEY = "traceId";

    public static void add(String key, String val) {
        MDC.put(key, val);
    }

    public static void addTraceId() {
        // traceId的生成规则，技术派提供了两种生成策略，可以使用自定义的也可以使用SkyWalking; 实际项目中选择一种即可
        MDC.put(TRACE_ID_KEY, SelfTraceIdGenerator.generate());
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    public static void reset() {
        System.out.println(MDC.get("bizCode"));
        String traceId = MDC.get(TRACE_ID_KEY);
        MDC.clear();
        MDC.put(TRACE_ID_KEY, traceId);
        System.out.println(MDC.get("bizCode"));
    }

    public static void clear() {
        MDC.clear();
    }
}
