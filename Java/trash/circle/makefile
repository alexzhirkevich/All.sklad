# makefile z4_2

.SUFFIXES: .class .java

PACK=circle
TARG=Test
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=$(PACK)\Circle.class \
    $(PACK)\Test.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: $(OBJ)
	$(JR) $(TARG).jar $(PACK).Test $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

$(PACK)\Circle.class: $(PACK)\Circle.java makefile

$(PACK)\Test.class: $(PACK)\Test.java makefile
# eof makefile z4_2
