 // http://viralpatel.net/blogs/java-xml-xpath-tutorial-parse-xml/
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.omg.CORBA.portable.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class XMLParSer {
    public static void main(String[] args) {
 
        try {
            //FileInputStream file = new FileInputStream(new File("C://Abhi//JavaWorkSpace//Employee.txt"));
        	FileInputStream file = new FileInputStream(new File("C://Abhi//JavaWorkSpace//NewFile.xml"));
        	
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
             
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
             
            Document xmlDocument = builder.parse(file);
 
            XPath xPath =  XPathFactory.newInstance().newXPath();
            
            System.out.println("*************************");
            String expression = "//@href";
            System.out.println(expression);
            //String user = xPath.compile(expression).evaluate(xmlDocument);
            //System.out.println(user);
 
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
            
     /*      
            System.out.println("*************************");
            String expression = "/Employees/Employee[@emplid='3333']/email";
            System.out.println(expression);
            String email = xPath.compile(expression).evaluate(xmlDocument);
            System.out.println(email);
 
            System.out.println("*************************");
            expression = "/Employees/Employee/firstname";
            System.out.println(expression);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[@type='admin']/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[@emplid='2222']";
            System.out.println(expression);
            Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
            if(null != node) {
                nodeList = node.getChildNodes();
                for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) {
                    Node nod = nodeList.item(i);
                    if(nod.getNodeType() == Node.ELEMENT_NODE)
                        System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue()); 
                }
            }
             
            System.out.println("*************************");
 
            expression = "/Employees/Employee[age>40]/firstname";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            System.out.println(expression);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
         
            System.out.println("*************************");
            expression = "/Employees/Employee[1]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
            System.out.println("*************************");
            expression = "/Employees/Employee[position() <= 2]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }
 
            System.out.println("*************************");
            expression = "/Employees/Employee[last()]/firstname";
            System.out.println(expression);
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue()); 
            }*/
 
            System.out.println("*************************");
            excutePost("http://localhost/rest/users/abhi2/remove","admin:Admin12#");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }       
    }
    
    public static String excutePost(String targetURL, String urlParameters)
    {
      URL url;
      HttpURLConnection connection = null;

      try {
    	String username = "admin";
    	String password = "Admin12#";
        //Create connection
        url = new URL(targetURL);
        connection = (HttpURLConnection)url.openConnection();
        ///connection.setRequestMethod("DELETE");
        /*connection.setRequestProperty("Content-Type", 
             "application/xml");
        System.out.println(	targetURL);
        System.out.println( urlParameters);
        connection.setRequestProperty("Content-Length", "" + 
                 Integer.toString(urlParameters.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");  */

        String encodedPassword = username + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(encodedPassword.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encoded);
        connection.setRequestProperty("Content-type", "application/xml");
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestMethod("DELETE");
        connection.setUseCaches (false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
        wr.writeBytes (urlParameters);
        System.out.println("*******" + urlParameters);
        wr.flush ();
        wr.close ();

        //Get Response	
        InputStream is = (InputStream) connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer(); 
        while((line = rd.readLine()) != null) {
          response.append(line);
          response.append('\r');
        }
        rd.close();
        return response.toString();

      } catch (Exception e) {

        e.printStackTrace();
        return null;

      } finally {

        if(connection != null) {
          connection.disconnect(); 
        }
      }
    }
    
}