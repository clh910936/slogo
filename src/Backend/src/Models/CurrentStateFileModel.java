package Models;

import BackExternal.IllegalSavedStateFileException;
import BackExternal.ModelManager;
import Commands.UserDefinedCommand;
import Parsing.CommandParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static java.util.Map.entry;

public class CurrentStateFileModel {
    private VariablesModel myVariablesModel;
    private UserDefinedCommandsModel myUserDefinedCommandsModel;
    private ModelManager myModelManager;
    private DocumentBuilder db;
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
        getDocumentBuilder();
    }

    private void getDocumentBuilder() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            // Should not ever happen - Exception required to be caught by Java
            System.out.println(e);
        }

    }

    public List<String> getSavedFilesList() {
        File folder = new File(DOCUMENT_PATH);
        File[] listOfFiles = folder.listFiles();
        return Arrays.asList(listOfFiles).stream().map(File::getName).collect(Collectors.toList());
    }

    public void setStateFromFile(String fileName, String language) {
        try {
            File file = new File(DOCUMENT_PATH + fileName + ".xml");
            Document document = db.parse(file);

            readTag(document, USER_DEFINED_COMMAND_TAG, eElement -> {
                String commandName = eElement.getElementsByTagName(COMMAND_NAME_TAG).item(0).getTextContent();
                String[] commandVar = eElement.getElementsByTagName(COMMAND_VAR_TAG).item(0).getTextContent().split(CommandParser.WHITESPACE);
                String commandDefined = eElement.getElementsByTagName(COMMAND_COMMANDS_TAG).item(0).getTextContent();
                myUserDefinedCommandsModel.addUserCreatedCommand(new UserDefinedCommand(language, myModelManager,commandName, commandDefined, commandVar));
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
        List<Node> nodeList = (ArrayList) document.getElementsByTagName(tagName);
        nodeList.stream().filter(node -> node.getNodeType()==Node.ELEMENT_NODE).map(node -> (Element) node).forEach(action);

    }


    public void saveStateIntoFile(String fileName) {
        try {
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("CONFIGURATION");
            doc.appendChild(rootElement);

            Element savedVariable = doc.createElement(VARIABLE_TAG);
            Map<String,String> variablesDataMap = myVariablesModel.getVariables();
            final Function<String, Map<String,String>> variableTagObjects = variable -> {
                return Map.ofEntries(
                        entry(VAR_NAME_TAG, variable),
                        entry(VAR_VALUE_TAG, variablesDataMap.get(variable))
                );
            };
            saveElementType(variablesDataMap, variable -> addChildElements(doc,variableTagObjects.apply(variable),savedVariable));

            Element savedCommand = doc.createElement(USER_DEFINED_COMMAND_TAG);
            Map<String, UserDefinedCommand> commandsDataMap = myUserDefinedCommandsModel.getUserCreatedCommands();
            final Function<UserDefinedCommand, Map<String,String>> commandTagObjects = command -> {
                return Map.ofEntries(
                        entry(COMMAND_NAME_TAG, command.getCommandName()),
                        entry(COMMAND_VAR_TAG, command.getVariablesToString()),
                        entry(COMMAND_COMMANDS_TAG, command.getCommands())
                );
            };
            saveElementType(commandsDataMap, command -> addChildElements(doc,commandTagObjects.apply(commandsDataMap.get(command)),savedCommand));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(DOCUMENT_PATH+fileName+".xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            // Should not ever happen - Exception required to be caught by Java
            System.out.println(tfe);
            }
    }

    private void saveElementType(Map<String,?> dataMap, Consumer<String> addChildren) {
        dataMap.keySet().stream().forEach(key -> {
            addChildren.accept(key);
        });
    }

    private void addChildElements(Document doc, Map<String,String> tagToDataMap, Element mainRoot) {
        tagToDataMap.keySet().stream().forEach(tag-> {
            Element root = doc.createElement(tag);
            root.appendChild(doc.createTextNode(tagToDataMap.get(tag)));
            mainRoot.appendChild(root);
                }

        );
    }






}
