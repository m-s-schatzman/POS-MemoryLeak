JFLAGS = -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
	Main.java \
	Misc/DBDriver.java
	

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) Output/*.class
