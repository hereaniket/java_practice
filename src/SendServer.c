#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <errno.h>
#include <unistd.h>
#include <arpa/inet.h>


#define PORT 7777
#define SIZE 1024


struct clients{
 struct sockaddr_in clientAddr;
 struct clients *next;
};

struct clients *AddClient(struct sockaddr_in new_ClientAddr,struct clients *head)
{

 struct clients *temp = head;
 puts("burda2");
 int a = 1;
 if(head == NULL)
 {
     temp = (struct clients *)malloc(sizeof(struct clients));
     temp->clientAddr = new_ClientAddr;
     temp->next = NULL;
 }
 else
 {
     while(temp != NULL)
     {
     if(temp->clientAddr.sin_addr.s_addr == new_ClientAddr.sin_addr.s_addr)
     {
    a = 0;
    break;
    }
         temp = temp->next;
     }
     if(a == 1)
     {
         temp->next = (struct clients *)malloc(sizeof(struct clients));
        temp->next->clientAddr = new_ClientAddr;
        temp = temp->next;
        temp->next = NULL;
     }
     else{
         puts("Its already save");
     }
 }
 return head;
}


void SendToAll(char msg[1023], struct sockaddr_in repliedClient, struct clients *head)
{
struct clients *temp = head;
int clients_socket;
int byte;
clients_socket  = socket(AF_INET, SOCK_STREAM, 0);
    if(clients_socket == -1)
        perror("Error On Socket(SendToAll)");
while(temp != NULL)
{
sleep(2)
   if(repliedClient.sin_addr.s_addr != temp->clientAddr.sin_addr.s_addr)//Dont send msg who to replied
    {
        if(connect(clients_socket, (struct sockaddr *)&temp->clientAddr,
                                            sizeof(struct sockaddr)) == -1)
        {
            perror("Error on Connect(SendToAll)");


        byte = send(clients_socket, msg, strlen(msg), 0);
        printf("%s message send",msg);

        if(byte == -1)
            perror("Error on Send(SendToAll");
        else if(byte == 0)
            printf("Connection've been closed");

        temp = temp->next;
        }
    }
}

}
// --- End of Function SendToAll() ---



int main(int argc, char *argv[])
{
struct clients *head = NULL;
int socket_fd, temp_fd;
struct sockaddr_in serverAddr,new_clientAddr;
int structSize,byte;
char text[1023];

// Creating socket
socket_fd = socket(AF_INET, SOCK_STREAM, 0);
if(socket_fd == -1)
    perror("Error on Soket");

// Editting Server socket
serverAddr.sin_family = AF_INET;
serverAddr.sin_port = htons(PORT);
serverAddr.sin_addr.s_addr = INADDR_ANY;
memset(&(serverAddr.sin_zero), '\0', 8);

// Bind
if(bind(socket_fd, (struct sockaddr *)&serverAddr, sizeof(struct sockaddr)) == -1)
    perror("Error on Bind");

// Start listen the port
if(listen(socket_fd, 20) == -1  )
    perror("Error on Listen");

structSize = sizeof(new_clientAddr);

while(1)
{
    puts(" ");
    // Accept
    temp_fd = accept(socket_fd , (struct sockaddr *)&new_clientAddr, &structSize);
    if(temp_fd == -1)
        perror("Error on Accept");
    // Recv
    byte = recv(temp_fd, &text, SIZE-1, 0);
    if(byte == -1)
        perror("Error on Recv");
    else if(byte == 0)
        printf("Connection is close\n");
    printf("%s", text);
    //Add to list
    head = AddClient(new_clientAddr, head);
    //Send message to other clients
    SendToAll(text, new_clientAddr, head);

    close(temp_fd);
}


close(socket_fd);


return (EXIT_SUCCESS);
}