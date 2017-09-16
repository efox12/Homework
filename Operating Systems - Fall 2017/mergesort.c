/*
* Erik Fox
* CPSC 326-02
* 9/15/2017
* Programming Assignment 1
*/
#include <stdio.h>
#include <stdlib.h>

struct node{
	int data;
	struct node * next; 
};

struct node* merge(struct node* head1, struct node* head2);
struct node* mergeSort(struct node* head, int size);
int listSize(struct node* head);
void printList(struct node* head, int size);

int main(void){
	// nodes for linked list
 	struct node* head11 = (struct node*) malloc(sizeof(struct node));
	struct node* head12 = (struct node*) malloc(sizeof(struct node));
	struct node* head13 = (struct node*) malloc(sizeof(struct node));
	struct node* head21 = (struct node*) malloc(sizeof(struct node));
	struct node* head22 = (struct node*) malloc(sizeof(struct node));
	struct node* head23 = (struct node*) malloc(sizeof(struct node));

 	head11->data = 3;
 	head11->next = head12;

 	head12->data = 5;
 	head12->next = head13;

 	head13->data = 9;
 	head13->next = head21;

 	head21->data = 1;
 	head21->next = head22;

 	head22->data = 6;
 	head22->next = head23;

 	head23->data = 7;
 	head23->next = NULL;

 	printList(head11, listSize(head11));
 	
 	return 0;
}

// merges two lists and sorts them
struct node* merge(struct node* head1, struct node* head2){
	struct node* head = (struct node*) malloc(sizeof(struct node));
	struct node* temp = (struct node*) malloc(sizeof(struct node));
	struct node* tail = (struct node*) malloc(sizeof(struct node));
	head = NULL;
	// keep adding the next small value to the new list until one list runs out of values
	while(head1 && head2) {
	    if(head1->data < head2->data){
	    	temp = head1;
	    	head1 = head1->next;
	    }
	    else{
	    	temp = head2;
	    	head2 = head2->next;
	    }
	    // assign head and tail on the first run through
	    if(head == NULL){
	    	tail = head = temp;
	    } else {
	    	tail->next = temp;
	    	tail = tail->next;
	    }  
	}
	// add the rest of the remaining list to the tail of the new list
	if(head1){
		tail->next = head1;
	}
	else if(head2){
		tail->next = head2;
	} else{
		tail->next = NULL;
	}
	return head;
}

// mergesorts a linked list
struct node* mergeSort(struct node* head, int size){
	struct node* head2 = (struct node*) malloc(sizeof(struct node));
	struct node* temp = (struct node*) malloc(sizeof(struct node));

	// base case
	if(size > 2){
		int size1 = size/2;
		int size2 = size - size1;

		// seperate list
		temp = head;
		for(int i = 0; i < (size1 - 1); i++){
			temp = temp->next;
		} 
		head2 = temp->next;
		temp->next = NULL;

		// recursive step
		return merge(mergeSort(head, size1), mergeSort(head2, size2));
	}
	else{
		return head;
	}
}

// gets the size of a linked list
int listSize(struct node* head){
	int count = 0;
	while(head){
		head = head->next;
		count++;
	}
	return count;
}

// prints the unsorted linked list and the sorted liked list
void printList(struct node* head, int size){
	struct node* temp = (struct node*) malloc(sizeof(struct node));
	struct node* merged = (struct node*) malloc(sizeof(struct node));
	// unsorted 
	temp = head;
	printf("%s", "Original list: ");
 	while(temp != NULL){
 		printf("%d", temp->data);
 		temp = temp->next;
 	}
 	printf("\n");

 	//sorted
 	printf("%s", "Sorted list: ");
	merged = mergeSort(head, size);
 	while(merged != NULL){
 		printf("%d", merged->data);
 		merged = merged->next;
 	}
 	printf("\n");
}