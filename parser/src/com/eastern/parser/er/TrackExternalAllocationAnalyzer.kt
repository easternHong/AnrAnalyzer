package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc

/**
 * 是否内存不足
 * dalvik.system.VMRuntime.trackExternalAllocation(NativeMethod)
 */
class TrackExternalAllocationAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        var lowMemory = false
        contentDesc.threadList.forEach { thread ->
            thread.stack.forEach {
                if (it.contains("trackExternalAllocation")) {
                    lowMemory = true
                }
            }
        }

        return "[TrackExternalAllocationAnalyzer:可能内存不足:$lowMemory]"
    }
}