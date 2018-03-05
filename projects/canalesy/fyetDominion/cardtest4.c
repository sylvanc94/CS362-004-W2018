/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * cardtest3: cardtest4.c dominion.o rngs.o
 *      gcc -o cardtest4 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)
 * -----------------------------------------------------------------------
 */

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>

// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 0

int main()
{
	int seed = 10;
	int players = 2;
	int p = 0;
	int handPos = 0;
	int bonus = 0;
	int choice1 = 0;
	int choice2 = 0;
	int choice3 = 0;
	int card = council_room;
	int playedCard;
	int pass = 1;
	int r;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, cutpurse, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING Council Room:\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));


#if (NOISY_TEST == 1)
	printf("Testing PRE. player 0 handCount %d, player 1 handCount %d, numBuys %d\n", G.handCount[0], G.handCount[1], G.numBuys);
#endif
	handPos = G.handCount[p] - 1;	// Act as if the last card in hand is the Council Room
	playedCard = G.hand[0][handPos];
	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

#if (NOISY_TEST == 1)
	printf("Testing POST. player 0 handCount %d, player 1 handCount %d, numBuys %d\n", G.handCount[0], G.handCount[1], G.numBuys);
#endif

	assert(r == 0);
	assert(G.numBuys == G2.numBuys + 1);
	assert(G.deckCount[0] == G2.deckCount[0] - 4);	
	assert(G.handCount[0] == G2.handCount[0] + 3);	// Player gains 4 and plays 1 for net gain of 3
	assert(G.handCount[1] == G2.handCount[1] + 1);	// Other player gains a card
	assert(G.deckCount[1] == G2.deckCount[1] - 1); 
	assert(G.playedCardCount == G2.playedCardCount + 1);	// Check a card was added to played pile
	assert(G.playedCards[G.playedCardCount - 1] == playedCard);	// Check that the played pile addition was the one that was played

	
	// Make sure unrelated state is unchanged
	p = 1;
	assert(G.discardCount[p] == G2.discardCount[p]);
	assert(memcmp(&G.discard[p], &G2.discard[p], MAX_DECK * sizeof(int)) == 0);
	

	if (pass) {
		printf("TEST SUCCESSFULLY COMPLETED!\n");
	} else {
		printf("TEST FAILED!\n");
	}
	return 0;
}