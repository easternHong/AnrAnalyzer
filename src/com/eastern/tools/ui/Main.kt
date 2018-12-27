package com.eastern.tools.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.regex.Pattern
import javax.swing.*


private const val WINDOW_WIDTH = 900
private const val WINDOW_HEIGHT = 400
private const val BTN_WIDTH = 200
private const val PADDING = 10

private val PATTERNS = Pattern.compile("//(?!^abstract\$|^abstract\\..*|.*\\.abstract\\..*|.*\\.abstract\$|^assert\$|^assert\\..*|.*\\.assert\\..*|.*\\.assert\$|^boolean\$|^boolean\\..*|.*\\.boolean\\..*|.*\\.boolean\$|^break\$|^break\\..*|.*\\.break\\..*|.*\\.break\$|^byte\$|^byte\\..*|.*\\.byte\\..*|.*\\.byte\$|^case\$|^case\\..*|.*\\.case\\..*|.*\\.case\$|^catch\$|^catch\\..*|.*\\.catch\\..*|.*\\.catch\$|^char\$|^char\\..*|.*\\.char\\..*|.*\\.char\$|^class\$|^class\\..*|.*\\.class\\..*|.*\\.class\$|^const\$|^const\\..*|.*\\.const\\..*|.*\\.const\$|^continue\$|^continue\\..*|.*\\.continue\\..*|.*\\.continue\$|^default\$|^default\\..*|.*\\.default\\..*|.*\\.default\$|^do\$|^do\\..*|.*\\.do\\..*|.*\\.do\$|^double\$|^double\\..*|.*\\.double\\..*|.*\\.double\$|^else\$|^else\\..*|.*\\.else\\..*|.*\\.else\$|^enum\$|^enum\\..*|.*\\.enum\\..*|.*\\.enum\$|^extends\$|^extends\\..*|.*\\.extends\\..*|.*\\.extends\$|^final\$|^final\\..*|.*\\.final\\..*|.*\\.final\$|^finally\$|^finally\\..*|.*\\.finally\\..*|.*\\.finally\$|^float\$|^float\\..*|.*\\.float\\..*|.*\\.float\$|^for\$|^for\\..*|.*\\.for\\..*|.*\\.for\$|^goto\$|^goto\\..*|.*\\.goto\\..*|.*\\.goto\$|^if\$|^if\\..*|.*\\.if\\..*|.*\\.if\$|^implements\$|^implements\\..*|.*\\.implements\\..*|.*\\.implements\$|^import\$|^import\\..*|.*\\.import\\..*|.*\\.import\$|^instanceof\$|^instanceof\\..*|.*\\.instanceof\\..*|.*\\.instanceof\$|^int\$|^int\\..*|.*\\.int\\..*|.*\\.int\$|^interface\$|^interface\\..*|.*\\.interface\\..*|.*\\.interface\$|^long\$|^long\\..*|.*\\.long\\..*|.*\\.long\$|^native\$|^native\\..*|.*\\.native\\..*|.*\\.native\$|^new\$|^new\\..*|.*\\.new\\..*|.*\\.new\$|^package\$|^package\\..*|.*\\.package\\..*|.*\\.package\$|^private\$|^private\\..*|.*\\.private\\..*|.*\\.private\$|^protected\$|^protected\\..*|.*\\.protected\\..*|.*\\.protected\$|^public\$|^public\\..*|.*\\.public\\..*|.*\\.public\$|^return\$|^return\\..*|.*\\.return\\..*|.*\\.return\$|^short\$|^short\\..*|.*\\.short\\..*|.*\\.short\$|^static\$|^static\\..*|.*\\.static\\..*|.*\\.static\$|^strictfp\$|^strictfp\\..*|.*\\.strictfp\\..*|.*\\.strictfp\$|^super\$|^super\\..*|.*\\.super\\..*|.*\\.super\$|^switch\$|^switch\\..*|.*\\.switch\\..*|.*\\.switch\$|^synchronized\$|^synchronized\\..*|.*\\.synchronized\\..*|.*\\.synchronized\$|^this\$|^this\\..*|.*\\.this\\..*|.*\\.this\$|^throw\$|^throw\\..*|.*\\.throw\\..*|.*\\.throw\$|^throws\$|^throws\\..*|.*\\.throws\\..*|.*\\.throws\$|^transient\$|^transient\\..*|.*\\.transient\\..*|.*\\.transient\$|^try\$|^try\\..*|.*\\.try\\..*|.*\\.try\$|^void\$|^void\\..*|.*\\.void\\..*|.*\\.void\$|^volatile\$|^volatile\\..*|.*\\.volatile\\..*|.*\\.volatile\$|^while\$|^while\\..*|.*\\.while\\..*|.*\\.while\$)(^(?:[a-z_]+(?:\\d*[a-zA-Z_]*)*)(?:\\.[a-z_]+(?:\\d*[a-zA-Z_]*)*)*\$)")

class Main : AnAction(), ActionListener {


    private lateinit var tvLogcat: JTextField
    private lateinit var tvMainThread: JTextField
    private lateinit var tvSysTrace: JTextField
    private lateinit var tvPkgName: JTextField

    private lateinit var chooser: JFileChooser
    private lateinit var btnSysTrace: JButton
    private lateinit var btnLogcatTrace: JButton
    private lateinit var btnMainThread: JButton
    private lateinit var jFrame: JFrame
    private lateinit var jPanel: JPanel


    override fun actionPerformed(p0: ActionEvent?) {
        var txtField: JTextField = tvSysTrace
        when {
            p0!!.source == btnSysTrace -> {
                txtField = tvSysTrace
            }
            p0.source == btnLogcatTrace -> {
                txtField = tvLogcat
            }
            p0.source == btnMainThread -> {
                txtField = tvMainThread
            }
        }
        chooser = JFileChooser(File(System.getProperty("user.home"))) //Downloads Directory as default
        chooser.dialogTitle = "Select Location"
        chooser.fileSelectionMode = JFileChooser.FILES_ONLY
        chooser.isAcceptAllFileFilterUsed = false
        if (chooser.showSaveDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
            txtField.text = chooser.selectedFile.path
        }
    }


    override fun actionPerformed(p0: AnActionEvent?) {
        jFrame = JFrame("ANR堆栈-分析工具")
        jFrame.minimumSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT / 2)
        jFrame.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT / 2)
        jPanel = JPanel()
        jPanel.layout = null
        val c = jFrame.contentPane
        c.add(jPanel, BorderLayout.CENTER)
        jFrame.layout = null
        c.add(jPanel)
        jPanel.setBounds(0, 0, jFrame.width, jFrame.height)

        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.setLocation(100, 70)
        jFrame.isVisible = true

        uploadPkgName()
        //系统traces.txt
        uploadSysTrace()
        //anr现场logcat.txt
        uploadLogcat()
        //当时主线程堆栈
        uploadMainThreadTrace()

        //开始分析
        startAnalyze()

        getLastResult()
    }


    private fun startAnalyze() {
        val btn = JButton("开始分析")
        btn.setBounds(PADDING, PADDING + 40 + 40 + 40 + 40, WINDOW_WIDTH - PADDING * 2, 30)
        jPanel.add(btn)
        btn.addActionListener {
            saveLastResult()
        }
    }

    private fun uploadPkgName() {
        tvPkgName = JTextField()
        tvPkgName.setBounds(PADDING, PADDING + 40 + 40 + 40, WINDOW_WIDTH - PADDING * 2, 30)
        tvPkgName.text = "enter pkgName"
        jPanel.add(tvPkgName)
    }

    private fun getLastResult() {
        val file = File(System.getProperty("user.home") + File.separator + ".anr.properties")
        val prop = Properties()
        var input: FileInputStream? = null
        try {
            input = FileInputStream(file)
            prop.load(input)
            val pkgName = prop.getProperty("pkgName")
            if (pkgName.isNotEmpty()) {
                tvPkgName.text = pkgName
            }

            val logcatFile = prop.getProperty("logcatFile")
            if (logcatFile.isNotEmpty()) {
                tvLogcat.text = logcatFile
            }
            val mainTraceFile = prop.getProperty("mainTraceFile")
            if (mainTraceFile.isNotEmpty()) {
                tvMainThread.text = mainTraceFile
            }

            val sysTraceFile = prop.getProperty("sysTraceFile")
            if (sysTraceFile.isNotEmpty()) {
                tvSysTrace.text = sysTraceFile
            }
        } catch (io: IOException) {
            io.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun saveLastResult() {
        val file = File(System.getProperty("user.home") + File.separator + ".anr.properties")
        val prop = Properties()
        try {
            prop.setProperty("pkgName", tvPkgName.text)
            if (File(tvLogcat.text).exists()) {
                prop.setProperty("logcatFile", tvLogcat.text)
            }
            if (File(tvMainThread.text).exists()) {
                prop.setProperty("mainTraceFile", tvMainThread.text)
            }
            if (File(tvSysTrace.text).exists()) {
                prop.setProperty("sysTraceFile", tvSysTrace.text)
            }
            prop.store(FileOutputStream(file), null)
        } catch (t: Throwable) {
        }
    }

    private fun uploadLogcat() {
        btnLogcatTrace = JButton("系统Logcat.txt")
        btnLogcatTrace.setBounds(PADDING, PADDING, BTN_WIDTH, 30)
        jPanel.add(btnLogcatTrace)
        btnLogcatTrace.addActionListener(this)

        tvLogcat = JTextField()
        tvLogcat.setBounds(PADDING + BTN_WIDTH + PADDING, PADDING, WINDOW_WIDTH - (10 + BTN_WIDTH + 10 + 10), 30)
        tvLogcat.text = "请上传logcat.txt"
        jPanel.add(tvLogcat)
    }


    private fun uploadSysTrace() {
        btnSysTrace = JButton("系统traces.txt")
        btnSysTrace.setBounds(PADDING, PADDING + 40, BTN_WIDTH, 30)
        jPanel.add(btnSysTrace)
        btnSysTrace.addActionListener(this)

        tvSysTrace = JTextField()
        tvSysTrace.setBounds(PADDING + BTN_WIDTH + PADDING, 50, WINDOW_WIDTH - (10 + BTN_WIDTH + 10 + 10), 30)
        tvSysTrace.text = "请上传系统traces.txt"
        jPanel.add(tvSysTrace)
    }

    private fun uploadMainThreadTrace() {
        btnMainThread = JButton("主线程traces.txt")
        btnMainThread.setBounds(PADDING, PADDING + 40 + 40, BTN_WIDTH, 30)
        jPanel.add(btnMainThread)
        btnMainThread.addActionListener(this)

        tvMainThread = JTextField()
        tvMainThread.setBounds(PADDING + BTN_WIDTH + PADDING, 50 + 40, WINDOW_WIDTH - (10 + BTN_WIDTH + 10 + 10), 30)
        tvMainThread.text = "请上传主线程crash_trace.txt"
        jPanel.add(tvMainThread)
    }
}