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

int checkAdventurer(int p, int handPos, struct gameState *post) {
	struct gameState pre;
	memcpy (&pre, post, sizeof(struct gameState));

	int r = 0;


	r = adventurerEffect(post, p);


	// Count the number of treasure cards in the deck
	int treasure = 0;
	int card, i;
	for (i = 0; i < pre.deckCount[p]; i++) {
		card = pre.deck[p][i];
		if (card == copper || card == silver || card == gold) {
			treasure++;
		}
	}

#if (NOISY_TEST == 1)
	printf("-----------------------------------------------------------------------------------\n");
	printf ("PRE: TreasureCount %d HandCount %d DeckCount %d DiscardCount %d\n", treasure, pre.handCount[p], pre.deckCount[p], pre.discardCount[p]);
#endif



	if (treasure >= 2) {
		// Since we know the deck has at least 2 Treasures,
		// draw cards from the deck until the we get the treasure cards
		int drawnTreasure = 0;
		while (drawnTreasure < 2) {
			// Get the top card from the deck
			card = pre.deck[p][pre.deckCount[p] - 1];

			// and add it to the hand
			pre.hand[p][pre.handCount[p]] = card;
			

			if (card == copper || card == silver || card == gold) {
				// Drew a Treasure card. Keep it in the hand.
				drawnTreasure++;
				pre.handCount[p]++;
			} else {
				// Did not draw a Treasure. Put this onto the discard.
				pre.discardCount[p]++;
			}
			pre.deckCount[p]--;
		}

		// The Adventurer puts cards on the discard in a backwards order,
		// so it's easier to just memcpy the discard piles here instead of
		// trying to put them on in reverse order.
		memcpy(pre.discard[p], post->discard[p], sizeof(int) * MAX_DECK);

		// Adventurer should discard itself onto the played pile like all the other cards
		// Currently it doesn't do that, so this part of the oracle reveals the bug and
		// causes the tests to fail every time.
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
		// There are not enough Treasure cards in the deck, but I know there are at least 2 Treasure
		// cards in the piles, so they must reside in the discard array. What Adventurer will do
		// is draw all cards off the deck until there are none left, then shuffle the discard cards
		// and add them back into the deck, then resume drawing cards until 2 Treasures are found.
		// How to mimic this in our test oracle?

#if (NOISY_TEST == 1)
		printf("SHUFFLE\n");
#endif

		memcpy(pre.deck[p], post->deck[p], sizeof(int) * MAX_DECK);
		memcpy(pre.discard[p], post->discard[p], sizeof(int) * MAX_DECK);
		memcpy(pre.hand[p], post->hand[p], sizeof(int) * MAX_HAND);

		pre.handCount[p]++;
		pre.deckCount[p] = post->deckCount[p];
		pre.discardCount[p] = post->discardCount[p];
	}



	assert (r == 0);

	// Compare bytes from the pre and post gameState
	// This also makes sure any other players states are not altered
#if (NOISY_TEST == 1)
	printf ("POST: TreasureCount %d HandCount %d DeckCount %d DiscardCount %d\n", treasure, post->handCount[p], post->deckCount[p], post->discardCount[p]);
#endif

	//assert(memcmp(&pre, post, sizeof(struct gameState)) == 0);
	if (!(memcmp(&pre, post, sizeof(struct gameState)) == 0)) {
		//printf("Assertion failed: (memcmp(&pre, post, sizeof(struct gameState)) == 0\n");
#if (NOISY_TEST == 1)
		printf("**FAILED**\n");
#endif
		return 1;
	}

	return 0;
}



int main () {

	struct gameState G;

	printf ("Testing Adventurer.\n");

	printf ("RANDOM TESTS.\n");

	SelectStream(2);
	PutSeed(3);

	int i, n, p, handPos;
	int result = 0;

	for (n = 0; n < 10000; n++) {
		for (i = 0; i < sizeof(struct gameState); i++) {
			((char*)&G)[i] = floor(Random() * 256);	// Write random characters to the gameState
		}

		p = floor(Random() * MAX_PLAYERS);
		G.whoseTurn = p;

		// Randomize the cards in the game, making sure there are at least 2 treasure cards to work with
		// From the deck or discard
		int treasure = 0;
		int card;
		while (treasure < 2) {

			// Randomize the size of the decks, but I don't want the sum of deck size + discard size
			// to be more than MAX_DECK, because if non-treasure cards are drawn from the deck they will be added
			// to the discard, and I don't want to overflow the bounds of the discard array.
			treasure = 0;

			G.deckCount[p] = floor(Random() * MAX_DECK);
			G.discardCount[p] = floor(Random() * (MAX_DECK - G.deckCount[p]));
			G.handCount[p] = 1 + floor(Random() * (MAX_HAND - 3));	// Leaving space for 2 Treasure cards

			// Check the piles for Treasure cards
			for (card = 0; card < G.deckCount[p]; card++) {
				G.deck[p][card] = floor(Random() * NUM_TOTAL_K_CARDS);
				if (card == copper || card == silver || card == gold) {
					treasure++;
				}
			}

			for (card = 0; card < G.discardCount[p]; card++) {
				G.discard[p][card] = floor(Random() * NUM_TOTAL_K_CARDS);
				if (card == copper || card == silver || card == gold) {
					treasure++;
				}
			}

			for (card = 0; card < G.handCount[p]; card++) {
				G.hand[p][card] = floor(Random() * NUM_TOTAL_K_CARDS);
			}
		}

		handPos = floor(Random() * G.handCount[p]);	// Randomly place the Adventurer in the hand

		result += checkAdventurer(p, handPos, &G);
	}

	if (result == 0) {
		printf ("ALL TESTS OK\n");
	} else {
		printf ("Tests failed\n");
	}


	return 0;
}
