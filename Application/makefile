JFLAGS = -g -d Output -classpath Output/
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
	Model/User.java \
        Model/Item.java \
        Model/Database.java \
        Model/LineItem.java \
        Model/Sale.java \
        Model/SaleTaxCalc.java \
	Model/PaymentTrans.java \
	View/ProcessSaleView.java \
	View/LoginView.java \
	Controller/ProcessSaleController.java \
	Controller/LoginController.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) Output/*.class
