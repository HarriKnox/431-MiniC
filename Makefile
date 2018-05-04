JAVAC := javac
JAVA  := java


CLASSPATH := .:../lib/antlr-4.7.1-complete.jar


JAVA_FILES := $(shell find -name "*.java")

ANTLR_JAVA_FILES := ./parser/*.java
ANTLR_FILES      := $(ANTLR_JAVA_FILES) ./parser/*.tokens ./parser/*.interp


all : ../out ../out/Main.class


../out :
	mkdir -p ../out


../out/Main.class : $(JAVA_FILES)
	$(JAVAC) -cp $(CLASSPATH) -d ../out Main.java -Xlint:unchecked


antlr :
	-rm $(ANTLR_FILES) 2>/dev/null
	$(JAVA) -cp $(CLASSPATH) org.antlr.v4.Tool -visitor parser/Mini.g4
	rm parser/MiniBaseListener.java
	sed -e 's/\t/   /g' -i $(ANTLR_JAVA_FILES)


clean:
	-rm -rf ../out 2>/dev/null


remake : clean all
