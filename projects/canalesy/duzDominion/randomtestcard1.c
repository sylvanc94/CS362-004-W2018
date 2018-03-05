#include "dominion.h"
#include "dominion_helpers.h"
#include "interface.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <assert.h>
#include "rngs.h"

#define DEBUG 0
#define NOISY_TEST 0

int checkSmithy(int p, int handPos, struct gameState *post) {
	struct gameState pre;
	memcpy (&pre, post, sizeof(struct gameState));

	int r = 0;
	int i, card;

	r = smithyEffect(p, post, handPos);

	card = pre.hand[p][handPos];

#if (NOISY_TEST == 1)
	printf("-----------------------------------------------------------------------------------\n");
	printf ("PRE: HandCount %d DeckCount %d DiscardCount %d\n", pre.handCount[p], pre.deckCount[p], pre.discardCount[p]);
#endif

	if (pre.deckCount[p] > 2) {
		// Normal drawing from the deck, no shuffling required
		for (i = 0; i < 3; i++) {
			pre.hand[p][pre.handCount[p]] = pre.deck[p][pre.deckCount[p] - 1];
			pre.handCount[p]++;
			pre.deckCount[p]--;
		}

		// Re-creating discardCard() functionality
		pre.hand[p][handPos] = -1;

		//remove card from player's hand
		if ( handPos == (pre.handCount[p] - 1) ) {
			pre.handCount[p]--;	//If removing the last card from the hand, simply decrement the hand count
		} else {
			pre.hand[p][handPos] = pre.hand[p][pre.handCount[p] - 1];	//replace discarded card with last card in hand
			pre.hand[p][pre.handCount[p] - 1] = -1;		//set last card to -1
			pre.handCount[p]--;	
		}

	} else {
		// A shuffle will be required

#if (NOISY_TEST == 1)
		//printf("SHUFFLE\n");
#endif

		memcpy(pre.deck[p], post->deck[p], sizeof(int) * MAX_DECK);
		memcpy(pre.discard[p], post->discard[p], sizeof(int) * MAX_DECK);
		memcpy(pre.hand[p], post->hand[p], sizeof(int) * MAX_HAND);

		pre.handCount[p] += 2;
		pre.deckCount[p] = post->deckCount[p];
		pre.discardCount[p] = post->discardCount[p];
	}

	// Place Smithy on the played pile
	pre.playedCards[pre.playedCardCount] = card;
	pre.playedCardCount++;

	assert (r == 0);

	// Compare bytes from the pre and post gameState
	// This also makes sure any other players states are not altered

	//assert(memcmp(&pre, post, sizeof(struct gameState)) == 0);
	if (!(memcmp(&pre, post, sizeof(struct gameState)) == 0)) {
#if (NOISY_TEST == 1)
		printf("*FAIL*\n");
#endif
		//printf("Assertion failed: (memcmp(&pre, post, sizeof(struct gameState)) == 0\n");
		return 1;
	}

#if (NOISY_TEST == 1)
	printf("PASS\n");
#endif
	return 0;
}



int main () {

	struct gameState G;

	printf ("Testing Smithy.\n");

	printf ("RANDOM TESTS.\n");

	SelectStream(2);
	PutSeed(3);

	int i, n, p, handPos;
	int result = 0;

	for (n = 0; n < 100000; n++) {
		for (i = 0; i < sizeof(struct gameState); i++) {
			((char*)&G)[i] = floor(Random() * 256);	// Write random characters to the gameState
		}

		p = floor(Random() * MAX_PLAYERS);
		G.whoseTurn = p;



		// Randomize the size of the decks, but need at least 3 cards to draw from
		int cardsToDraw = 0;
		while (cardsToDraw < 3) {
			cardsToDraw = 0;
			G.deckCount[p] = floor(Random() * MAX_DECK);
			G.discardCount[p] = floor(Random() * MAX_DECK);
			G.handCount[p] = 1 + floor(Random() * (MAX_HAND - 4));	// Leaving space for 3 drawn cards with minimum 1 card
			G.playedCardCount = floor(Random() * (MAX_DECK - 1)); // Leaving space for Smithy

			handPos = floor(Random() * G.handCount[p]);	// Randomly place the Smithy in the hand

			cardsToDraw = G.deckCount[p] + G.discardCount[p];
		}

		result += checkSmithy(p, handPos, &G);	// Any return values > 0 will indicate there was a failure
	}

	if (result == 0) {
		printf ("ALL TESTS OK\n");
	} else {
		printf ("Tests failed\n");
	}


	return 0;
}
