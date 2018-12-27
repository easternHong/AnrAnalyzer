package com.eastern.parser.systrace.er

import com.eastern.parser.systrace.model.ContentDesc


class IOWaitAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {

        var iowait = false
        contentDesc.systemDesc.forEach {
            if (it.contains("iowait")) {
                iowait = true
            }
        }
        return "[IOWaitAnalyzer:io阻塞:$iowait]"
    }

}