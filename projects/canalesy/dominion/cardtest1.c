/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * cardtest1: cardtest1.c dominion.o rngs.o
 *      gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o $(CFLAGS)
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
	int choice1 = 0;	// Adventurer doesn't use card choices
	int choice2 = 0;
	int choice3 = 0;
	int handPos = 0;
	int bonus = 0;
	int pass = 1;
	int card = adventurer;
	int r;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING Adventurer:\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Force deck count to 0, to exercise the shuffle conditional branch
	// and copy deck cards to the discard
	memcpy(G.discard[p], G.deck[p], G.deckCount[p] * sizeof(int));
	G.discardCount[p] = G.deckCount[p];
	G.deckCount[p] = 0;

	// Make copy of gameState so we can compare before and after
	memcpy(&G2, &G, sizeof(struct gameState));

#if (NOISY_TEST == 1)
	printf("Testing. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif

	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

	assert(r == 0);

	// gameState should be altered by the shuffling
	assert(memcmp(&G, &G2, sizeof(struct gameState)) != 0);



	// Start a new test where the first two cards drawn should be treasure cards
	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	p = 0;
	handPos = 0;
	G.deck[p][G.deckCount[p] - 1] = gold;
	G.deck[p][G.deckCount[p] - 2] = gold;

#if (NOISY_TEST == 1)
	printf("Testing first two treasure cards PRE. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif

	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

	assert(r == 0);
	assert(G.deckCount[p] == G2.deckCount[p] - 2);
	assert(G.handCount[p] == G2.handCount[p] + 2);
	assert(G.discardCount[p] == G2.discardCount[p]);

	// Make sure state is unchanged for other player
	p = 1;
	assert(G.deckCount[p] == G2.deckCount[p]);
	assert(G.handCount[p] == G2.handCount[p]);
	assert(G.discardCount[p] == G2.discardCount[p]);


	// Start a new test where the first two cards drawn are NOT treasure cards
	// But the next two are. So 4 cards should get drawn total, with the 
	// first two being discarded since they are not treasure cards.

	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	p = 0;
	handPos = 0;
	G.deck[p][G.deckCount[p] - 1] = village;
	G.deck[p][G.deckCount[p] - 2] = gardens;
	G.deck[p][G.deckCount[p] - 3] = gold;
	G.deck[p][G.deckCount[p] - 4] = gold;

#if (NOISY_TEST == 1)
	printf("Testing first two not treasure cards PRE. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif

	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

#if (NOISY_TEST == 1)
	printf("Testing first two not treasure cards POST. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif

	assert(r == 0);
	//assert(G.deckCount[p] == G2.deckCount[p] - 4);
	if (!(G.deckCount[p] == G2.deckCount[p] - 4)) {
		printf("Assertion failed: (G.deckCount[p] == G2.deckCount[p] - 4)\n");
		pass = 0;
	}
	assert(G.handCount[p] == G2.handCount[p] + 2);
	if (!(G.discardCount[p] == G2.discardCount[p] + 2)) {
		printf("Assertion failed: (G.discardCount[p] == G2.discardCount[p] + 2)\n");
		pass = 0;
	}

	// Make sure state is unchanged for other player
	p = 1;
	assert(G.deckCount[p] == G2.deckCount[p]);
	assert(G.handCount[p] == G2.handCount[p]);
	assert(G.discardCount[p] == G2.discardCount[p]);
	assert(memcmp(&G.deck[p], &G2.deck[p], MAX_DECK * sizeof(int)) == 0);
	assert(memcmp(&G.hand[p], &G2.hand[p], MAX_HAND * sizeof(int)) == 0);
	assert(memcmp(&G.discard[p], &G2.discard[p], MAX_DECK * sizeof(int)) == 0);


	if (pass) {
		printf("TEST SUCCESSFULLY COMPLETED!\n");
	} else {
		printf("TEST FAILED!\n");
	}
	return 0;
}