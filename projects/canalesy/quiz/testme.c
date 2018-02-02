#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

char inputChar()
{
    /*
     * Simple random pick from a subset of the ASCII table. I'm only
     * choosing from the 'normal' characters here, which starts from
     * the space, goes through punctuation, numbers, and letters mostly,
     * but avoids the extended ASCII codes which can have strange behavior.
     */
    char x = rand() % 95 + 32;
    return x;
}

char *inputString()
{
    /*
     * Here I am creating a string of fixed length 6, and assigning
     * the first five characters to a random character in the set of
     * lower-case alphabet letters. I made this restriction to keep
     * the run-time down. There are 26^5 possibilities which takes on
     * average 11,881,376 iterations to make 'reset'.
     */
    char *my_string = calloc(6, sizeof(char));
    my_string[0] = rand() % 26 + 97;
    my_string[1] = rand() % 26 + 97;
    my_string[2] = rand() % 26 + 97;
    my_string[3] = rand() % 26 + 97;
    my_string[4] = rand() % 26 + 97;
    return my_string;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error ");
      exit(200);
    }
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}
