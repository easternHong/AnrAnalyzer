package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


class ContentProviderAnalyzer : IAnalyzer {
    override fun analyzer(contentDesc: ContentDesc): String {
        return "[ContentProviderAnalyzer:]"
    }
}