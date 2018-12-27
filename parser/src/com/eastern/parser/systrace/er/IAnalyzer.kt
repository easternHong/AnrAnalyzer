package com.eastern.parser.systrace.er

import com.eastern.parser.systrace.model.ContentDesc


interface IAnalyzer {

    /**
     * 解析器
     */
    fun analyzer(contentDesc: ContentDesc): String

}