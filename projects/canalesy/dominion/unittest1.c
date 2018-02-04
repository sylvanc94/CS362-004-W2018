/* -----------------------------------------------------------------------
 * Sylvan Canales
 * Assignment 3
 * Include the following lines in your makefile:
 *
 * unittest1: unittest1.c dominion.o rngs.o
 *      gcc -o unittest1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)
 * -----------------------------------------------------------------------
 */

#include "dominion.h"
#include <stdio.h>
#include <assert.h>

// set NOISY_TEST to 0 to remove printfs from output
#define NOISY_TEST 0

int main()
{
	int a;
	int b;
	int result;

	printf ("TESTING compare():\n");

	// Test for a < b
	a = 2;
	b = 3;
	result = compare(&a, &b);

#if (NOISY_TEST == 1)
	printf("a = %d, b = %d, result = %d, expected = -1\n", a, b, result);
#endif
	assert(result == -1); // check if the result is correct
	assert(a == 2); // check if a hasn't been altered
	assert(b == 3); // check if b hasn't been altered


	// Test for a > b
	a = 3;
	b = 2;
	result = compare(&a, &b);

#if (NOISY_TEST == 1)
	printf("a = %d, b = %d, result = %d, expected = 1\n", a, b, result);
#endif
	assert(result == 1); // check if the result is correct
	assert(a == 3); // check if a hasn't been altered
	assert(b == 2); // check if b hasn't been altered


	// Test for a == b
	a = 2;
	b = 2;
	result = compare(&a, &b);

#if (NOISY_TEST == 1)
	printf("a = %d, b = %d, result = %d, expected = 0\n", a, b, result);
#endif
	assert(result == 0); // check if the result is correct
	assert(a == 2); // check if a hasn't been altered
	assert(b == 2); // check if b hasn't been altered

	printf("TEST SUCCESSFULLY COMPLETED!\n");
	return 0;
}