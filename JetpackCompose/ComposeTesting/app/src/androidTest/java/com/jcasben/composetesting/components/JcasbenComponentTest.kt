package com.jcasben.composetesting.components

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.swipeUp
import org.junit.Rule
import org.junit.Test

class JcasbenComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myFirstTest() {
        composeTestRule.setContent {
            JcasbenComponent()
        }
        // Finders
        composeTestRule.onNodeWithText("TEXT 1")
        composeTestRule.onNodeWithTag("text 2")
        composeTestRule.onNodeWithContentDescription("star")

        composeTestRule.onAllNodesWithText("TEXT")
        composeTestRule.onAllNodesWithTag(":")
        composeTestRule.onAllNodesWithContentDescription("icon")

        // Actions
        composeTestRule.onNodeWithText("text", ignoreCase = true).performClick()
        composeTestRule.onAllNodesWithText("a").onFirst().performClick()
        composeTestRule.onNodeWithText("text").performTouchInput {
            doubleClick()
            longClick()
            swipeDown()
            swipeUp()
            swipeLeft()
            swipeRight()
        }
        composeTestRule.onNodeWithText("text").performScrollTo()
        composeTestRule.onNodeWithText("text").performImeAction()
        composeTestRule.onNodeWithText("text").performTextClearance()
        composeTestRule.onNodeWithText("text").performTextInput("hola")
        composeTestRule.onNodeWithText("text").performTextReplacement("ADasdasdsa")

        // Assertions
        composeTestRule.onNodeWithText("text").assertExists()
        composeTestRule.onNodeWithText("text").assertDoesNotExist()
        composeTestRule.onNodeWithText("text").assertContentDescriptionEquals("asdasdsad")
        composeTestRule.onNodeWithText("text").assertContentDescriptionContains("asdasdad")
        composeTestRule.onNodeWithText("text").assertIsDisplayed()
        composeTestRule.onNodeWithText("text").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("text").assertIsEnabled()
        composeTestRule.onNodeWithText("text").assertIsNotEnabled()
        composeTestRule.onNodeWithText("text").assertIsSelected()
        composeTestRule.onNodeWithText("text").assertIsNotSelected()
        composeTestRule.onNodeWithText("text").assertIsFocused()
        composeTestRule.onNodeWithText("text").assertIsNotFocused()
        composeTestRule.onNodeWithText("text").assertIsOn()
        composeTestRule.onNodeWithText("text").assertIsOff()
        composeTestRule.onNodeWithText("text").assertTextEquals()
        composeTestRule.onNodeWithText("text").assertTextContains("jcasben")
    }

    @Test
    fun whenComponentStart_thenVerifyContentIsJesus() {
        composeTestRule.setContent { JcasbenComponent() }

        composeTestRule.onNodeWithText("jesus", ignoreCase = true).assertExists()
        composeTestRule.onNodeWithTag("tf").assertTextContains("Jesus")
    }

    @Test
    fun whenNameIsAdded_thenVerifyTextContainsGreeting() {
        composeTestRule.setContent { JcasbenComponent() }

        composeTestRule.onNodeWithTag("tf").performTextReplacement("Pablo")
        composeTestRule.onNodeWithTag("textGreeting").assertTextEquals("Te llamas Pablo")
    }
}