#include <stdio.h>
#include <string.h>
/*
Write a programme which has your name and
your age saved in respective variables. Print out
your name and your age in the same sentence.
Print out the length of your name which is
stored in a string variable.
*/

int main(int argc, char const *argv[]) {
    char name[20];
    int age;

    printf("Please enter your name\n" );
    scanf("%s", name );
    printf("Pls enter your age\n");
    scanf("%d", &age );

    printf("Your name is %s and your age is %d\n",name,age );
    printf("Your name has %d letters\n", strlen(name));
  return 0;
}
