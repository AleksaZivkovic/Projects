#include <stdio.h>
#include <string.h>

/*
unesi dva stringa, ispisi njihove duyine i napisi najduyi string
napisi da li su isti
*/


int main(int argc, char const *argv[]) {
char a[100];
char b[300];

  printf("Napisi prvi string:\n");
  scanf("%s", a);
  printf("Napisi drugi string:\n");
  scanf("%s", b);

  printf("Duzina prvog stringa je %d, duzina drugog stringa je %d\n", strlen(a),strlen(b));

  if (strlen(a)<strlen(b)){
    printf("%s\n", b);
  }  else {
    printf("%s\n",a);
  }
  if ( strcmp(a,b)==0){
    printf("Isti su\n");
  }else{
    printf("Nisu isti\n");
  }
  return 0;
}
