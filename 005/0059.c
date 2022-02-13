#include <stdio.h>
/*
Let us try to find out the average marks of a group of
five students for two subjects, Mathematics and
Programming. To do this, we use a two-dimensional
array called grades. The marks corresponding to
Mathematics would be stored in the first row
(grades[0]), whereas those corresponding to
Programming would be stored in the second row
(grades[1]). Complete the following steps:
• Declare grades as a two-dimensional array of
integers
• Compute the average marks obtained in each
subject
*/
int main(int argc, char const *argv[]){
  int grades[2][5];
  grades [0][0]=1;
  grades [0][1]=4;
  grades [0][2]=3;
  grades [0][3]=5;
  grades [0][4]=5;

  grades [1][0]=1;
  grades [1][1]=5;
  grades [1][2]=3;
  grades [1][3]=2;
  grades [1][4]=1;

  float averagem= (grades [0][0]+grades [0][1]+grades [0][2]+grades [0][3]+grades [0][4])/5.0;
  float averagep= (grades [1][0]+grades [1][1]+grades [1][2]+grades [1][3]+grades [1][4])/5.0;

  printf("Average mark for matem is %.2f and average mark for programming is %.2f\n", averagem, averagep);

  return 0;

}
