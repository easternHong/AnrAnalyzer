package com.eastern.parser.systrace.er

import com.eastern.parser.systrace.model.ContentDesc


class ContentProviderAnalyzer : IAnalyzer {
    override fun analyzer(contentDesc: ContentDesc): String {
        return "[ContentProviderAnalyzer:]"
    }
}