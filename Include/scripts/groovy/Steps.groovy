import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class Steps {
	@Given("I have created new board with (.*) name")
	def createNewBoard(String boardName) {
		//Send CreateNewBoard POST request
		ResponseObject response1 = WS.sendRequest(findTestObject('Object Repository/CreateNewBoard'))
		
		//Tests
		WS.verifyResponseStatusCode(response1, 200)
		WS.verifyElementPropertyValue(response1, 'name', boardName)
		WS.verifyElementPropertyValue(response1, 'closed', false)
		WS.verifyElementPropertyValue(response1, 'prefs.permissionLevel', 'private')
		
		//Store board id into the boardID global variable
		def slurper = new groovy.json.JsonSlurper()
		def result1 = slurper.parseText(response1.getResponseBodyContent())
		def boardID = result1.id
		System.out.println('boardID = ' + boardID)
		GlobalVariable.boardID = boardID
		System.out.println('Global boardID = ' + GlobalVariable.boardID)
	}
	
	@Given("I have added (.*) and (.*) list to my board")
	def addTwoListsToBoard(String listOne, String listTwo) {
		//Run AddDoneList request
		ResponseObject response2 = WS.sendRequest(findTestObject('Object Repository/AddDoneList'))
		
		//Tests
		WS.verifyResponseStatusCode(response2, 200)
		WS.verifyElementPropertyValue(response2, 'name', listOne)
		WS.verifyElementPropertyValue(response2, 'closed', false)
		
		//Store DONE list id into the doneListID global variable
		def slurper = new groovy.json.JsonSlurper()
		def result2 = slurper.parseText(response2.getResponseBodyContent())
		def doneListID = result2.id
		System.out.println('doneListID = ' + doneListID)
		GlobalVariable.doneListID = doneListID
		System.out.println('Global doneListID = ' + GlobalVariable.doneListID)
		
		//Run AddTodoList request
		ResponseObject response3 = WS.sendRequest(findTestObject('Object Repository/AddTodoList'))
		
		//Tests
		WS.verifyResponseStatusCode(response3, 200)
		WS.verifyElementPropertyValue(response3, 'name', listTwo)
		WS.verifyElementPropertyValue(response3, 'closed', false)
		
		//Store TODO list id into the todoListID global variable
		def result3 = slurper.parseText(response3.getResponseBodyContent())
		def todoListID = result3.id
		System.out.println('todoListID = ' + todoListID)
		GlobalVariable.todoListID = todoListID
		System.out.println('Global todoListID = ' + todoListID)
	}
	
	@When("I add (.*) card to TODO list")
	def addCardToTodoList(String cardName) {
		//Run AddCardToTodoList request
		ResponseObject response4 = WS.sendRequest(findTestObject('Object Repository/AddCardToTodoList'))
		
		//Tests
		WS.verifyResponseStatusCode(response4, 200)
		WS.verifyElementPropertyValue(response4, 'name', cardName)
		WS.verifyElementPropertyValue(response4, 'closed', false)
		WS.verifyElementPropertyValue(response4, 'idList', GlobalVariable.todoListID)
		WS.verifyElementPropertyValue(response4, 'idBoard', GlobalVariable.boardID)
		
		//Store card id into cardID global variable
		def slurper = new groovy.json.JsonSlurper()
		def result4 = slurper.parseText(response4.getResponseBodyContent())
		def cardID = result4.id
		System.out.println('cardID = ' + cardID)
		GlobalVariable.cardID = cardID
		System.out.println('Global cardID = ' + GlobalVariable.cardID)
	}
	
	@Then("I should be able to move my card to DONE list")
	def moveCardFromTodoListToDoneList() {
		//Run MoveCardToDoneList request
		ResponseObject response5 = WS.sendRequest(findTestObject('Object Repository/MoveCardToDoneList'))
		
		//MoveCardToDoneList tests
		WS.verifyResponseStatusCode(response5, 200)
		WS.verifyElementPropertyValue(response5, '[0].id', GlobalVariable.cardID)
		WS.verifyElementPropertyValue(response5, '[0].idList', GlobalVariable.doneListID)
	}
	
	@When("I delete my board")
	def deleteBoard() {
		//Run DeleteBoard request
		ResponseObject response6 = WS.sendRequest(findTestObject('Object Repository/DeleteBoard'))
		
		//Tests
		WS.verifyResponseStatusCode(response6, 200)
	}
	
	@Then("I should be able to verify that my board was deleted")
	def verifyBoardDeleted() {
		//Run VerifyBoardDeleted request
		ResponseObject response7 = WS.sendRequest(findTestObject('Object Repository/VerifyBoardDeleted'))
		
		//Tests
		WS.verifyResponseStatusCode(response7, 404)
	}
}