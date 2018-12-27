package com.eastern.parser.systrace.er

import com.eastern.parser.systrace.model.ContentDesc

/**
 * 如果CPU使用量很少，说明主线程被BLOCK了
 */
class CpuAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        return "[CpuAnalyzer:是否高CPU负载：]"
    }
}