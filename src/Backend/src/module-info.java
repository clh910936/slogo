module Backend {
    requires Controller;
    requires jdk.xml.dom;
    requires java.xml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.web;
    exports BackExternal;
}