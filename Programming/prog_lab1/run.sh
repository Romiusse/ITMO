javac -d ./bin -cp libs/Pokemon.jar -sourcepath src src/s367385/lab1/Main.java
cd bin
jar cfm ../Main.jar ../manifest.mf *
cd ..
java -jar Main.jar
