package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc


interface IAnalyzer {

    /**
     * 解析器
     */
    fun analyzer(contentDesc: ContentDesc): String

}