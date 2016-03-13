#include<iostream>
#include<cstdlib>

using namespace std;

#define max_size 10
int s_front[max_size] = {0};
int s_back[max_size] = {0};

int cnt_front = 0; 
int cnt_back = 0;	//it is number of element in array, rather than index of array

void copy_back_to_front()
{
	if(cnt_front==0 && cnt_back>0)	//if out_stack is empty
	{
		for(int i=0; i<cnt_back;i++) //please pay attention to the opposite index
			s_front[cnt_back-1-i] = s_back[i];
		cnt_front = cnt_back;
		cnt_back = 0;
	}
}

bool Q_Push(int data)
{
	if(cnt_back<10)
	{
		cnt_back++;
		s_back[cnt_back-1] = data;
		copy_back_to_front();
		cout<<"item <"<<data<<"> enqueued"<<endl;
		return true;
	}
	else{
		cout<<"the number of input is over the limits!"<<endl;
		return false;
	}
}

int Q_Pop()
{
	int tmp=0;
	if(cnt_front!=0)	//front¶ÑÕ»²»Îª¿Õ
	{
		tmp=s_front[cnt_front-1];
		cout<<"item <"<<tmp<<"> dequeued"<<endl;
		cnt_front--;	// equal to pop()
		copy_back_to_front();
		return tmp; //return the value of pop
	}
	else{
		cout<<"the Queue is empty!"<<endl;
		return false;
	}
}

int main()
{
	Q_Push(1);
	Q_Push(2);
	Q_Pop();
	Q_Push(3);
	Q_Pop();
	Q_Push(4);
	Q_Push(5);
	Q_Pop();
	Q_Pop();
	Q_Pop();
	Q_Push(6);
	Q_Pop();
	for(int i=7; i<12;i++)
		Q_Push(i);
	for(int i=0; i<5;i++)
		Q_Pop();

	getchar();
	return 0;
}