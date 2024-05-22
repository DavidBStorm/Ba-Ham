package com.task_baham.ui.main

import android.content.pm.PackageManager
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.Arrays

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule
        .grant(android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private val rootDir = "/sdcard/"

    @Test
    fun testTargetContextPermission() {
        val targetContext = InstrumentationRegistry.getTargetContext()
        assertTrue("Not the target package",
            !targetContext.packageName.endsWith(".test"))
        assertTrue("Permissions not Granted", targetContext.checkSelfPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED )

        val file = File(rootDir + "targetTest.txt")
        if (file.exists()) {
            file.delete()
        }
        assertTrue("File was not created", file.createNewFile())

    }

    @Test
    fun testInstrumentationPermission() {
        val instrumentationContext = InstrumentationRegistry.getContext()
        assertTrue("Not the instrumentation package",
            instrumentationContext.packageName.endsWith(".test"))
        assertTrue("Permissions not Granted", instrumentationContext.checkSelfPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)

        val file = File(rootDir + "instrumentationTest.txt")
        if (file.exists()) {
            file.delete()
        }
        assertTrue("File was not created", file.createNewFile())
        val sdcard = File(rootDir)
        assertTrue("sdcard is empty or file not found", sdcard.list()!!.isNotEmpty() &&
                listOf(*sdcard.list() as Array<out String>).contains("instrumentationTest.txt"))
    }
}