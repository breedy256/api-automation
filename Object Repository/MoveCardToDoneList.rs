<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>MoveCardToDoneList</name>
   <tag></tag>
   <elementGuidId>a164ba20-f926-4a50-8862-c8b723667d28</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://api.trello.com/1/lists/${id}/moveAllCards?key=${key}&amp;token=${token}&amp;idBoard=${boardID}&amp;idList=${doneListID}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.key</defaultValue>
      <description></description>
      <id>5d84b3d3-ce4d-4ea2-b683-4eb6cb94e8fb</id>
      <masked>false</masked>
      <name>key</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.token</defaultValue>
      <description></description>
      <id>51169478-ab74-4ed2-8886-6a2a1602a715</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.boardID</defaultValue>
      <description></description>
      <id>ea0d97f1-6408-4311-a5a2-06be3a12a88a</id>
      <masked>false</masked>
      <name>boardID</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.doneListID</defaultValue>
      <description></description>
      <id>09b2b9f1-e17b-489b-a9b1-a7e0b7634708</id>
      <masked>false</masked>
      <name>doneListID</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.todoListID</defaultValue>
      <description></description>
      <id>c177a3ed-64f4-479d-8b40-951baef01b9e</id>
      <masked>false</masked>
      <name>id</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
