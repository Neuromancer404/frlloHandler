module com.example.frllohandler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.frllohandler to javafx.fxml;
    exports com.example.frllohandler;
    exports com.example.frllohandler.JsonHandler;
    opens com.example.frllohandler.JsonHandler to javafx.fxml;
    exports com.example.frllohandler.FrlloTools;
    opens com.example.frllohandler.FrlloTools to javafx.fxml;
    exports com.example.frllohandler.Tools;
    opens com.example.frllohandler.Tools to javafx.fxml;
}