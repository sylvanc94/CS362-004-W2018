/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * cardtest3: cardtest3.c dominion.o rngs.o
 *      gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
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
	int card = cutpurse;
	int count_pre;
	int count_post;
	int pass = 1;
	int r, i;
	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, cutpurse, baron, great_hall};
	struct gameState G;
	struct gameState G2;

	printf ("TESTING Cutpurse:\n");

	initializeGame(players, k, seed, &G); // initialize a new game

	// Need to give other player some cards in the hand, since initializeGame only does Player 0
	for (i = 0; i < 5; i++) {
		drawCard(1, &G);
	}

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	// Get the count of coppers for player 2
	count_pre = 0;
	for (i = 0; i < G.handCount[1]; i++) {
		if (G.hand[1][i] == copper) count_pre++;
	}


#if (NOISY_TEST == 1)
	printf("Testing PRE. player %d, coins %d, other player coppers %d\n", p, G.coins, count_pre);
#endif

	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

	// Get the count of coppers for player 2
	count_post = 0;
	for (i = 0; i < G.handCount[1]; i++) {
		if (G.hand[1][i] == copper) count_post++;
	}

#if (NOISY_TEST == 1)
	printf("Testing POST. player %d, coins %d, other player coppers %d\n", p, G.coins, count_post);
#endif

	assert(r == 0);
	assert(G.coins == G2.coins + 2);	// Player 0 should have 2 more coins

	// Check that player 1 has 1 less copper
	if (count_pre > 0) {
		if (!(count_post == count_pre - 1)) {
			printf("Assertion failed: (count_post == count_pre - 1)\n");
			pass = 0;
		}
	}

	// Test a new scenario where player 1 has no coppers in the hand
	// This is supposed to reach the conditional branch that reveals
	// the cards in the hand.
	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	initializeGame(players, k, seed, &G); // initialize a new game

	// Need to give other player some cards in the hand, since initializeGame only does Player 0
	for (i = 0; i < 5; i++) {
		drawCard(1, &G);
		if (G.hand[1][i] == copper) {
			G.hand[1][i] = gold;
		}
	}

	// Make copy of gameState so we can compare before and after
	memcpy (&G2, &G, sizeof(struct gameState));

	// Get the count of coppers for player 2
	count_pre = 0;
	for (i = 0; i < G.handCount[1]; i++) {
		if (G.hand[1][i] == copper) count_pre++;
	}

#if (NOISY_TEST == 1)
	printf("Testing PRE. player %d, coins %d, other player coppers %d\n", p, G.coins, count_pre);
#endif

	r = cardEffect(card, choice1, choice2, choice3, &G, handPos, &bonus);

	// Get the count of coppers for player 2
	count_post = 0;
	for (i = 0; i < G.handCount[1]; i++) {
		if (G.hand[1][i] == copper) count_post++;
	}

#if (NOISY_TEST == 1)
	printf("Testing POST. player %d, coins %d, other player coppers %d\n", p, G.coins, count_post);
#endif

	
	// Make sure unrelated state is unchanged for other player
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