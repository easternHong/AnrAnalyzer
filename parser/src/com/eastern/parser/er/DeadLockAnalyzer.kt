package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc
import com.eastern.parser.model.ThreadDesc


class DeadLockAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        val deadLocksThread = ArrayList<ThreadDesc>()
        contentDesc.threadList.forEach { thread ->
            if (thread.stack[0].contains("Blocked") && thread.waitingLocks.size > 0 && thread.lockedLocks.size > 0) {
                deadLocksThread.add(thread)
            }
        }
        val ret = StringBuilder()
        deadLocksThread.forEach {
            ret.append("(l:")
                    .append(it.lineNumber)
                    .append(")")
            ret.append(it.stack[0])
                    .append("-")
        }
        return "[DeadLockAnalyzer_dead_lock_between:$ret]"
    }
}