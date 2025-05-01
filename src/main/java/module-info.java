module com.istealmoney.inserttyping.inserttyping {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jdk.accessibility;

    opens com.istealmoney.inserttyping.inserttyping to javafx.fxml;
    exports com.istealmoney.inserttyping.inserttyping;
}