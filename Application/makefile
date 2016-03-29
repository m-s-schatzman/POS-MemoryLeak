JFLAGS = -g -d Output -classpath ./:Output/:Model/:View/:Controller/
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java


CLASSES = \
	Model/User.java \
	Model/Database.java \
    Model/Item.java \
    Model/LineItem.java \
    Model/Sale.java \
    Model/TaxCalculator.java \
	Model/PaymentAuthorizer.java \
	Model/PersistentStorage.java \
	View/ProcessSaleView.java \
	View/LoginView.java \
	View/ManageUserView.java \
	View/POSView.java \
	Controller/ProcessSaleController.java \
	Controller/LoginController.java \
	Controller/ManageUserController.java \
	Controller/POSController.java \
	Main.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) Output/*.class
