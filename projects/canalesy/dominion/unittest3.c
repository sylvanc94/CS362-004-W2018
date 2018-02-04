/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * unittest3: unittest3.c dominion.o rngs.o
 *      gcc -o unittest3 -g  unittest3.c dominion.o rngs.o $(CFLAGS)
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
	int handPos = 0;
	int maxHandCount = 5;
	int trashFlag;
	int discarded;
	int last;
	int preCount;
	int postCount;
	int pass = 1;
	int p = 0;
	int i;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING discardCard():\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	// Test trash vs. played
	trashFlag = 1;	// do not add to played pile
	discardCard(handPos, p, &G, trashFlag);

#if (NOISY_TEST == 1)
	printf("Testing trashFlag = 1.\n");
#endif

	assert(G.playedCardCount == G2.playedCardCount);

	trashFlag = 0;
	handPos = 0;

	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	for (i = 0; i < maxHandCount - 1; i++) {

#if (NOISY_TEST == 1)
		printf("Testing trashFlag %d, handCount %d, handPos %d.\n", trashFlag, G.handCount[p], handPos);
#endif
		// Test else branch, where card is not last in array, and not only card left
		discarded = G.hand[p][handPos];
		last = G.hand[p][G.handCount[p] - 1];
		preCount = G.handCount[p];
		discardCard(handPos, p, &G, trashFlag);

		postCount = G.handCount[p];

		assert(G.hand[p][0] == last);
		assert(postCount = preCount - 1);
	}


#if (NOISY_TEST == 1)
	printf("Testing trashFlag %d, handCount %d, handPos %d.\n", trashFlag, G.handCount[p], handPos);
#endif

	// Test when last card is discarded (also the last position in the array)
	discardCard(handPos, p, &G, trashFlag);

	assert(G.handCount[p] == 0);
	assert(G.playedCardCount == 5);
	
	// Check the gameState against the copy of the original to verify 2nd player is unchanged
	p = 1;
	assert(G.handCount[p] == G2.handCount[p]);
	assert(memcmp(&G.deck[p], &G2.deck[p], MAX_DECK * sizeof(int)) == 0);
	assert(memcmp(&G.hand[p], &G2.hand[p], MAX_HAND * sizeof(int)) == 0);
	assert(memcmp(&G.discard[p], &G2.discard[p], MAX_DECK * sizeof(int)) == 0);

	for (i = 0; i < maxHandCount; i++) {
		assert(G.hand[p][i] == G.hand[p][i]);

	}

	if (pass) {
		printf("TEST SUCCESSFULLY COMPLETED!\n");
	} else {
		printf("TEST FAILED!\n");
	}
	return 0;
}