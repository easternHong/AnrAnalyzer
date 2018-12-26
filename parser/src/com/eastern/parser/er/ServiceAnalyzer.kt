package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


class ServiceAnalyzer : IAnalyzer {
    override fun analyzer(contentDesc: ContentDesc): String {
        return "[ServiceAnalyzer:]"
    }
}