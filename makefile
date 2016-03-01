CC=javac
FLAGS=-g
SRC=$(wildcard *.java)
TARGETS=$(SRC:.java=.class)
OUT=MyKruskal
ARGS=in.txt
CLASSPATH=.:lib/algs4.jar

all: $(TARGETS)

%.class: %.java
	$(CC) -cp $(CLASSPATH) $(FLAGS) $<

run:
	java -classpath $(CLASSPATH) -ea $(OUT) $(ARGS)

debug:$(TARGETS)
	jdb MinMax

clean:
	rm *.class
