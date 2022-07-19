package com.iprayforgod.core.modules.logger.utils

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import java.io.File
import java.io.FileWriter
import java.io.IOException

class DiskLogHandler(
    looper: Looper,
    private val folder: String?,
    private val fileName: String?,
    private val maxFileSize: Int
) : Handler(looper) {

    companion object {
        private val defaultLooper: Looper
            get() {
                val ht = HandlerThread("AndroidFileLogger")
                ht.start()
                return ht.looper
            }
    }

    constructor(folder: String?, fileName: String?, maxFileSize: Int) : this(
        defaultLooper, folder, fileName, maxFileSize
    )

    override fun handleMessage(msg: Message) {
        if (folder != null && fileName != null) {
            val content = msg.obj as String
            var fileWriter: FileWriter? = null
            val logFile = getLogFile(folder, fileName)
            try {
                fileWriter = FileWriter(logFile, true).apply {
                    writeLog(this, content)
                    flush()
                    close()
                }
            } catch (e: IOException) {
                fileWriter?.let {
                    try {
                        it.flush()
                        it.close()
                    } catch (e1: IOException) { /* fail silently */ }
                }
            }
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun getLogFile(folderName: String, fileName: String): File {
        val folder = File(folderName)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        var newFileCount = 0
        var newFile = createLogFile(folder, fileName, newFileCount)
        var existingFile: File? = null
        while (newFile.exists()) {
            existingFile = newFile
            newFileCount++
            newFile = createLogFile(folder, fileName, newFileCount)
        }

        return if (existingFile != null) {
            if (existingFile.length() >= maxFileSize) {
                newFile
            } else existingFile
        } else newFile
    }

    /**
     * Description: This is used to create a new file
     * @param folder --> Name of the folder
     * @param fileName --> Name of the file
     * @param newFileCount --> Count
     * @return New file
     */
    private fun createLogFile(
        folder: File,
        fileName: String,
        newFileCount: Int
    ) = File(folder, String.format("%s_%s.csv", fileName, newFileCount))

    /**
     * This is always called on a single background thread.
     * Implementing classes must ONLY write to the fileWriter and nothing more.
     * The abstract class takes care of everything else including close the stream and catching IOException
     *
     * @param fileWriter an instance of FileWriter already initialised to the correct file
     */
    @Throws(IOException::class)
    private fun writeLog(fileWriter: FileWriter, content: String) {
        fileWriter.append(content)
    }
}
