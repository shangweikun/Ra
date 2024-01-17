//package org.example;
//
//import jakarta.xml.soap.*;
//
//public class SoapClientDemo {
//    public static void main(String args[]) {
//        try {
//            // 创建SOAPConnectionFactory对象
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            // 使用SOAPConnectionFactory创建SOAPConnection对象
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//
//            // 创建SOAP消息的目标URL
//            String url = "http://localhost:7001/MyService";
//
//            // 创建SOAP消息
//            MessageFactory messageFactory = MessageFactory.newInstance();
//            SOAPMessage soapMessage = messageFactory.createMessage();
//            SOAPPart soapPart = soapMessage.getSOAPPart();
//
//            SOAPEnvelope envelope = soapPart.getEnvelope();
//            envelope.addNamespaceDeclaration("web", "http://www.example.com/");
//
//            SOAPBody soapBody = envelope.getBody();
//            SOAPElement soapElement = soapBody.addChildElement("sayHello", "web");
//            SOAPElement element = soapElement.addChildElement("arg0");
//            element.addTextNode("world");
//
//            MimeHeaders headers = soapMessage.getMimeHeaders();
//            headers.addHeader("SOAPAction", "http://www.example.com/MyService/sayHello");
//
//            soapMessage.saveChanges();
//
//            // 打印出请求的SOAP消息
//            System.out.println("Request SOAP Message:");
//            soapMessage.writeTo(System.out);
//            System.out.println("\n");
//
//            // 发送SOAP消息并获取响应
//            SOAPMessage soapResponse = soapConnection.call(soapMessage, url);
//
//            // 打印出响应的SOAP消息
//            System.out.println("Response SOAP Message:");
//            soapResponse.writeTo(System.out);
//
//            soapConnection.close();
//        } catch (Exception e) {
//            System.err.println("Error occurred while sending SOAP Request to Server");
//            e.printStackTrace();
//        }
//    }
//}