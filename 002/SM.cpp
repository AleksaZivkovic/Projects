#include "SM.h"

#include <string>
#include <iostream>
#include <sstream>
#include <vector>

SM::SM(){
  std::cout << "Created SM class\n";
}

int SM::stringToInt(std::string str){
  int res = std::stoi(str);
  return res;
}

double SM::stringToDouble(std::string str){
  double res = std::stod(str);
  return res;
}

float SM::stringToFloat(std::string str){
  float res = std::stof(str);
  return res;
}

void SM::stringToInt(std::string str, int& num){
  int res = std::stoi(str);
  //int num2 = const_cast<int&>(num);

  num = res;
}

void SM::stringToDouble(std::string str, double& num){
  double res = std::stod(str);
  //double num2 = const_cast<double&>(num);

  num = res;
}

void SM::stringToFloat(std::string str, float& num){
  float res = std::stof(str);
  //float num2 = const_cast<float&>(num);

  num = res;
}

std::string SM::intToString(int a){
  std::string res;
  std::stringstream ss;

  ss << a;
  ss >> res;

  return res;
}

std::string SM::doubleToString(double a){
  std::string res;
  std::stringstream ss;

  ss << a;
  ss >> res;

  return res;
}

std::string SM::floatToString(float a){
  std::string res;
  std::stringstream ss;

  ss << a;
  ss >> res;

  return res;
}

void SM::intToString(int a, std::string& str){
  std::string res;
  std::stringstream ss;
  //std::string& str2 = const_cast<std::string&>(str);

  ss << a;
  ss >> res;

  str = res;
}

void SM::doubleToString(double a, std::string& str){
  std::string res;
  std::stringstream ss;
  //std::string& str2 = const_cast<std::string&>(str);

  ss << a;
  ss >> res;

  str = res;
}

void SM::floatToString(float a, std::string& str){
  std::string res;
  std::stringstream ss;
  //std::string& str2 = const_cast<std::string&>(str);

  ss << a;
  ss >> res;

  str = res;
}

void SM::printVector(std::vector<int> intVector){
  std::string res = "";
  for(int num : intVector){
    res += SM::intToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  std::cout << res << "\n";
}

void SM::printVector(std::vector<double> doubleVector){
  std::string res = "";
  for(double num : doubleVector){
    res += SM::doubleToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  std::cout << res << "\n";
}

void SM::printVector(std::vector<float> floatVector){
  std::string res = "";
  for(float num : floatVector){
    res += SM::floatToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  std::cout << res << "\n";
}

void SM::printVector(std::vector<std::string> stringVector){
  std::string res = "";
  for(std::string str : stringVector){
    res += str;
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  std::cout << res << "\n";
}

std::string SM::vectorToString(std::vector<int> intVector){
  std::string res = "";
  for(int num : intVector){
    res += SM::intToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  return res;
}

std::string SM::vectorToString(std::vector<double> doubleVector){
  std::string res = "";
  for(double num : doubleVector){
    res += SM::doubleToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  return res;
}

std::string SM::vectorToString(std::vector<float> floatVector){
  std::string res = "";
  for(float num : floatVector){
    res += SM::floatToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  return res;
}

std::string SM::vectorToString(std::vector<std::string> stringVector){
  std::string res = "";
  for(std::string str : stringVector){
    res += str;
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  return res;
}

void SM::vectorToString(std::vector<int> intVector, std::string& str){
  std::string res = "";
  for(int num : intVector){
    res += SM::intToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  str = res;
}

void SM::vectorToString(std::vector<double> doubleVector, std::string& str){
  std::string res = "";
  for(double num : doubleVector){
    res += SM::doubleToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  str = res;
}

void SM::vectorToString(std::vector<float> floatVector, std::string& str){
  std::string res = "";
  for(float num : floatVector){
    res += SM::floatToString(num);
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  str = res;
}

void SM::vectorToString(std::vector<std::string> stringVector, std::string& str){
  std::string res = "";
  for(std::string str : stringVector){
    res += str;
    res += ", ";
  }

  res = res.substr(0, res.length() - 2);

  str = res;
}

std::vector<std::string> SM::split(std::string str, char c){
  std::vector<std::string> res;

  std::string temp = "";
  for(int i = 0; i < str.length(); i++){
    if(str[i] == c && temp != ""){
      res.push_back(temp);
      temp = "";
    }else if(str[i] != c){
      temp += str[i];
    }
  }

  if(temp != ""){
    res.push_back(temp);
  }

  return res;
}

void SM::split(std::string str, char c, std::vector<std::string>& stringVector){
  std::string temp = "";
  for(int i = 0; i < str.length(); i++){
    if(str[i] == c && temp != ""){
      stringVector.push_back(temp);
      temp = "";
    }else if(str[i] != c){
      temp += str[i];
    }
  }

  if(temp != ""){
    stringVector.push_back(temp);
  }
}
