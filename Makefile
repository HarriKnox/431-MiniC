JAVAC=javac
JAVA=java


ANTLR_LIB=:../lib/antlr-4.7.1-complete.jar
CLASSPATH=.:../out:../lib/*


JAVA_FILES=main/*.java ast/*.java parser/*.java analyzer/*.java cfg/*.java
ANTLR_FILES=./parser/MiniBaseVisitor.java ./parser/MiniLexer.java ./parser/MiniVisitor.java ./parser/MiniParser.java ./parser/MiniBaseListener.java ./parser/MiniListener.java

all : ../out out/main/Main.class

../out :
	mkdir -p ../out

out/main/Main.class : $(JAVA_FILES)
	$(JAVAC) -cp $(CLASSPATH) -d ../out main/Main.java -Xlint:unchecked

clean:
	-rm -rf ../out

clean-antlr:
	-rm $(ANTLR_FILES) ./parser/MiniLexer.tokens ./parser/Mini.tokens


remake : clean all

antlr : parser/Mini.g4
	$(JAVA) -cp $(CLASSPATH) org.antlr.v4.Tool -visitor parser/Mini.g4
	sed -e 's/\t/   /g' -i $(ANTLR_FILES)


test: ../out
	$(JAVAC) -cp $(CLASSPATH) -d ../out Main.java
