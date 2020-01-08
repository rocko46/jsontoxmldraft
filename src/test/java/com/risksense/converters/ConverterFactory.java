package com.risksense.converters;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.*;
import org.apache.commons.io.IOUtils;

import org.junit.Test;

import com.fasterxml.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.XML;

public class ConverterFactory implements XMLJSONConverterI {
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public static String travel(String json) throws JsonProcessingException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\"?>");
		sb.append("\r\n\t"+"<"+"object"+">");
		JsonFactory factory = new JsonFactory();
	

		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(json);

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			//System.out.println("Key: " + field.getKey() +"\t" + field.getValue().getClass().getSimpleName() + "\tValue:" + field.getValue());
			
		//	System.out.println(field.getValue().getClass().getSimpleName());
			
		
			
			
			if (field.getValue().getClass().getSimpleName().contentEquals("BooleanNode"))
			{
				
				sb.append("\r\n\t"+"<" + "boolean " +"name="+field.getKey() + ">"+ field.getValue()+ "</"+"boolean"+">");
				//sb.append(printbool(fieldsIterator));
				
			}
			
			

			if (field.getValue().getClass().getSimpleName().contentEquals("TextNode"))
			{
			
				sb.append("\r\n\t"+"<" + "string " +"name="+field.getKey() + ">"+ field.getValue()+ "</"+"string"+">");
				
		
		    }
			
			
			if (field.getValue().getClass().getSimpleName().contentEquals("DoubleNode"))
			{
			
				sb.append("\r\n\t\t"+"<" + "number " +"name="+field.getKey() + ">"+ field.getValue()+ "</"+"boolean"+">");
				
		
		    }
			

			if (field.getValue().getClass().getSimpleName().contentEquals("IntNode"))
			{
			
				sb.append("\r\n\t\t"+"<" + "number " +"name="+field.getKey() + ">"+ field.getValue()+ "</"+"boolean"+">");
				
		
		    }
			
			if (field.getValue().getClass().getSimpleName().contentEquals("NullNode"))
			{
			
				sb.append("\r\n\t\t"+"<" + "null " +"name="+field.getKey() + "/>");
				
		
		    }
			
			
			
			
			
			
			
			
			
			
			
			if (field.getValue().getClass().getSimpleName().contentEquals("ObjectNode"))
			{
				
				
				JsonFactory factory1 = new JsonFactory();
				ObjectMapper mapper1 = new ObjectMapper(factory1);
				JsonNode rootNode1 = mapper1.readTree(field.getValue().toString());
				sb.append("\r\n\t"+"<"+"object "+"name= "+field.getKey()+">");
				Iterator<Map.Entry<String, JsonNode>> fieldsIterator1 = rootNode1.fields();
				
				while (fieldsIterator1.hasNext()) {

					Map.Entry<String, JsonNode> field1 = fieldsIterator1.next();
					//System.out.println("Key1: " + field1.getKey() +"\t" + field1.getValue().getClass().getSimpleName() + "\tValue:" + field1.getValue());
					
				
					if (field1.getValue().getClass().getSimpleName().contentEquals("TextNode"))
					{
					
						sb.append("\r\n\t\t"+"<" + "string " +"name="+field1.getKey() + ">"+ field1.getValue()+ "</"+"string"+">");
						
				
				    }
					
					if (field1.getValue().getClass().getSimpleName().contentEquals("BooleanNode"))
					{
					
						sb.append("\r\n\t\t"+"<" + "boolean " +"name="+field1.getKey() + ">"+ field1.getValue()+ "</"+"boolean"+">");
				
				
				    }
					
					if (field1.getValue().getClass().getSimpleName().contentEquals("DoubleNode"))
					{
					
						sb.append("\r\n\t\t"+"<" + "number " +"name="+field1.getKey() + ">"+ field1.getValue()+ "</"+"boolean"+">");
						
				
				    }
					
					if (field1.getValue().getClass().getSimpleName().contentEquals("IntNode"))
					{
					
						sb.append("\r\n\t\t"+"<" + "number " +"name="+field1.getKey() + ">"+ field1.getValue()+ "</"+"boolean"+">");
						
				
				    }
					
					if (field1.getValue().getClass().getSimpleName().contentEquals("NullNode"))
					{
					
						sb.append("\r\n\t\t"+"<" + "null " +"name="+field1.getKey() + "/>");
						
				
				    }
				}
				sb.append("\r\n\t"+"</"+"object"+">");
			}
					
			if (field.getValue().getClass().getSimpleName().contentEquals("ArrayNode"))
			{
				
				sb.append("\r\n\t"+"<"+"array "+"name= "+field.getKey()+">");
				
				//System.out.println(field.getValue());

				JsonFactory factory2 = new JsonFactory();
				ObjectMapper mapper2 = new ObjectMapper(factory2);
				JsonNode rootNode2 = mapper2.readTree(field.getValue().toString());
				
				//System.out.println("/n/n/n/n/n/n");
				
				if (rootNode2.isArray())
				{
					for (final JsonNode objNode : rootNode2)
					{
						//System.out.println(objNode);
						//System.out.println(objNode.getClass().getSimpleName());
						
						
						if (objNode.getClass().getSimpleName().contentEquals("TextNode"))
						{
							
							sb.append("\r\n\t\t"+"<" + "string"  + ">"+ objNode+ "</"+"string"+">");
							
							
						}
						
						if (objNode.getClass().getSimpleName().contentEquals("IntNode"))
						{
							
							sb.append("\r\n\t\t"+"<" + "number"  + ">"+ objNode+ "</"+"number"+">");
							
							
						}
						if (objNode.getClass().getSimpleName().contentEquals("DoubleNode"))
						{
							
							sb.append("\r\n\t\t"+"<" + "number"  + ">"+ objNode+ "</"+"number"+">");
						
							
						}
						
						if (objNode.getClass().getSimpleName().contentEquals("NullNode"))
						{
							
							sb.append("\r\n\t\t"+"<" + objNode  + "/>");
						
							
						}
						
						
						if (objNode.getClass().getSimpleName().contentEquals("ObjectNode"))
						{

							JsonFactory factory3 = new JsonFactory();
							ObjectMapper mapper3 = new ObjectMapper(factory3);
							JsonNode rootNode3 = mapper3.readTree(objNode.toString());
							sb.append("\r\n\t\t"+"<"+"object"+">");
							Iterator<Map.Entry<String, JsonNode>> fieldsIterator3 = rootNode3.fields();
							
							while (fieldsIterator3.hasNext()) {

								Map.Entry<String, JsonNode> field3 = fieldsIterator3.next();
								//System.out.println("Key1: " + field3.getKey() +"\t" + field3.getValue().getClass().getSimpleName() + "\tValue:" + field3.getValue());
								
							
								if (field3.getValue().getClass().getSimpleName().contentEquals("TextNode"))
								{
								
									sb.append("\r\n\t\t"+"<" + "string " +"name="+field3.getKey() + ">"+ field3.getValue()+ "</"+"string"+">");
									
							
							    }
								
								if (field3.getValue().getClass().getSimpleName().contentEquals("BooleanNode"))
								{
								
									sb.append("\r\n\t\t"+"<" + "boolean " +"name="+field3.getKey() + ">"+ field3.getValue()+ "</"+"boolean"+">");
							
							
							    }
								
								if (field3.getValue().getClass().getSimpleName().contentEquals("DoubleNode"))
								{
								
									sb.append("\r\n\t\t"+"<" + "number " +"name="+field3.getKey() + ">"+ field3.getValue()+ "</"+"boolean"+">");
									
							
							    }
								
								if (field3.getValue().getClass().getSimpleName().contentEquals("IntNode"))
								{
								
									sb.append("\r\n\t\t"+"<" + "number " +"name="+field3.getKey() + ">"+ field3.getValue()+ "</"+"boolean"+">");
									
							
							    }
								
								if (field3.getValue().getClass().getSimpleName().contentEquals("NullNode"))
								{
								
									sb.append("\r\n\t\t"+"<" + "null " +"name="+field3.getKey() + "/>");
									
							
							    }
							}
							sb.append("\r\n\t\t"+"</"+"object"+">");
						}
						
						if (objNode.getClass().getSimpleName().contentEquals("ArrayNode"))
						{
							JsonFactory factory3 = new JsonFactory();
							ObjectMapper mapper3 = new ObjectMapper(factory3);
							JsonNode rootNode3 = mapper3.readTree(objNode.toString());
							sb.append("\r\n\t\t"+"<"+"array"+">");
							Iterator<Map.Entry<String, JsonNode>> fieldsIterator3 = rootNode3.fields();
							
							
							
							
							
							if (rootNode3.isArray())
							{
								for (final JsonNode objNode1 : rootNode3)
								{
									if (objNode1.getClass().getSimpleName().contentEquals("TextNode"))
									{
										
										sb.append("\r\n\t\t\t"+"<" + "string"  + ">"+ objNode+ "</"+"string"+">");
										
										
									}
									
									if (objNode1.getClass().getSimpleName().contentEquals("IntNode"))
									{
										
										sb.append("\r\n\t\t\t"+"<" + "number"  + ">"+ objNode+ "</"+"number"+">");
										
										
									}
									if (objNode1.getClass().getSimpleName().contentEquals("DoubleNode"))
									{
										
										sb.append("\r\n\t\t\t"+"<" + "number"  + ">"+ objNode+ "</"+"number"+">");
									
										
									}
									
									if (objNode1.getClass().getSimpleName().contentEquals("NullNode"))
									{
										
										sb.append("\r\n\t\t\t"+"<" + objNode  + "/>");
									
										
									}
									
									
									if (objNode1.getClass().getSimpleName().contentEquals("ObjectNode"))
									{

										JsonFactory factory31 = new JsonFactory();
										ObjectMapper mapper31 = new ObjectMapper(factory31);
										JsonNode rootNode31 = mapper31.readTree(objNode1.toString());
										sb.append("\r\n\t\t\t"+"<"+"object"+">");
										Iterator<Map.Entry<String, JsonNode>> fieldsIterator31 = rootNode31.fields();
										
										while (fieldsIterator31.hasNext()) {

											Map.Entry<String, JsonNode> field31 = fieldsIterator31.next();
											//System.out.println("Key1: " + field3.getKey() +"\t" + field3.getValue().getClass().getSimpleName() + "\tValue:" + field3.getValue());
											
										
											if (field31.getValue().getClass().getSimpleName().contentEquals("TextNode"))
											{
											
												sb.append("\r\n\t\t\t"+"<" + "string " +"name="+field31.getKey() + ">"+ field31.getValue()+ "</"+"string"+">");
												
										
										    }
											
											if (field31.getValue().getClass().getSimpleName().contentEquals("BooleanNode"))
											{
											
												sb.append("\r\n\t\t\t"+"<" + "boolean " +"name="+field31.getKey() + ">"+ field31.getValue()+ "</"+"boolean"+">");
										
										
										    }
											
											if (field31.getValue().getClass().getSimpleName().contentEquals("DoubleNode"))
											{
											
												sb.append("\r\n\t\t\t"+"<" + "number " +"name="+field31.getKey() + ">"+ field31.getValue()+ "</"+"boolean"+">");
												
										
										    }
											
											if (field31.getValue().getClass().getSimpleName().contentEquals("IntNode"))
											{
											
												sb.append("\r\n\t\t\t"+"<" + "number " +"name="+field31.getKey() + ">"+ field31.getValue()+ "</"+"boolean"+">");
												
										
										    }
											
											if (field31.getValue().getClass().getSimpleName().contentEquals("NullNode"))
											{
											
												sb.append("\r\n\t\t\t"+"<" + "null " +"name="+field31.getKey() + "/>");
												
										
										    }
										}
										sb.append("\r\n\t\t\t"+"</"+"object"+">");
									
								}
								} 
							
								sb.append("\r\n\t\t\t"+"</"+"array"+">");
						}

						
					}
						
						
				}

					sb.append("\r\n\t\t"+"</"+"array"+">");
			}
			
			
			
			
			
			

	//	System.out.println(sb.toString());
		sb.append("\r\n\t"+"</"+"object"+">");
		
			}
		}
		System.out.println(sb.toString());
		return(sb.toString());
	}
		

		









	




	public static final void XMLJSONConverter(String json, String xml) throws IOException, FileNotFoundException {
		// Todo: Implement this method please.
		String xml1 = "";

		try (FileInputStream inputStream = new FileInputStream(json)) {
			String fileContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			System.out.println(fileContents);
			
		
			
			
		/*	 HashMap<String, Object> map = createHashMapFromJsonString(fileContents);
			
			int sizeofmap = map.size();
			
			for(int i=0; i < sizeofmap; i++) { 	
			
			 
			 if ((map.get(map.keySet().toArray()[i]).toString().contains("{") )|| (map.get(map.keySet().toArray()[i]).toString().contains("[") ))
					 {
			 
				 System.out.println("not absolete object" + map.get(map.keySet().toArray()[i] ));
				 System.out.println(map.get(map.keySet().toArray()[i]).toString());
			}
			 
			 else {
				 System.out.println(" absolete object" + map.get(map.keySet().toArray()[i]));
				 keystack.add((String) map.get(map.keySet().toArray()[i]));
				 System.out.println(map.get(map.keySet().toArray()[i]).toString());
			 }
			 
			 
			}
			System.out.println("keystack" + keystack.toString());
			 
		
			System.out.println ("\n\n\n\n"); ? */
			
			xml1 = travel(fileContents);
			
		
			
			
			OutputStream file = new FileOutputStream(xml);
			
			IOUtils.write(xml1,file,"UTF-8");
			
			
			
			
			
			
			
		}


		catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		ConverterFactory obj = new ConverterFactory();
		XMLJSONConverter(
				"src/main/resources/sample.json", "src/main/resources/xmloutput.xml");
	}
}