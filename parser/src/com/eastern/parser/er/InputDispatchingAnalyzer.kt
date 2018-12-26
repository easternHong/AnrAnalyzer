package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


class InputDispatchingAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        return "[InputDispatchingAnalyzer:]"
    }
}