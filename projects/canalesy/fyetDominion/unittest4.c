/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * unittest4: unittest4.c dominion.o rngs.o
 *      gcc -o unittest4 -g  unittest4.c dominion.o rngs.o $(CFLAGS)
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
	int card;
	int preCount;
	int postCount;
	int toFlag;
	int p = 0;
	int pass = 1;
	int r;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING gainCard():\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	// toFlag = 0 : add to discard
	// toFlag = 1 : add to deck
	// toFlag = 2 : add to hand

	// Test for requesting a card that isn't available
	card = tribute;
#if (NOISY_TEST == 1)
	printf("Testing card not in supply. %d.\n", G.supplyCount[card]);
#endif
	
	toFlag = 1;
	r = gainCard(card, &G, toFlag, p);
	assert(r == -1);

	// Test toFlag = 0 with an available card
	toFlag = 0;
#if (NOISY_TEST == 1)
	printf("Testing toFlag %d.\n", toFlag);
#endif
	
	card = adventurer;
	preCount = G.discardCount[p];
	r = gainCard(card, &G, toFlag, p);
	postCount = G.discardCount[p];

	assert(r == 0);
	assert(postCount = preCount + 1);
	assert(G.discard[p][postCount - 1] == adventurer);

#if (NOISY_TEST == 1)
	printf("Comparing adventurer supply. G %d, G2 %d.\n", G.supplyCount[adventurer], G2.supplyCount[adventurer]);
#endif
	// Test supply count is being decremented properly
	assert(G.supplyCount[adventurer] == G2.supplyCount[adventurer] - 1);

	// Test toFlag = 1 with an available card
	toFlag = 1;
#if (NOISY_TEST == 1)
	printf("Testing toFlag %d.\n", toFlag);
#endif
	
	preCount = G.deckCount[p];
	r = gainCard(card, &G, toFlag, p);
	postCount = G.deckCount[p];

	assert(r == 0);
	assert(postCount = preCount + 1);
	assert(G.deck[p][postCount - 1] == adventurer);

	// Test toFlag = 2 with an available card
	toFlag = 2;
#if (NOISY_TEST == 1)
	printf("Testing toFlag %d.\n", toFlag);
#endif
	
	preCount = G.handCount[p];
	r = gainCard(card, &G, toFlag, p);
	postCount = G.handCount[p];

	assert(r == 0);
	assert(postCount = preCount + 1);
	assert(G.hand[p][postCount - 1] == adventurer);


#if (NOISY_TEST == 1)
	printf("Testing other player state.\n");
#endif

	p = 1;
	assert(G.handCount[p] == G2.handCount[p]);
	assert(G.deckCount[p] == G2.deckCount[p]);
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
