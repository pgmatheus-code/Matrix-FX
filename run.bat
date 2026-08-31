@echo off
set FX="%~dp0lib"

java --module-path %FX% ^
     --add-modules javafx.controls,javafx.fxml ^
     --enable-native-access=javafx.graphics ^
     -jar "%~dp0MatrixFX.jar"

pause
