package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


class BroadcastAnalyzer : IAnalyzer {
    override fun analyzer(contentDesc: ContentDesc): String {
        return "[BroadcastAnalyzer:]"
    }
}