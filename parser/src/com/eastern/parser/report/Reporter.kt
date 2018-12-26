package com.eastern.parser.report


class Reporter : IReporter {

    val result = ArrayList<String>()

    override fun report(result: String) {
        this.result.add(result.plus("\n"))
    }
}