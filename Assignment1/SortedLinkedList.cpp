#include<iostream>
#include<cstdlib>

using namespace std;

struct ListNode {		// node of single list
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(nullptr) {}
};

class linklist {		//a list of links
private:
	ListNode* first;	//pointer to first link
public:
	linklist()			//no-argument constructor
	{
		first = nullptr;	//nullptr != NULL, it is better than "NULL"
	} 
	void Traverse();		//display all links
	void insert(int key);	//add data item (one link)
	void Delete(int key);	
};

// First, to search the right position, then insert
void linklist::insert(int key)
{
	ListNode * New_Node = new ListNode(key);
	if(first == nullptr)
		first = New_Node;
	else
	{
		ListNode * current = first;
		ListNode * pre = current;
		while(current != nullptr)
		{
			if(New_Node->val > current->val)
			{
				pre = current;	// it is important
				current = current->next;
				if(current == nullptr)	// this shows it is the last node
				{
					pre->next = New_Node; // please compare it with the next line of code
					//current = New_Node; // it is a serious problem, you must avoid it
					return;
				}
			}
			else // insert the node just before the current node
			{
				if(current == first)
				{
					New_Node->next = current;	//  
					first = New_Node;	//In this line, it must be "first", rather than "current"
				}
				else
				{
					New_Node->next = current;
					pre->next = New_Node;
				}
				return;
			}
		}
	}
}

void linklist::Delete(int key)
{
	ListNode* current = first;
	ListNode* pre = current;
	while (current != nullptr)
	{
		if(current->val == key)	//if they are equal, then delete
		{
			pre->next = current->next;
			delete current;
			return;
		}
		else
		{
			pre = current;
			current = current->next;
		}
	}
	cout <<"Key <"<<key<<"> not found"<< endl;
}

void linklist::Traverse()
{
	ListNode* current = first;			//set ptr to first link
	while (current != nullptr)			//quit on last link
	{
		cout <<"Item <"<<current->val<<">"<< endl;	//print data
		current = current->next;		//move to next link
	}
}

int main(int argc, char** argv) {
	linklist First_List;	//make linked list
	First_List.insert(12);	//add four items to list
	First_List.insert(8);
	First_List.insert(49);
	First_List.insert(64);
	First_List.insert(32);
	First_List.insert(99);
	First_List.insert(6);
	First_List.insert(50);
	First_List.insert(88);
	First_List.insert(66);
	First_List.Traverse(); //display entire list

	cout << endl;
	First_List.Delete(49);
	First_List.Delete(32);
	First_List.Delete(23); // cannot be found in the list
	First_List.Traverse();

	getchar();
	return 0;
}
