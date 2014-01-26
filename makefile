JCC=javac

all:
	$(JCC) -d ./bin ./src/*.java

clean:
	rm -f ./bin/*.class