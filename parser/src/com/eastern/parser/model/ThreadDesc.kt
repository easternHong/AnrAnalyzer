package com.eastern.parser.model


class ThreadDesc {

    /**
     * stack
     */
    val stack = ArrayList<String>()
    /**
     * 持有的锁
     */
    var lockedLocks = ArrayList<String>()

    /**
     * 等待的锁
     */
    val waitingLocks = ArrayList<String>()

    var lineNumber = -1

    var desc = ""
}