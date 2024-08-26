package com.jcasben.composetesting.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class DialogComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenDialogGetATrue_thenShowDialog() {
        composeTestRule.setContent {
            AddTaskDialog(show = true, onDismiss = {}, onTaskAdded = {})
        }
        composeTestRule.onNodeWithTag("addDialogCard").assertIsDisplayed()
    }

    @Test
    fun whenDialogGetAFalse_thenShowDialog() {
        composeTestRule.setContent {
            AddTaskDialog(show = false, onDismiss = {}, onTaskAdded = {})
        }
        composeTestRule.onNodeWithTag("addDialogCard").assertIsNotDisplayed()
    }
}