JFLAGS = -g -d OutputTree -classpath OutputTree/:OutputTree/View/:OutputTree/Controller/:OutputTree/Model/
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
        Model/Item.java \
	Model/Inventory.java \
        Model/LineItem.java \
        Model/Sale.java \
        Model/saleTaxCalc.java \
	Model/PaymentTrans.java \
	View/ProcessSaleView.java \
	Controller/ProcessSaleController.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) OutputTree/*.class
