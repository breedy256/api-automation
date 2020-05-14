import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

//Run CreateNewBoard request
response1 = WS.sendRequest(findTestObject('Object Repository/CreateNewBoard'))

//Tests
WS.verifyResponseStatusCode(response1, 200)
WS.verifyElementPropertyValue(response1, 'name', 'Auto Board')
WS.verifyElementPropertyValue(response1, 'closed', false)
WS.verifyElementPropertyValue(response1, 'prefs.permissionLevel', 'private')

//Store board id into the boardID global variable
def slurper = new groovy.json.JsonSlurper()
def result1 = slurper.parseText(response1.getResponseBodyContent())
def boardID = result1.id
System.out.println('boardID = ' + boardID)
GlobalVariable.boardID = boardID
System.out.println('Global boardID = ' + GlobalVariable.boardID)

//Run AddDoneList request
response2 = WS.sendRequest(findTestObject('Object Repository/AddDoneList'))

//Tests
WS.verifyResponseStatusCode(response2, 200)
WS.verifyElementPropertyValue(response2, 'name', 'DONE')
WS.verifyElementPropertyValue(response2, 'closed', false)

//Store DONE list id into the doneListID global variable
def result2 = slurper.parseText(response2.getResponseBodyContent())
def doneListID = result2.id
System.out.println('doneListID = ' + doneListID)
GlobalVariable.doneListID = doneListID
System.out.println('Global doneListID = ' + GlobalVariable.doneListID)

//Run AddTodoList request
response3 = WS.sendRequest(findTestObject('Object Repository/AddTodoList'))

//Tests
WS.verifyResponseStatusCode(response3, 200)
WS.verifyElementPropertyValue(response3, 'name', 'TODO')
WS.verifyElementPropertyValue(response3, 'closed', false)

//Store TODO list id into the todoListID global variable
def result3 = slurper.parseText(response3.getResponseBodyContent())
def todoListID = result3.id
System.out.println('todoListID = ' + todoListID)
GlobalVariable.todoListID = todoListID
System.out.println('Global todoListID = ' + todoListID)

//Run AddCardToTodoList request
response4 = WS.sendRequest(findTestObject('Object Repository/AddCardToTodoList'))

//Tests
WS.verifyResponseStatusCode(response4, 200)
WS.verifyElementPropertyValue(response4, 'name', 'My Card')
WS.verifyElementPropertyValue(response4, 'closed', false)
WS.verifyElementPropertyValue(response4, 'idList', GlobalVariable.todoListID)
WS.verifyElementPropertyValue(response4, 'idBoard', GlobalVariable.boardID)

//Store card id into cardID global variable
def result4 = slurper.parseText(response4.getResponseBodyContent())
def cardID = result4.id
System.out.println('cardID = ' + cardID)
GlobalVariable.cardID = cardID
System.out.println('Global cardID = ' + GlobalVariable.cardID)

//Run MoveCardToDoneList request
response5 = WS.sendRequest(findTestObject('Object Repository/MoveCardToDoneList'))

//MoveCardToDoneList tests
WS.verifyResponseStatusCode(response5, 200)
WS.verifyElementPropertyValue(response5, '[0].id', GlobalVariable.cardID)
WS.verifyElementPropertyValue(response5, '[0].idList', GlobalVariable.doneListID)

//Run DeleteBoard request
response6 = WS.sendRequest(findTestObject('Object Repository/DeleteBoard'))

//Tests
WS.verifyResponseStatusCode(response6, 200)

//Run VerifyBoardDeleted request
response7 = WS.sendRequest(findTestObject('Object Repository/VerifyBoardDeleted'))

//Tests
WS.verifyResponseStatusCode(response7, 404)
