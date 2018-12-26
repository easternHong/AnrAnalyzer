package com.eastern.parser.er

import com.eastern.parser.model.ContentDesc
import com.eastern.parser.model.ThreadDesc


class LockWaitAnalyzer : IAnalyzer {

    override fun analyzer(contentDesc: ContentDesc): String {
        var uiWaitLock = ""
        var isBlockByThread = ThreadDesc()
        contentDesc.threadList.forEach { thread ->
            if (thread.stack[0].contains(" tid=1 ") && thread.stack[0].contains("Blocked")) {
                if (thread.waitingLocks.isNotEmpty()) {
                    uiWaitLock = thread.waitingLocks[0]
                }
            }
        }
        if (uiWaitLock.isNotEmpty()) {
            contentDesc.threadList.forEach { thread ->
                thread.lockedLocks.forEach {
                    if (it == uiWaitLock && !thread.stack[0].contains(" tid=1 ")) {
                        isBlockByThread = thread
                    }
                }
            }
        }
        return "[LockWaitAnalyzer_ui_blocked_by :[(l:${isBlockByThread.lineNumber})${isBlockByThread.stack[0]}]]"
    }
}