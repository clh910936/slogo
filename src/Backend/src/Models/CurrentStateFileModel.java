package Models;

import BackExternal.ModelManager;
import Commands.UserDefinedCommand;
import Parsing.CommandParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CurrentStateFileModel {
    private List<String> savedFilesList;
    private VariablesModel myVariablesModel;
    private UserDefinedCommandsModel myUserDefinedCommandsModel;
    private ModelManager myModelManager;
    private final String DOCUMENT_PATH = "data/saved_states/";
    private final String USER_DEFINED_COMMAND_TAG = "COMMAND";
    private final String VARIABLE_TAG = "VARIABLE";
    private final String COMMAND_NAME_TAG = "COMNAME";
    private final String COMMAND_VAR_TAG = "COMVAR";
    private final String COMMAND_COMMANDS_TAG = "COMCOMMS";
    private final String VAR_NAME_TAG = "VARNAME";
    private final String VAR_VALUE_TAG = "VALUE";



    public CurrentStateFileModel(VariablesModel vm, UserDefinedCommandsModel userDefinedCommandsModel, ModelManager mm) {
        savedFilesList = new ArrayList<>();
        myVariablesModel = vm;
        myUserDefinedCommandsModel = userDefinedCommandsModel;
        myModelManager = mm;
        savedFilesList = new ArrayList<>();
        File folder = new File(DOCUMENT_PATH);
        File[] listOfFiles = folder.listFiles();
        for(File f : listOfFiles) {
            savedFilesList.add(f.getName());
        }
    }

    public List<String> getSavedFilesList() {
        return savedFilesList;
    }


    public void setStateFromFile(String fileName, String language) {
        try {
            File file = new File(DOCUMENT_PATH + fileName + ".xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            NodeList commandList = document.getElementsByTagName(USER_DEFINED_COMMAND_TAG);
            for (int i = 0; i < commandList.getLength(); i++) {
                Node nNode = commandList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String commandName = eElement.getElementsByTagName(COMMAND_NAME_TAG).item(0).getTextContent();
                    String[] commandVar = eElement.getElementsByTagName(COMMAND_VAR_TAG).item(0).getTextContent().split(CommandParser.WHITESPACE);
                    String commandDefined = eElement.getElementsByTagName(COMMAND_COMMANDS_TAG).item(0).getTextContent();
                    myUserDefinedCommandsModel.addUserCreatedCommand(new UserDefinedCommand(language, myModelManager,commandName, commandDefined, commandVar));
                }
            }


            NodeList variablesList = document.getElementsByTagName(VARIABLE_TAG);
            for (int i = 0; i < variablesList.getLength(); i++) {
                Node variNode = variablesList.item(i);
                if (variNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) variNode;
                    String variableName = eElement.getElementsByTagName(VAR_NAME_TAG).item(0).getTextContent();
                    String variableValue = eElement.getElementsByTagName(VAR_VALUE_TAG).item(0).getTextContent();
                    myVariablesModel.addVariable(variableName,variableValue);
                }
            }
        }
        catch (Exception e) {
           //TODO: HANDLE
        }
    }



    public void save(String fileName) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("CONFIGURATION");
            doc.appendChild(rootElement);

            Map<String,String> variables = myVariablesModel.getVariables();
            for(Map.Entry<String,String> variable : variables.entrySet()) {
                Element savedVariable = doc.createElement(VARIABLE_TAG);
                addChildElement(doc, VAR_NAME_TAG, variable.getKey(), savedVariable);
                addChildElement(doc, VAR_VALUE_TAG, variable.getValue(), savedVariable);
                rootElement.appendChild(savedVariable);
            }


            Map<String, UserDefinedCommand> commands = myUserDefinedCommandsModel.getUserCreatedCommands();
            for(Map.Entry<String, UserDefinedCommand> command : commands.entrySet()) {
                Element savedCommand = doc.createElement(USER_DEFINED_COMMAND_TAG);
                String commandName = command.getKey();
                addChildElement(doc, COMMAND_NAME_TAG, commandName, savedCommand);
                String commandVar = command.getValue().getVariablesToString();
                addChildElement(doc, COMMAND_VAR_TAG, commandVar,savedCommand);
                String commandsDefined =command.getValue().getCommands();
                addChildElement(doc, COMMAND_COMMANDS_TAG, commandsDefined,savedCommand);
                rootElement.appendChild(savedCommand);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(DOCUMENT_PATH+fileName+".xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            System.out.println(pce);

        } catch (TransformerException tfe) {
            System.out.println(tfe);
            }
    }
    private void addChildElement(Document doc, String rootElement, String childElement, Element mainRoot) {
        Element root = doc.createElement(rootElement);
        root.appendChild(doc.createTextNode(childElement));
        mainRoot.appendChild(root);
    }






}
