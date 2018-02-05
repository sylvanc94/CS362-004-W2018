/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * cardtest2: cardtest2.c dominion.o rngs.o
 *      gcc -o cardtest2 -g  cardtest2.c dominion.o rngs.o $(CFLAGS)
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
	int card = smithy;
	int pass = 1;
	int playedCard;
	int r;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING Smithy:\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

#if (NOISY_TEST == 1)
	printf("Testing PRE. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif
	playedCard = G.hand[0][handPos];
	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

#if (NOISY_TEST == 1)
	printf("Testing POST. deckCount %d, handCount %d, discardCount %d\n", G.deckCount[p], G.handCount[p], G.discardCount[p]);
#endif

	assert(r == 0);
	assert(G.hand[p][handPos] != smithy);
	assert(G.deckCount[p] == G2.deckCount[p] - 3); // Player lost 3 cards from deck
	assert(G.handCount[p] == G2.handCount[p] + 2); // Player gained 3 cards and discarded one

	// Check that card was added to the played pile
	if (!(G.playedCardCount == G2.playedCardCount + 1)) {
		printf("Assertion failed: (G.playedCardCount == G2.playedCardCount + 1)\n");
		pass = 0;
	}

	// Check that the played pile addition was the one that was played
	if (!(G.playedCards[G.playedCardCount - 1] == playedCard)) {
		printf("Assertion failed: (G.playedCards[G.playedCardCount - 1] == playedCard)\n");
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