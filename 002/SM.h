#ifndef SM_H
#define SM_H

#include <iostream>
#include <string>
#include <vector>

class SM{
public:
  SM();

  int stringToInt(std::string);
  double stringToDouble(std::string);
  float stringToFloat(std::string);

  void stringToInt(std::string, int&);
  void stringToDouble(std::string, double&);
  void stringToFloat(std::string, float&);

  std::string intToString(int);
  std::string doubleToString(double);
  std::string floatToString(float);

  void intToString(int, std::string&);
  void doubleToString(double, std::string&);
  void floatToString(float, std::string&);

  void printVector(std::vector<int>);
  void printVector(std::vector<double>);
  void printVector(std::vector<float>);
  void printVector(std::vector<std::string>);

  std::string vectorToString(std::vector<int>);
  std::string vectorToString(std::vector<double>);
  std::string vectorToString(std::vector<float>);
  std::string vectorToString(std::vector<std::string>);

  void vectorToString(std::vector<int>, std::string&);
  void vectorToString(std::vector<double>, std::string&);
  void vectorToString(std::vector<float>, std::string&);
  void vectorToString(std::vector<std::string>, std::string&);

  std::vector<std::string> split(std::string, char);
  void split(std::string, char, std::vector<std::string>&);
};
#endif
