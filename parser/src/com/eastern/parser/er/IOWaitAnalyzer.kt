package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


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