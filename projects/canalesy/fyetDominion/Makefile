CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

randomtestadventurer: randomtestadventurer.c dominion.o rngs.o
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o $(CFLAGS)

randomtestcard1: randomtestcard1.c dominion.o rngs.o
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)

randomtestcard2: randomtestcard2.c dominion.o rngs.o
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

unittest1: unittest1.c dominion.o
	gcc -o unittest1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)

unittest2: unittest2.c dominion.o
	gcc -o unittest2 -g  unittest2.c dominion.o rngs.o $(CFLAGS)

unittest3: unittest3.c dominion.o
	gcc -o unittest3 -g  unittest3.c dominion.o rngs.o $(CFLAGS)

unittest4: unittest4.c dominion.o
	gcc -o unittest4 -g  unittest4.c dominion.o rngs.o $(CFLAGS)

cardtest1: cardtest1.c dominion.o
	gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o $(CFLAGS)

cardtest2: cardtest2.c dominion.o
	gcc -o cardtest2 -g  cardtest2.c dominion.o rngs.o $(CFLAGS)

cardtest3: cardtest3.c dominion.o
	gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)

cardtest4: cardtest4.c dominion.o
	gcc -o cardtest4 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

unittestresults.out: unittest1.c unittest2.c unittest3.c unittest4.c cardtest1.c cardtest2.c cardtest3.c cardtest4.c dominion.o
	gcc -o unittest1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)
	gcc -o unittest2 -g  unittest2.c dominion.o rngs.o $(CFLAGS)
	gcc -o unittest3 -g  unittest3.c dominion.o rngs.o $(CFLAGS)
	gcc -o unittest4 -g  unittest4.c dominion.o rngs.o $(CFLAGS)
	gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o $(CFLAGS)
	gcc -o cardtest2 -g  cardtest2.c dominion.o rngs.o $(CFLAGS)
	gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
	gcc -o cardtest4 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)
	@echo ------ Executing Unit Tests ------
	./unittest1 > unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./unittest2 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./unittest3 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./unittest4 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./cardtest1 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./cardtest2 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./cardtest3 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	./cardtest4 >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	gcov -abcfu dominion.c >> unittestresults.out
	@echo ------------------------------ >> unittestresults.out
	cat dominion.c.gcov >> unittestresults.out
	@echo ------ Tests Complete ------

randomtestcard1.out: clean randomtestcard1.c dominion.o
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)
	@echo ------ randomtestcard1 ------
	./randomtestcard1 > randomtestcard1.out
	@echo ------------------------------ >> randomtestcard1.out
	gcov -abcfu dominion.c >> randomtestcard1.out
	@echo ------------------------------ >> randomtestcard1.out
	gcov -abcfu randomtestcard1.c >> randomtestcard1.out
	@echo ------------------------------ >> randomtestcard1.out
	cat dominion.c.gcov >> randomtestcard1.out
	@echo ------------------------------ >> randomtestcard1.out
	cat randomtestcard1.c.gcov >> randomtestcard1.out

randomtestcard2.out: clean randomtestcard2.c dominion.o
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)
	@echo ------ randomtestcard2 ------
	./randomtestcard2 > randomtestcard2.out
	@echo ------------------------------ >> randomtestcard2.out
	gcov -abcfu dominion.c >> randomtestcard2.out
	@echo ------------------------------ >> randomtestcard2.out
	gcov -abcfu randomtestcard2.c >> randomtestcard2.out
	@echo ------------------------------ >> randomtestcard2.out
	cat dominion.c.gcov >> randomtestcard2.out
	@echo ------------------------------ >> randomtestcard2.out
	cat randomtestcard2.c.gcov >> randomtestcard2.out

randomtestadventurer.out: clean randomtestadventurer.c dominion.o
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o rngs.o $(CFLAGS)
	@echo ------ randomtestadventurer ------
	./randomtestadventurer > randomtestadventurer.out
	@echo ------------------------------ >> randomtestadventurer.out
	gcov -abcfu dominion.c >> randomtestadventurer.out
	@echo ------------------------------ >> randomtestadventurer.out
	gcov -abcfu randomtestadventurer.c >> randomtestadventurer.out
	@echo ------------------------------ >> randomtestadventurer.out
	cat dominion.c.gcov >> randomtestadventurer.out
	@echo ------------------------------ >> randomtestadventurer.out
	cat randomtestadventurer.c.gcov >> randomtestadventurer.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player testDrawCard testBuyCard badTestDrawCard

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe
	rm -f unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4
	rm -f randomtestcard1 randomtestcard2 randomtestadventurer
	rm -rf *.dSYM
