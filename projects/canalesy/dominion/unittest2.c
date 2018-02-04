/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * unittest2: unittest2.c dominion.o rngs.o
 *      gcc -o unittest2 -g  unittest2.c dominion.o rngs.o $(CFLAGS)
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
	int maxHandCount = 5;
	int maxDeckCount = 10;
	int coppers[MAX_HAND];
	int handCount;
	int discardCount;
	int deckCount;
	int fullCount;
	int returnValue;
	int i, p, r;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	for (i = 0; i < MAX_HAND; i++)
	{
		coppers[i] = copper;
	}

	printf ("TESTING fullDeckCount():\n");
	for (p = 0; p < players; p++) {
		for (handCount = 0; handCount < maxHandCount; handCount++) {
			for (discardCount = 0; discardCount < maxDeckCount; discardCount++) {
				for (deckCount = 0; deckCount < maxDeckCount; deckCount++) {
					fullCount = handCount + discardCount + deckCount;

#if (NOISY_TEST == 1)
					printf("Test player %d, handCount %d, discardCount %d, deckCount %d.\n", p, handCount, discardCount, deckCount);
#endif

					memset(&G, 23, sizeof(struct gameState));   // clear the game state
					r = initializeGame(players, k, seed, &G); // initialize a new game

					G.handCount[p] = handCount;
					G.deckCount[p] = deckCount;
					G.discardCount[p] = discardCount;

					memcpy(G.hand[p], coppers, sizeof(int) * (handCount + 1)); // set all the hand cards to copper
					memcpy(G.deck[p], coppers, sizeof(int) * (deckCount + 1)); // set all the deck cards to copper
					memcpy(G.discard[p], coppers, sizeof(int) * (discardCount + 1)); // set all the discard cards to copper

					// Make copy of gameState so we can compare before and after fullDeckCount()
					memcpy (&G2, &G, sizeof(struct gameState));

					returnValue = fullDeckCount(p, copper, &G);

#if (NOISY_TEST == 1)
					printf("result = %d, expected = %d\n", returnValue, fullCount);
#endif
					assert(returnValue == fullCount);

					// Make sure gameState is not altered by the function call
					assert(memcmp(&G, &G2, sizeof(struct gameState)) == 0);
				}
			}
		}
	}

	printf("TEST SUCCESSFULLY COMPLETED!\n");
	return 0;
}