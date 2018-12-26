package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


class IPCAnalyzer : IAnalyzer {
    override fun analyzer(contentDesc: ContentDesc): String {
        return "[IPCAnalyzer:]"
    }
}