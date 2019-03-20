package Models;

import BackExternal.IllegalSavedStateFileException;
import BackExternal.ModelManager;
import Commands.UserDefinedCommand;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static java.util.Map.entry;

/**
 * @author christinachen
 * This class is used to for saving and setting the state of the program using XML files
 * Depends on the UserDefinedCommandsModel, VariablesModel, and ModelManager
 */

public class CurrentStateFileModel {
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
        myVariablesModel = vm;
        myUserDefinedCommandsModel = userDefinedCommandsModel;
        myModelManager = mm;
    }

    /**
     * This is to be used so that the front end can get all of the existing files to display to the user
     * @return list of all of the saved files
     */
    public List<String> getSavedFilesList() {
        File folder = new File(DOCUMENT_PATH);
        File[] listOfFiles = folder.listFiles();
        return Arrays.asList(listOfFiles).stream().map(File::getName).collect(Collectors.toList());
    }

    /**
     * This sets the state in the variables model and user defined command model to be the state read in from a file
     * @param fileName
     */
    public void setStateFromFile(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(DOCUMENT_PATH + fileName);
            Document document = db.parse(file);


            readTag(document, USER_DEFINED_COMMAND_TAG, eElement -> {
                String commandName = eElement.getElementsByTagName(COMMAND_NAME_TAG).item(0).getTextContent();
                String[] commandVar = eElement.getElementsByTagName(COMMAND_VAR_TAG).item(0).getTextContent().split(" ");
                String commandDefined = eElement.getElementsByTagName(COMMAND_COMMANDS_TAG).item(0).getTextContent();
                myUserDefinedCommandsModel.addUserCreatedCommand(new UserDefinedCommand(myModelManager,commandName, commandDefined, commandVar));
            });

            readTag(document, VARIABLE_TAG, eElement -> {
                String variableName = eElement.getElementsByTagName(VAR_NAME_TAG).item(0).getTextContent();
                String variableValue = eElement.getElementsByTagName(VAR_VALUE_TAG).item(0).getTextContent();
                myVariablesModel.addVariable(variableName,variableValue);});
        }
        catch (Exception e) {
            throw new IllegalSavedStateFileException();
        }
    }

    private void readTag(Document document, String tagName, Consumer<Element> action) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        for(int i = 0 ;i<nodeList.getLength();i++) {
            Node node = nodeList.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE) {
                System.out.println("TEXT: " + node.getTextContent());
                action.accept((Element) node);
            }

        }
    }


    /**
     * Used to save the current state into an XML file
     * @param fileName
     */
    public void saveStateIntoFile(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("CONFIGURATION");
            doc.appendChild(rootElement);

            Map<String,String> variablesDataMap = myVariablesModel.getVariables();
            Function<String, Map<String,String>> variableTagObjects = variable -> {
                return Map.ofEntries(
                        entry(VAR_NAME_TAG, variable),
                        entry(VAR_VALUE_TAG, variablesDataMap.get(variable))
                );
            };
            saveElementType(variablesDataMap, variable -> addChildElements(doc,variableTagObjects.apply(variable),VARIABLE_TAG,rootElement));

            Map<String, UserDefinedCommand> commandsDataMap = myUserDefinedCommandsModel.getUserCreatedCommands();


            Function<UserDefinedCommand, Map<String,String>> commandTagObjects = command -> {
                return Map.ofEntries(
                        entry(COMMAND_NAME_TAG, command.getCommandName()),
                        entry(COMMAND_VAR_TAG, command.getVariablesToString()),
                        entry(COMMAND_COMMANDS_TAG, command.getCommands())
                );
            };
            saveElementType(commandsDataMap, command -> addChildElements(doc,commandTagObjects.apply(commandsDataMap.get(command)),USER_DEFINED_COMMAND_TAG,rootElement));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(DOCUMENT_PATH+fileName+".xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        } catch (Exception tfe) {
            // Should not ever happen - Exception required to be caught by Java
            System.out.println(tfe);
        }
    }

    private void saveElementType(Map<String,?> dataMap, Consumer<String> addChildren) {
        dataMap.keySet().stream().forEach(key -> {
            addChildren.accept(key);
        });
    }

    private void addChildElements(Document doc, Map<String,String> tagToDataMap, String rootTag, Element fileRoot) {
        Element root = doc.createElement(rootTag);
        tagToDataMap.keySet().stream().forEach(tag-> {
            Element tagElement = doc.createElement(tag);
            tagElement.appendChild(doc.createTextNode(tagToDataMap.get(tag)));
            root.appendChild(tagElement);
        });
        fileRoot.appendChild(root);
    }






}
