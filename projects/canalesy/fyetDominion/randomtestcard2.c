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

int checkCouncilRoom(int p, int handPos, struct gameState *post) {
	struct gameState pre;
	memcpy (&pre, post, sizeof(struct gameState));

	int r = 0;
	int i, card;
	int bonus = 0;

#if (NOISY_TEST == 1)
	printf("-----------------------------------------------------------------------------------\n");
	printf ("PRE: player 0: HandCount %d DeckCount %d DiscardCount %d\n", pre.handCount[0], pre.deckCount[0], pre.discardCount[0]);
	printf ("PRE: player 1: HandCount %d DeckCount %d DiscardCount %d\n", pre.handCount[1], pre.deckCount[1], pre.discardCount[1]);
	printf ("PRE: player 2: HandCount %d DeckCount %d DiscardCount %d\n", pre.handCount[2], pre.deckCount[2], pre.discardCount[2]);
	printf ("PRE: player 3: HandCount %d DeckCount %d DiscardCount %d\n", pre.handCount[3], pre.deckCount[3], pre.discardCount[3]);
	printf ("PRE: p %d handPos %d numPlayers %d numBuys %d\n", p, handPos, pre.numPlayers, pre.numBuys);
#endif

	r = cardEffect(council_room, 0, 0, 0, post, handPos, &bonus);

	card = pre.hand[p][handPos];


	if (pre.deckCount[p] > 3) {
		// The player can draw 4 cards from the deck without shuffling
		for (i = 0; i < 4; i++) {
			pre.hand[p][pre.handCount[p]] = pre.deck[p][pre.deckCount[p] - 1];
			pre.handCount[p]++;
			pre.deckCount[p]--;
		}

		// Re-creating discardCard() functionality to put the played card on the played pile
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
		printf("primary player SHUFFLE\n");
#endif

		memcpy(pre.deck[p], post->deck[p], sizeof(int) * MAX_DECK);
		memcpy(pre.discard[p], post->discard[p], sizeof(int) * MAX_DECK);
		memcpy(pre.hand[p], post->hand[p], sizeof(int) * MAX_HAND);

		pre.handCount[p] += 3;
		pre.deckCount[p] = post->deckCount[p];
		pre.discardCount[p] = post->discardCount[p];

	}

	for (i = 0; i < pre.numPlayers; i++) {
		if (i != p) {
			//printf("in loop. p: %d i: %d\n", p, i);
			if (pre.deckCount[i] > 0) {
				// This player can draw a card from the deck without shuffling
				pre.hand[i][pre.handCount[i]] = pre.deck[i][pre.deckCount[i] - 1];
				pre.handCount[i]++;
				pre.deckCount[i]--;
			} else {
				// A shuffle will be required

				memcpy(pre.deck[i], post->deck[i], sizeof(int) * MAX_DECK);
				memcpy(pre.discard[i], post->discard[i], sizeof(int) * MAX_DECK);
				memcpy(pre.hand[i], post->hand[i], sizeof(int) * MAX_HAND);

				pre.handCount[i]++;
				pre.deckCount[i] = post->deckCount[i];
				pre.discardCount[i] = post->discardCount[i];
			}
		}

	}

	// Place Council Room on the played pile
	pre.playedCards[pre.playedCardCount] = card;
	pre.playedCardCount++;

	pre.numBuys++;

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

	printf ("Testing Council Room.\n");

	printf ("RANDOM TESTS.\n");

	SelectStream(2);
	PutSeed(3);

	int i, n, p, handPos;
	int result = 0;

	for (n = 0; n < 100000; n++) {
		for (i = 0; i < sizeof(struct gameState); i++) {
			((char*)&G)[i] = floor(Random() * 256);	// Write random characters to the gameState
		}

		G.numPlayers = MAX_PLAYERS;
		G.whoseTurn = floor(Random() * MAX_PLAYERS);
		G.playedCardCount = floor(Random() * (MAX_DECK - 1)); // Leaving space for Council Room
		G.numBuys = 1;

		for (p = 0; p < MAX_PLAYERS; p++) {
			// Randomize the size of the decks for all players, but need at least 4 cards to draw from
			int cardsToDraw = 0;
			while (cardsToDraw < 4) {
				cardsToDraw = 0;
				G.deckCount[p] = floor(Random() * MAX_DECK);
				G.discardCount[p] = floor(Random() * MAX_DECK);
				G.handCount[p] = 1 + floor(Random() * (MAX_HAND - 5));	// Leaving space for 4 drawn cards with minimum 1 card

				cardsToDraw = G.deckCount[p] + G.discardCount[p];
			}
		}

		p = G.whoseTurn;
		handPos = floor(Random() * G.handCount[p]);	// Randomly place the Council Room in the hand

		result += checkCouncilRoom(p, handPos, &G);	// Any return values > 0 will indicate there was a failure
	}

	if (result == 0) {
		printf ("ALL TESTS OK\n");
	} else {
		printf ("Tests failed\n");
	}


	return 0;
}
