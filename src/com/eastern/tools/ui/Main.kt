package com.eastern.tools.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*


private const val WINDOW_WIDTH = 900
private const val WINDOW_HEIGHT = 400
private const val BTN_WIDTH = 200
private const val PADDING = 10

class Main : AnAction(), ActionListener {


    private lateinit var tvLogcat: JTextField
    private lateinit var tvMainThread: JTextField
    private lateinit var tvSysTrace: JTextField

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

        //系统traces.txt
        uploadSysTrace()
        //anr现场logcat.txt
        uploadLogcat()
        //当时主线程堆栈
        uploadMainThreadTrace()

        //开始分析
        startAnalyze()
    }

    private fun startAnalyze() {
        val btn = JButton("开始分析")
        btn.setBounds(PADDING, PADDING + 40 + 40 + 40, WINDOW_WIDTH - PADDING * 2, 30)
        jPanel.add(btn)
        btn.addActionListener {

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