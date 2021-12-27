# A simple Makefile
main: scooter.o main.o
	gcc -lm scooter.o main.o -o main
	
scooter.o: scooter.s
	gcc -lm -g -Wall -c scooter.s -o scooter.o 
	
main.o: main.c
	gcc -lm -g -Wall -c main.c -o main.o
	
clean:
	rm -f *.o main
	
run: main
	./main