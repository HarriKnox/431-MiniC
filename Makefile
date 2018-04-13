JAVAC=javac
JAVA=java


ANTLR_LIB=:../lib/antlr-4.7.1-complete.jar
CLASSPATH=.:../out:../lib/*


JAVA_FILES=main/*.java ast/*.java parser/*.java analyzer/*.java


all : ../out out/main/Main.class

../out :
	mkdir -p ../out

out/main/Main.class : $(JAVA_FILES)
	$(JAVAC) -cp $(CLASSPATH) -d ../out main/Main.java

clean:
	-rm -rf ../out

remake : clean all

antlr : parser/Mini.g4
	$(JAVA) -cp $(CLASSPATH) org.antlr.v4.Tool -visitor parser/Mini.g4
