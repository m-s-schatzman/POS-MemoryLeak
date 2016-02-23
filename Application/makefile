JFLAGS = -g -classpath ./OutputTree -d ./OutputTree
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
        Model/Inventory.java \
        Model/Item.java \
        Model/LineItem.java \
        Model/Sale.java \
        Model/saleTaxCalc.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
