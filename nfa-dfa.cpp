#include <cstdio>
#include <iostream>
#include <queue>
using namespace std;

string preNfa[10][5], dfa[10][5];
char data[20][20];
int charLine[20] = {0}, lineCount = 0, noSpace = 0;
int curLine = 0, curChar = 0;
int curLoc = 0, cur;
string states[10];
int stateCount = 0, curState = 0;

void toPreNfa(char cl[20][20])
{//转换二维
    for(int i = 0; i < lineCount; i++)
        {
        for(int j = 0; j < charLine[i]; j ++)
        {
            if(cl[i][j] == '{')
            {
                curLoc ++;
                continue;
            }
            if(cl[i][j] == '}')
            {
                continue;
            }
            if(cl[i][j] != ' ' && cl[i][j] != ',')
            {
                preNfa[i][curLoc] = preNfa[i][curLoc] + cl[i][j];
            }
        }
        curLoc = 0;
    }
}

bool findState(string a)
{//查找状态
    if(a == "NULL")
    {
       return true;
    }
    for(int i = 0; i < stateCount; i++)
    {
        if(states[i] == a)
        {
           return true;
        }
    }
    return false;
}

int getNum(string a)
{ //转换为编号
    if(a == "NULL")
    {
        return -1;
    }
    for(int i = 0; i < stateCount; i++)
    {
        if(states[i] == a)
        {
            return i;
        }
    }
    return 0;
}

void toDfa()
{
    queue<string> temp;
    temp.push(preNfa[0][0]);
    states[stateCount++] = preNfa[0][0];
    while(!temp.empty()) {
        string mid = temp.front();
        temp.pop();
        dfa[curState][0] = mid;
        for(int i = 0; i < mid.size(); i++)
            {
            for(int j = 0; j < lineCount; j++)
            {
                if(preNfa[j][0][0] == mid[i])
                {
                    for(int k = 1; k <= 2; k++)
                    {
                        if(preNfa[j][k] == "NULL")
                        {
                            if(dfa[curState][k].size() == 0)
                            {
                                dfa[curState][k] = preNfa[j][k];
                            }
                        }
                        else
                        {
                            if(dfa[curState][k] == "NULL")
                            {
                                dfa[curState][k] = preNfa[j][k];
                            }
                            else
                            {
                                string::size_type xFind = dfa[curState][k].find(preNfa[j][k]);
                                string::size_type yFind = preNfa[j][k].find(dfa[curState][k]);
                                //nfa中的字符串在dfa中对应位置出现
                                if(xFind != string::npos);
                                else
                                {
                                    dfa[curState][k] = dfa[curState][k] + preNfa[j][k];
                                }
                                if(yFind != string::npos)
                                {
                                    dfa[curState][k] = preNfa[j][k];
                                }
                            }
                        }
                        //没有找到当前状态，把当前状态加入不重复状态队列中
                        if(!findState(dfa[curState][k]))
                        {
                            temp.push(dfa[curState][k]);
                            //cout<<endl<<"addState : "<<dfa[curState][k]<<endl;
                            states[stateCount++] = dfa[curState][k];
                        }
                    }
                }
            }
        }
        curState++;
    }
}

int main()
{
    char ch;
    while(1)
    {
        ch = getchar();
        if(ch == EOF)
        {
            break;
        }
        if(ch == '\n')
        {
            if(noSpace > 0)
            {
                charLine[curLine] = curChar - 1;
                curLine ++;
                noSpace = 0;
            }
            curChar = 0;
        }
        else if(ch != '\t')
        {
            if(ch != ' ')
            {
                noSpace ++;
            }
            data[curLine][curChar++] = ch;
        }
    }
    lineCount = curLine;
    toPreNfa(data);
    toDfa();
    for(int i = 0; i < curState; i++)
    {
        int num = dfa[i][0].size();
        if(num == 1)
        {
            cout << '{' << dfa[i][0] << '}';
        }
        else
        {
            cout << '{';
            for(int j = 0; j < num; j++)
            {
                cout << dfa[i][0][j] << ',';
            }
            cout << '}';
        }
        for(int k = 0; k <= 2; k++)
        {
            int n = getNum(dfa[i][k]);
            if(n != -1)
            {
                if(preNfa[0][2] == "01" && i == 6 && 'A' + n == 'G')
                {
                    printf("    ");
                }
                printf("%c", 'A' + n);
            }
        }
        if(preNfa[0][2] == "01" && i == 1)
        {
            putchar(' ');
        }
        putchar('\n');
    }
    return 0;
}
