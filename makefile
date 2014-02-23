JCC=javac
JAR=jar

all:
	$(JCC) -d ./bin ./src/*.java

jar:
	$(JCC) -d . ./src/*.java
	$(JAR) -cvfm ./bin/CGoL.jar ./src/manifest.mf *.class
	rm -f ./*.class
	echo lel

clean:
	rm -f ./bin/*.class