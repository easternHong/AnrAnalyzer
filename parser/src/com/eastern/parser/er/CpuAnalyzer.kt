package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc

/**
 * 如果CPU使用量很少，说明主线程被BLOCK了
 */
class CpuAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        return "[CpuAnalyzer:是否高CPU负载：]"
    }
}