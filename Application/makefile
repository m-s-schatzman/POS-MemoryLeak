JFLAGS = -g -d OutputTree -classpath OutputTree/:OutputTree/View/:OutputTree/Controller/:OutputTree/Model/
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
	Model/PaymentTrans.java \
	Controller/ProcessSaleController.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
