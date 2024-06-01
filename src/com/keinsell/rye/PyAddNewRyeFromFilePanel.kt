package com.keinsell.rye


import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.module.Module
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.util.NlsSafe
import com.intellij.util.ui.FormBuilder
import org.jetbrains.annotations.SystemDependent
import java.awt.BorderLayout
import javax.swing.JPanel

/**
 *  This source code is created by @koxudaxi Koudai Aono <koxudaxi@gmail.com>
 */

class PyAddNewRyeFromFilePanel(private val module: Module) : JPanel() {

    val envData: Data
        get() = Data(ryePathField.text)

    private val ryePathField = TextFieldWithBrowseButton()

    init {
        ryePathField.apply {
            getRyeExecutable()?.absolutePath?.also { text = it }

            addBrowseFolderListener(
                "Select Path to Rye Executable",
                null,
                module.project,
                FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor()
            )
        }

        layout = BorderLayout()
        val formPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Rye executable:", ryePathField)
            .panel
        add(formPanel, BorderLayout.NORTH)
    }

    fun validateAll(): List<ValidationInfo> = listOfNotNull(validateRyeExecutable(ryePathField.text))

    data class Data(val ryePath: @NlsSafe @SystemDependent String)
}